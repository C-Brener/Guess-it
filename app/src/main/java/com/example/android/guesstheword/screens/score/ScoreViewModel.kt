package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    private var _score = MutableLiveData<Int>()
    var score: LiveData<Int> = _score

    private var _event = MutableLiveData<Boolean>()
    var event: LiveData<Boolean> = _event

    init {
        Log.i("ScoreViewModel", "Final Score is $finalScore")
        _score.value = finalScore
    }

    fun onPlayAgain(){
        _event.value = true
    }
    fun onPlayAgainCompleto(){
        _event.value = false

    }
}