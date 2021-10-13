package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandoval.bestworldrecipes.viewmodels.MainViewModel
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.adapters.RecipesAdapter
import com.sandoval.bestworldrecipes.utils.Constants.Companion.API_KEY
import com.sandoval.bestworldrecipes.utils.NetworkResult
import com.sandoval.bestworldrecipes.utils.observeOnce
import com.sandoval.bestworldrecipes.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Method to show the place holder shimmer for future usage.
        //view.recipesRecyclerView.showShimmer()
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        setupRecyclerView()
        readDatabase()
        // requestApiData()

        return mView
    }

    private fun setupRecyclerView() {
        mView.recipesRecyclerView.adapter = mAdapter
        mView.recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    /** At first implementation the observer was being called twice, first if DB was empty
     * requestApiData and after that as the observer keep observing, and the DB was not empty
     * anymore, called mAdapter.setData(database[0].foodRecipe), means called the data from the DB
     * and DB entity. So this is solved using a property from observers called ObserveOnce from my
     * Extension Functions. **/

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    // Log.d("RecipesFragment: ", "Read Database Called")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            })
        }
    }

    private fun requestApiData() {
        // Log.d("RecipesFragment: ", "Request Api Data Called")
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let {
                        mAdapter.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                }
            })
        }
    }

    private fun showShimmerEffect() {
        mView.recipesRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        mView.recipesRecyclerView.hideShimmer()
    }
}