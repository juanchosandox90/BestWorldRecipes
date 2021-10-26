package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.adapters.FavoriteRecipesAdapter
import com.sandoval.bestworldrecipes.databinding.FragmentFavoritesReceipesBinding
import com.sandoval.bestworldrecipes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesRecipesFragment : Fragment() {

    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: FragmentFavoritesReceipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentFavoritesReceipesBinding.inflate(
            inflater,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        setupFavoriteRecipesRecyclerView(binding.favoritesRecipesRecycler)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupFavoriteRecipesRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}