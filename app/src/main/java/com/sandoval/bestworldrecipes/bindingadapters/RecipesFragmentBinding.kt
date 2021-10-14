package com.sandoval.bestworldrecipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sandoval.bestworldrecipes.data.database.entity.RecipesEntity
import com.sandoval.bestworldrecipes.data.models.FoodRecipe
import com.sandoval.bestworldrecipes.utils.NetworkResult

class RecipesFragmentBinding {
    companion object {

        @BindingAdapter(
            "android:readApiResponse",
            "android:readDatabase",
            requireAll = true
        )
        @JvmStatic
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiRespose: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (apiRespose is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (apiRespose is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            } else if (apiRespose is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }


        @BindingAdapter(
            "android:readApiResponseText",
            "android:readDatabaseText",
            requireAll = true
        )
        @JvmStatic
        fun errorTextViewVisibility(
            textView: TextView,
            apiRespose: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (apiRespose is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiRespose.message.toString()
            } else if (apiRespose is NetworkResult.Loading) {
                textView.visibility = View.INVISIBLE
            } else if (apiRespose is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }
        }

    }
}