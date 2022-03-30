
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

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer{ eventGameFinish ->
            if (eventGameFinish){
                val currentPoints = viewModel.scoreLiveData.value?:0
                gameFinished(currentPoints)
            }
        })



        return binding.root

    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished(currentPoints:Int) {
        val action = GameFragmentDirections.actionGameToScore(currentPoints)
        findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }

}
