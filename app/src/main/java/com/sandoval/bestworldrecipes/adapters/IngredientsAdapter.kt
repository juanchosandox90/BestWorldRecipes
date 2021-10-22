package com.sandoval.bestworldrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.data.models.ExtendedIngredient
import com.sandoval.bestworldrecipes.utils.Constants.Companion.BASE_IMAGE_URL
import com.sandoval.bestworldrecipes.utils.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredients = emptyList<ExtendedIngredient>()

    override fun getItemCount(): Int {
        return ingredients.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredients_row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ingredientImgView.load(BASE_IMAGE_URL + ingredients[position].image) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.itemView.ingredientTitleText.text = ingredients[position].name.capitalize()
        holder.itemView.ingredientAmountText.text = ingredients[position].amount.toString()
        holder.itemView.ingredientUnitText.text = ingredients[position].unit
        holder.itemView.ingredientConsistencyText.text = ingredients[position].consistency
        holder.itemView.ingredientOriginalText.text = ingredients[position].original
    }

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtils = RecipesDiffUtil(
            ingredients, newIngredients
        )
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtils)
        ingredients = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}