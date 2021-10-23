package com.sandoval.bestworldrecipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sandoval.bestworldrecipes.data.database.dao.RecipesDao
import com.sandoval.bestworldrecipes.data.database.entity.FavoritesEntity
import com.sandoval.bestworldrecipes.data.database.entity.RecipesEntity

//I normal circunstances version should be upgraded everytime a new entity is added to the DB
//as this is a development project if an error occurs this could be fixed by unistalling the app
//and installing it again
@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}