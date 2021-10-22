package com.sandoval.bestworldrecipes.ui.fragments.viewpagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.adapters.IngredientsAdapter
import com.sandoval.bestworldrecipes.data.models.Result
import com.sandoval.bestworldrecipes.utils.Constants.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_ingredients.view.*

class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_ingredients, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        setupIngredientsRecyclerView(mView)
        myBundle?.extendedIngredients?.let {
            mAdapter.setData(it)
        }

        return mView
    }

    private fun setupIngredientsRecyclerView(view: View) {
        view.ingredientsRecyclerView.adapter = mAdapter
        view.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}