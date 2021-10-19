package com.sandoval.bestworldrecipes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.sandoval.bestworldrecipes.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.sandoval.bestworldrecipes.viewmodels.RecipesViewModel
import kotlinx.android.synthetic.main.recipes_bottom_sheet.view.*
import java.lang.Exception
import java.util.*


class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel
    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView = inflater.inflate(R.layout.recipes_bottom_sheet, container, false)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, mView.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, mView.dietTypeChipGroup)
        })

        mView.mealTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().lowercase(Locale.getDefault())
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId
        }

        mView.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedDietTypeChip = chip.text.toString().lowercase(Locale.getDefault())
            dietTypeChip = selectedDietTypeChip
            dietTypeChipId = selectedChipId
        }

        mView.applyButton.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
        }

        return mView
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                Log.e("Recipes Bottom Sheet: ", e.message.toString())
            }

        }
    }
}