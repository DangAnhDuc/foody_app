package com.example.foody_app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody_app.Util.Constants.Companion.RECIPES_TABLE
import com.example.foody_app.model.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}