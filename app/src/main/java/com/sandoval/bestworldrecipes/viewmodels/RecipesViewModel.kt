package com.sandoval.bestworldrecipes.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sandoval.bestworldrecipes.data.DataStoreRepository
import com.sandoval.bestworldrecipes.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.sandoval.bestworldrecipes.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.sandoval.bestworldrecipes.utils.Constants.Companion.QUERY_NUMBER
import com.sandoval.bestworldrecipes.utils.Constants.Companion.QUERY_API_KEY
import com.sandoval.bestworldrecipes.utils.Constants.Companion.QUERY_TYPE
import com.sandoval.bestworldrecipes.utils.Constants.Companion.QUERY_DIET
import com.sandoval.bestworldrecipes.utils.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.sandoval.bestworldrecipes.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.sandoval.bestworldrecipes.utils.Constants.Companion.API_KEY
import com.sandoval.bestworldrecipes.utils.Constants.Companion.DEFAULT_RECIPES_NUMBER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    application: Application
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    private fun saveBackOnline(
        backOnline: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveBackOnline(
            backOnline
        )
    }

    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveMealAndDietType(
            mealType, mealTypeId, dietType, dietTypeId
        )
    }

    fun applyQueries(): HashMap<String, String> {

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries

    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No internet connection", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online!", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
}