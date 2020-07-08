package app.brkline.and_project4_baking_app.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import app.brkline.and_project4_baking_app.models.Recipe;

@Dao
public interface RecipeDao {

//    @Query("SELECT * FROM recipe")
//    LiveData<List<Recipe>> getAllRecipes();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void bulkInsertRecipe(List<Recipe> recipes);
//
//    @Query("DELETE FROM recipe")
//    void deleteAllRecipes();
}
