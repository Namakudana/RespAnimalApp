package com.mardana.respanimalapp.question_page

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.mardana.respanimalapp.data.QuestionModel
import com.mardana.respanimalapp.data.ScoreModel
import com.mardana.respanimalapp.utils.calculateScore

class QuestionPageViewModel() : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private var timer: CountDownTimer? = null

    private val initialTime = MutableLiveData<Long>((60 * 1000) * 5)
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _questionList = MutableLiveData<Map<Int, QuestionModel>>()
    val questionList: LiveData<Map<Int, QuestionModel>>
        get() = _questionList


    private val _currentNumber = MutableLiveData(1)
    val currentNumber: LiveData<Int>
        get() = _currentNumber

    private val _currentQuestion = MutableLiveData<QuestionModel>()
    val currentQuestion: LiveData<QuestionModel>
        get() = _currentQuestion

    private val _currentScore = MutableLiveData<Int>()

    private val _eventCountDownFinish = MutableLiveData<Boolean>()
    val eventCountDownFinish: LiveData<Boolean> = _eventCountDownFinish

    private fun startTimer() {
        timer = object : CountDownTimer(initialTime.value ?: 0, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished
            }

            override fun onFinish() {
                finishTimer()
            }
        }
        timer?.start()
    }

    fun finishTimer() {
        timer?.cancel()
        _currentTime.value = initialTime.value
        _eventCountDownFinish.value = true
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun getQuestionList() {
        db.collection("question").addSnapshotListener { value, error ->
            val tempList = mutableMapOf<Int, QuestionModel>()
            if (value != null && !value.isEmpty) {
                value.documents.forEachIndexed { index, snapshot ->
                    val getQuestion = snapshot.toObject(QuestionModel::class.java)
                    getQuestion?.let { question ->
                        tempList[index + 1] = question
                    }
                }
                _questionList.value = tempList
                _currentQuestion.value = tempList[currentNumber.value]
            } else {
                tempList.clear()
                Log.e("Question VM", "data kosong atau null")
            }
        }
    }

    fun nextQuestion(
        answer: String,
    ) {
        if (currentQuestion.value?.answer == answer.lowercase()) {
            _currentScore.value = (_currentScore.value ?: 0) + 1
        }
        if ((questionList.value?.size ?: 0) == currentNumber.value) {
            finishTimer()
        } else {
            _currentNumber.value = (currentNumber.value ?: 0) + 1
            _currentQuestion.value = questionList.value?.get(currentNumber.value ?: 0)
        }
    }

    fun saveScore(
        scoreModel: ScoreModel,
        callback: (success: Boolean, data: String) -> Unit,
    ) {
        val getCurrentScore =
            calculateScore((_currentScore.value ?: 0), (questionList.value?.size ?: 0))
        val newData = ScoreModel(
            uid = scoreModel.uid,
            name = scoreModel.name,
            room = scoreModel.room,
            score = getCurrentScore,
            scoreUpdateAt = System.currentTimeMillis()
        )
        db.collection("score").document(scoreModel.uid ?: "0").set(newData)
            .addOnSuccessListener {
                callback(true, getCurrentScore)
            }.addOnFailureListener {
                callback(false, it.message.toString())
                Log.e("QuestionPageVM", it.message.toString())
            }
    }

    init {
        startTimer()
        getQuestionList()
    }
}

