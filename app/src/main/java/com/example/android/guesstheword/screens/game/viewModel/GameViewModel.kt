package com.example.android.guesstheword.screens.game.viewModel

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    private val timer: CountDownTimer

//    Live Data

    private var _wordLiveData = MutableLiveData<String>()
    var wordLiveData: LiveData<String> = _wordLiveData

    private var _scoreLiveData = MutableLiveData<Int>()
    val scoreLiveData: LiveData<Int> = _scoreLiveData

    private var _currentTime = MutableLiveData<Long>()
    val currentTimeString = Transformations.map(_currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private var _eventGameFinish = MutableLiveData<Boolean>()
    var eventGameFinish: LiveData<Boolean> = _eventGameFinish



    private lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        _scoreLiveData.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }

        }

        timer.start()
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
            resetList()

        }
        _wordLiveData.value = wordList.removeAt(0)

    }

    fun onSkip() {
        _scoreLiveData.value = (scoreLiveData.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _scoreLiveData.value = (scoreLiveData.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}