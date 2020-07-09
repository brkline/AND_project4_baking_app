package app.brkline.and_project4_baking_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import app.brkline.and_project4_baking_app.models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        if (null == intent) {
            closeOnError();
        }

        int position = intent.getIntExtra(Constants.RECIPE_POSITION_SELECTED,
                Constants.RECIPE_DEFAULT_POSITION_SELECTED);
        if (position == Constants.RECIPE_DEFAULT_POSITION_SELECTED) {
            // EXTRA_POSITION not found in intent
            closeOnError();
        }

        recipe = intent.getParcelableExtra(Constants.RECIPE_EXTRA_DATA);
        if (null == recipe) {
            closeOnError();
        }

        setTitle(recipe.getName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.recipe_detail_error_message, Toast.LENGTH_SHORT).show();
    }
}