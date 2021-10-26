package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.adapters.FavoriteRecipesAdapter
import com.sandoval.bestworldrecipes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorites_receipes.view.*

@AndroidEntryPoint
class FavoritesRecipesFragment : Fragment() {

    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val mView = inflater.inflate(R.layout.fragment_favorites_receipes, container, false)
        setupFavoriteRecipesRecyclerView(mView.favoritesRecipesRecycler)

        mainViewModel.readFavoriteRecipes.observe(viewLifecycleOwner, { favoriteRecipesEntity ->
            mAdapter.setData(favoriteRecipesEntity)
        })

        return mView

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupFavoriteRecipesRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}