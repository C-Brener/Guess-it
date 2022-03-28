
package com.example.android.guesstheword.screens.game.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.example.android.guesstheword.screens.game.viewModel.GameViewModel

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel


    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.scoreLiveData.observe(viewLifecycleOwner, Observer { scoreText ->
            binding.scoreText.text = scoreText.toString()

        })
        viewModel.wordLiveData.observe(viewLifecycleOwner, Observer { nextWord ->
            binding.wordText.text = nextWord.toString()

        })
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer{
            if (it){
                val currentPoints = viewModel.scoreLiveData.value?:0
                gameFinished(currentPoints)
            }
        })
        viewModel.currentTime.observe(viewLifecycleOwner, Observer {
            binding.timerText.text = viewModel.currentTime.value.toString()
        })

        return binding.root

    }

    /**
     * Resets the list of words and randomizes the order
     */


    /**
     * Called when the game is finished
     */
    private fun gameFinished(currentPoints:Int) {
        val action = GameFragmentDirections.actionGameToScore(currentPoints)
        findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }

    /**
     * Moves to the next word in the list
     */

    /** Methods for updating the UI **/


}
