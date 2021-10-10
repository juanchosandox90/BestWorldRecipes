package com.sandoval.bestworldrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.bestworldrecipes.data.models.FoodRecipe
import com.sandoval.bestworldrecipes.databinding.RecipesRowLayoutBinding
import com.sandoval.bestworldrecipes.data.models.Result
import com.sandoval.bestworldrecipes.utils.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes = emptyList<Result>()

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    fun setData(newData: FoodRecipe) {
        val recipesDiffUtil = RecipesDiffUtil(
            recipes,
            newData.results
        )
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}