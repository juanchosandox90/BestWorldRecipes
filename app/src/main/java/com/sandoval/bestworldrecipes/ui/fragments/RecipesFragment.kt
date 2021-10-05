package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandoval.bestworldrecipes.R

class RecipesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Method to show the place holder shimmer for future usage.
        //view.recipesRecyclerView.showShimmer()
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }
}