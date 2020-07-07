package app.brkline.and_project4_baking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import app.brkline.and_project4_baking_app.adapters.RecipeAdapter;
import app.brkline.and_project4_baking_app.databinding.ActivityMainBinding;
import app.brkline.and_project4_baking_app.models.Recipe;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recipeRecyclerView = activityMainBinding.mainActivityRecipeRv;
        RecipeAdapter recipeAdapter = new RecipeAdapter(recipes);
        GridLayoutManager recipeGridLayoutManager = new GridLayoutManager(this, 1);
    }
}