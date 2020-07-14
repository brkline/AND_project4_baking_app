package app.brkline.and_project4_baking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.brkline.and_project4_baking_app.models.Recipe;
import app.brkline.and_project4_baking_app.models.RecipeStep;

public class StepDetailActivity extends AppCompatActivity {

    Recipe recipe;
    int recipePosition;
    int stepPosition;
    RecipeStep recipeStep;
    TextView recipeStepDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        recipeStepDescriptionTextView = findViewById(R.id.activity_step_detail_step_description_tv);
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

        recipeStep = intent.getParcelableExtra(Constants.STEP_EXTRA);
        if (null == recipeStep) {
            closeOnError();
        }

        // Setup our ingredients RecyclerView
        setTitle(recipe.getName());

        recipeStepDescriptionTextView.setText(recipeStep.getDescription());


        // Bottom navigation based on Medium post located here:
        // https://medium.com/@suragch/how-to-add-a-bottom-navigation-bar-in-android-958ed728ef6c
        BottomNavigationView bottomNavigationView = findViewById(R.id.activtity_step_detail_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case (R.id.menu_bottom_nav_prev):
                        break;
                    case (R.id.menu_bottom_nav_next):
                        break;
                }
                return true;
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        super.onBackPressed();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(StepDetailActivity.this, RecipeDetailActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.recipe_detail_no_recipe, Toast.LENGTH_SHORT).show();
    }
}