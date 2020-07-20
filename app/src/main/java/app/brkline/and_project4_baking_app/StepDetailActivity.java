package app.brkline.and_project4_baking_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.brkline.and_project4_baking_app.databinding.ActivityStepDetailBinding;
import app.brkline.and_project4_baking_app.models.Recipe;
import app.brkline.and_project4_baking_app.models.RecipeStep;


// Need to read over this as it provides an example that may help
// https://developer.android.com/guide/components/fragments.html
public class StepDetailActivity extends AppCompatActivity {

    Recipe recipe;
    int recipePosition;
    int stepPosition;
    RecipeStep recipeStep;
    TextView recipeStepDescriptionTextView;
    int numberOfSteps;
    private int currentRecipeStepId;
    private List<RecipeStep> recipeSteps;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

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

//        recipeStepDescriptionTextView.setText(recipeStep.getDescription());
        recipeSteps = recipe.getSteps();
        numberOfSteps = recipeSteps.size() - 1;

//        FragmentManager stepDetailFragmentManager = getSupportFragmentManager();
//        StepDetailFragment stepDetailFragment = StepDetailFragment.newInstance(recipeStep, numberOfSteps);
//        FragmentTransaction stepDetailFragmentTransaction = stepDetailFragmentManager.beginTransaction();
//        stepDetailFragmentTransaction.replace(R.id.activity_step_detail_fl, stepDetailFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (null == savedInstanceState) {
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.STEP_EXTRA, recipeStep);
            bundle.putInt(Constants.STEP_NUMBER_OF_STEPS, numberOfSteps);
            stepDetailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.activity_step_detail_container, stepDetailFragment).commit();
        }

        toolbar = findViewById(R.id.toolbar);
        initToolbar();
//        setSupportActionBar(toolbar);


//        View.OnClickListener prevButtonOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onPrevStepClick(recipeStep);
//            }
//        };
//
//        View.OnClickListener nextButtonOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onNextStepClick(recipeStep);
//            }
//        };
//
//        Button prevButton = findViewById(R.id.activity_step_detail_prev_btn);
//        Button nextButton = findViewById(R.id.activity_step_detail_next_btn);
//        prevButton.setOnClickListener(prevButtonOnClickListener);
//        nextButton.setOnClickListener(nextButtonOnClickListener);
        // Bottom navigation based on Medium post located here:
        // https://medium.com/@suragch/how-to-add-a-bottom-navigation-bar-in-android-958ed728ef6c
//        BottomNavigationView bottomNavigationView = findViewById(R.id.activtity_step_detail_bottom_nav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case (R.id.menu_bottom_nav_prev):
//                        stepPosition = recipeStep.getId();
//                        if (stepPosition > 0) {
//                            gotoStep(recipe.getSteps().get(stepPosition - 1));
//                        } else {
//                            finish();
//                        }
//                        break;
//                    case (R.id.menu_bottom_nav_next):
//                        stepPosition = recipeStep.getId();
//                        if (stepPosition < numberOfSteps - 1) {
//                            gotoStep(recipe.getSteps().get(stepPosition + 1));
//                        } else {
//                            finish();
//                        }
//                        break;
//                }
//                return true;
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void replaceFragmentInActivity(FragmentManager stepDetailFragmentManager, StepDetailFragment stepDetailFragment, int id) {
        FragmentTransaction stepDetailFragmentTransaction = stepDetailFragmentManager.beginTransaction();
        stepDetailFragmentTransaction.replace(id, stepDetailFragment);
        stepDetailFragmentTransaction.commit();
    }

    private void gotoStep(RecipeStep recipeStep) {
        FragmentTransaction recipeStepFragmentTransaction = getSupportFragmentManager().beginTransaction();
        StepDetailFragment recipeStepDetailFragment = StepDetailFragment.newInstance(recipeStep, numberOfSteps);
        recipeStepFragmentTransaction.replace(android.R.id.content, recipeStepDetailFragment);
        recipeStepFragmentTransaction.addToBackStack(null);
        recipeStepFragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putParcelable(Constants.STEP_EXTRA, recipeStep);
        outstate.putInt(Constants.STEP_NUMBER_OF_STEPS, numberOfSteps);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            navigateUpTo(new Intent(StepDetailActivity.this, RecipeDetailActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.step_detail_activity_no_step_text, Toast.LENGTH_SHORT).show();
    }

    public void onPrevStepClick(RecipeStep recipeStep) {
        currentRecipeStepId = recipeStep.getId();
        if (currentRecipeStepId > 0) {
            gotoStep(recipeSteps.get(currentRecipeStepId - 1));
        } else {
            finish();
        }
    }

    public void onNextStepClick(RecipeStep recipeStep) {
        currentRecipeStepId = recipeStep.getId();
        if (currentRecipeStepId < recipeSteps.size() - 1) {
            gotoStep(recipeSteps.get(currentRecipeStepId + 1));
        } else {
            finish();
        }
    }
}