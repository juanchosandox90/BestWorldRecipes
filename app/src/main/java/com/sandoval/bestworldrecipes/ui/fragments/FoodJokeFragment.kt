package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.databinding.FragmentFoodJokeBinding
import com.sandoval.bestworldrecipes.utils.Constants.Companion.API_KEY
import com.sandoval.bestworldrecipes.utils.NetworkResult
import com.sandoval.bestworldrecipes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    private val mainViewmodel: MainViewModel by viewModels()

    private var _binding: FragmentFoodJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodJokeBinding.inflate(
            inflater,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewmodel

        mainViewmodel.readFoodJoke(API_KEY)
        mainViewmodel.foodJokeResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.foodJokeText.text = response.data?.text
                }
                is NetworkResult.Error -> {
                    loadFoodJokeDataFromCache()
                    showSnackBar(response.message.toString())
                }
                is NetworkResult.Loading -> {
                    Log.d("FoodJokeFragment", "Loading...")
                }
            }
        })

        return binding.root
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).setAction(requireContext().getString(R.string.snackbar_action_okay)) {}
            .show()
    }

    private fun loadFoodJokeDataFromCache() {
        lifecycleScope.launch {
            mainViewmodel.readFoodJoke.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty() && database != null) {
                    binding.foodJokeText.text = database[0].foodJoke.text
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}