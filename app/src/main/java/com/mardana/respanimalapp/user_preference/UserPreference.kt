package com.mardana.respanimalapp.user_preference

import android.content.Context
import com.mardana.respanimalapp.data.ScoreModel
import com.mardana.respanimalapp.utils.generateRandomId

class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_preference"
        private const val USER_UID = "user_uid"
        private const val USER_NAME = "user_name"
        private const val USER_ROOM = "user_room"
        private const val USER_SCORE = "user_score"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun loginUser(
        name: String,
        room: String,
    ) {
        val editor = preference.edit()
        editor.putString(USER_UID, generateRandomId())
        editor.putString(USER_NAME, name)
        editor.putString(USER_ROOM, room)
        editor.apply()
    }

    fun inputScore(score: String) {
        val editor = preference.edit()
        editor.putString(USER_SCORE, score)
        editor.apply()
    }

    fun getUserData(): ScoreModel {
        val uid = preference.getString(USER_UID, null)
        val name = preference.getString(USER_NAME, null)
        val room = preference.getString(USER_ROOM, null)
        val score = preference.getString(USER_SCORE, null)
        return ScoreModel(
            uid = uid,
            name = name,
            room = room,
            score = score
        )
    }

    fun logoutUser() {
        val editor = preference.edit()
        with(editor) {
            remove(USER_UID)
            remove(USER_NAME)
            remove(USER_ROOM)
            remove(USER_SCORE)
        }
        editor.apply()
    }
}