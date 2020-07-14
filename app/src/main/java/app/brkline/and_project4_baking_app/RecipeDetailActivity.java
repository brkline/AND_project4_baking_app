package app.brkline.and_project4_baking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import app.brkline.and_project4_baking_app.adapters.RecipeIngredientsAdapter;
import app.brkline.and_project4_baking_app.adapters.RecipeStepsAdapter;
import app.brkline.and_project4_baking_app.models.Recipe;
import app.brkline.and_project4_baking_app.models.RecipeStep;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeStepsAdapter.RecipeStepClickListener {

    Recipe recipe;
    int recipePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        if (null == intent) {
            closeOnError();
        }

        recipePosition = intent.getIntExtra(Constants.RECIPE_POSITION_SELECTED, Constants.RECIPE_DEFAULT_POSITION_SELECTED);
        if (recipePosition == Constants.RECIPE_DEFAULT_POSITION_SELECTED) {
            closeOnError();
        }

        recipe = intent.getParcelableExtra(Constants.RECIPE_EXTRA);
        if (null == recipe) {
            closeOnError();
        }

        // Setup our ingredients RecyclerView
        setTitle(recipe.getName());
        RecipeIngredientsAdapter recipeIngredientsAdapter = new RecipeIngredientsAdapter(RecipeDetailActivity.this, recipe.getIngredients());
        RecyclerView.LayoutManager recipeIngredientsLayoutManager = new LinearLayoutManager(RecipeDetailActivity.this);
        RecyclerView recipeIngredientsRecyclerView = findViewById(R.id.fragment_ingredient_rv);
        recipeIngredientsRecyclerView.setVisibility(View.VISIBLE);
        recipeIngredientsRecyclerView.setLayoutManager(recipeIngredientsLayoutManager);
        recipeIngredientsRecyclerView.setAdapter(recipeIngredientsAdapter);

        // Setup our steps RecyclerView
        RecipeStepsAdapter recipeStepsAdapter = new RecipeStepsAdapter(RecipeDetailActivity.this, recipe.getSteps(), RecipeDetailActivity.this);
        RecyclerView.LayoutManager recipeStepsLayoutManager = new LinearLayoutManager(RecipeDetailActivity.this);
        RecyclerView recipeStepsRecyclerView = findViewById(R.id.fragment_steps_rv);
        recipeStepsRecyclerView.setVisibility(View.VISIBLE);
        recipeStepsRecyclerView.setLayoutManager(recipeStepsLayoutManager);
        recipeStepsRecyclerView.setAdapter(recipeStepsAdapter);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.recipe_detail_no_recipe, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecipeStepClick(RecipeStep recipeStep) {
        if (null != recipeStep) {
            Intent intent = new Intent(RecipeDetailActivity.this, StepDetailActivity.class);
            intent.putExtra(Constants.STEP_EXTRA, recipeStep);
            intent.putExtra(Constants.RECIPE_EXTRA, recipe);
            intent.putExtra(Constants.RECIPE_POSITION_SELECTED, recipePosition);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.RECIPE_EXTRA, recipe);
        outState.putInt(Constants.RECIPE_POSITION_SELECTED, recipePosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recipe = savedInstanceState.getParcelable(Constants.RECIPE_EXTRA);
        recipePosition = savedInstanceState.getInt(Constants.RECIPE_POSITION_SELECTED);
    }
}