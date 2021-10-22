package com.sandoval.bestworldrecipes.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sandoval.bestworldrecipes.data.models.Result
import com.sandoval.bestworldrecipes.utils.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)