package com.example.android.guesstheword.screens.game.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    
//    Live Data

    private var _wordLiveData = MutableLiveData<String>()
    var wordLiveData : LiveData<String> = _wordLiveData
    
    private var _scoreLiveData = MutableLiveData<Int>()
    var scoreLiveData : LiveData<Int> = _scoreLiveData
    
    
    private lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        _scoreLiveData.value = 0
    }


    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
        } else {
            _wordLiveData.value = wordList.removeAt(0)
        }

    }

    fun onSkip() {
        _scoreLiveData.value = (scoreLiveData.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _scoreLiveData.value = (scoreLiveData.value)?.plus(1)
        nextWord()
    }

}