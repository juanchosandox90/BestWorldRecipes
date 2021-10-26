package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.sandoval.bestworldrecipes.R

class FavoritesReceipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_favorites_receipes, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}