package com.mardana.respanimalapp.question_page

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mardana.respanimalapp.QuizResults
import com.mardana.respanimalapp.R
import com.mardana.respanimalapp.databinding.ActivityQuestionPageBinding
import com.mardana.respanimalapp.user_preference.UserPreference
import com.mardana.respanimalapp.utils.Pattern
import com.mardana.respanimalapp.utils.timestampToDate

class QuestionPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionPageBinding
    private lateinit var questionPageViewModel: QuestionPageViewModel
    var currentAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreference = UserPreference(this)

        questionPageViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[QuestionPageViewModel::class.java]

        questionPageViewModel.currentTime.observe(this) {
            binding.timer.text = it.timestampToDate(Pattern.minutePattern)
        }

        questionPageViewModel.eventCountDownFinish.observe(this) {
            if (it) {
                questionPageViewModel.saveScore(
                    userPreference.getUserData()
                ) { success, data ->
                    Toast.makeText(this, "Nilai anda adalah $data", Toast.LENGTH_SHORT).show()
                    if (success) {
                        startActivity(Intent(this@QuestionPageActivity, QuizResults::class.java))
                        userPreference.inputScore(data)
                        finish()
                    }
                }
            }
        }

        questionPageViewModel.currentQuestion.observe(this) {
            binding.question.text = it.question.toString()
            binding.option1.text = it.option?.get("a").toString()
            binding.option2.text = it.option?.get("b").toString()
            binding.option3.text = it.option?.get("c").toString()
            binding.option4.text = it.option?.get("d").toString()
        }

        binding.btnNext.setOnClickListener {
            resetColor()
            if (currentAnswer == null) {
                Toast.makeText(
                    this@QuestionPageActivity,
                    "Silahkan pilih jawaban",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                questionPageViewModel.nextQuestion(currentAnswer ?: "z")
                currentAnswer = null
            }
        }

        with(questionPageViewModel) {
            questionList.observe(this@QuestionPageActivity) { listQuestion ->
                binding
                currentNumber.observe(this@QuestionPageActivity) { currentNumber ->
                    binding.number.text = "$currentNumber/${listQuestion.size}"
                    if ((listQuestion.size == currentNumber)) {
                        binding.btnNext.text = "Selesai"
                    }
                }
            }
        }

        with(binding) {
            option1.setOnClickListener {
                resetColor()
                currentAnswer = "a"
                option1.setTextColor(getColor(R.color.selected))
            }
            option2.setOnClickListener {
                resetColor()
                currentAnswer = "b"
                option2.setTextColor(getColor(R.color.selected))
            }
            option3.setOnClickListener {
                resetColor()
                currentAnswer = "c"
                option3.setTextColor(getColor(R.color.selected))
            }
            option4.setOnClickListener {
                resetColor()
                currentAnswer = "d"
                option4.setTextColor(getColor(R.color.selected))
            }
        }
    }

    private fun resetColor() {
        with(binding) {
            option1.setTextColor(getColor(R.color.black))
            option2.setTextColor(getColor(R.color.black))
            option3.setTextColor(getColor(R.color.black))
            option4.setTextColor(getColor(R.color.black))
        }
    }
}