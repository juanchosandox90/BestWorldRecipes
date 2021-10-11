package com.sandoval.bestworldrecipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.sandoval.bestworldrecipes.R

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
            }
        }

    }
}