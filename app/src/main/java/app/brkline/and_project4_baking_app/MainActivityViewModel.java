package app.brkline.and_project4_baking_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.brkline.and_project4_baking_app.data.RecipeRepository;
import app.brkline.and_project4_baking_app.models.Recipe;
import app.brkline.and_project4_baking_app.utils.AppExecutors;
//
//public class MainActivityViewModel extends AndroidViewModel {
//
//    private final AppExecutors appExecutors;
//    private LiveData<List<Recipe>> recipes;
//
//    public MainActivityViewModel(@NonNull Application application) {
//        super(application);
//        appExecutors = AppExecutors.getAppExecutors();
//        RecipeRepository recipeRepository = new RecipeRepository(application);
//        recipes = recipeRepository.getAllRecipes();
//    }
//
//    public LiveData<List<Recipe>> getAllRecipes() {
//        return recipes;
//    }
//}
