package com.example.foody_app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody_app.Util.Constants.Companion.FAVORITES_RECIPES_TABLE
import com.example.foody_app.model.Result

@Entity(tableName = FAVORITES_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
) {

}