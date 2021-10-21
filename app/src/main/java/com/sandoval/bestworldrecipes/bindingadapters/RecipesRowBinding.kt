package com.sandoval.bestworldrecipes.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.data.models.Result
import com.sandoval.bestworldrecipes.ui.fragments.RecipesFragmentDirections
import org.jsoup.Jsoup
import java.lang.Exception

class RecipesRowBinding {

    companion object {

        @BindingAdapter("android:setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(
            textView: TextView,
            likes: Int
        ) {
            textView.text = likes.toString()
        }


        @BindingAdapter("android:setMinutes")
        @JvmStatic
        fun setMinutes(
            textView: TextView,
            minutes: Int
        ) {
            textView.text = minutes.toString()
        }

        @BindingAdapter("android:setVeganColor")
        @JvmStatic
        fun setVeganColor(
            view: View,
            vegan: Boolean
        ) {
            if (vegan) {
                when (view) {
                    is TextView ->
                        view.setTextColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    is ImageView ->
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                }
            }
        }

        @BindingAdapter("android:loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(
            imageView: ImageView,
            imageUrl: String
        ) {

            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("android:onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result) {
            recipeRowLayout.setOnClickListener {
                try {
                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.e("Exception: ", e.toString())
                }
            }
        }

        @BindingAdapter("android:parseHtmlDesc")
        @JvmStatic
        fun parseHtml(textView: TextView, description: String?) {
            if (description != null) {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }
        }

    }
}