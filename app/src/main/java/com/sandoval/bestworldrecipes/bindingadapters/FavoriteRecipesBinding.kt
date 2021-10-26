package com.sandoval.bestworldrecipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.bestworldrecipes.adapters.FavoriteRecipesAdapter
import com.sandoval.bestworldrecipes.data.database.entity.FavoritesEntity

class FavoriteRecipesBinding {
    companion object {

        @BindingAdapter(
            "android:viewVibility",
            "android:setData",
            requireAll = false
        )
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            database: List<FavoritesEntity>?,
            mAdapter: FavoriteRecipesAdapter?
        ) {
            if (database.isNullOrEmpty()) {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.VISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            } else {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        mAdapter?.setData(database)
                    }
                }
            }
        }
    }
}