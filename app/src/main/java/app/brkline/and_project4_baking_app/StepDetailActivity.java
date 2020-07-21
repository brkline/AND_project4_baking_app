package app.brkline.and_project4_baking_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import app.brkline.and_project4_baking_app.databinding.FragmentStepDetailBinding;
import app.brkline.and_project4_baking_app.databinding.ToolbarBinding;
import app.brkline.and_project4_baking_app.models.Recipe;
import app.brkline.and_project4_baking_app.models.RecipeStep;

public class StepDetailActivity extends AppCompatActivity {

    Recipe recipe;
    int recipePosition;
    RecipeStep recipeStep;
    int numberOfSteps;
    private int currentRecipeStepId;
    private List<RecipeStep> recipeSteps;
//    Toolbar toolbar;
    ActivityStepDetailBinding activityStepDetailBinding;
    FragmentStepDetailBinding fragmentStepDetailBinding;
    ToolbarBinding toolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_step_detail);
        activityStepDetailBinding = ActivityStepDetailBinding.inflate(getLayoutInflater());
        View view = activityStepDetailBinding.getRoot();
        toolbarBinding = activityStepDetailBinding.activityStepDetailIncludeToolbar;
        setContentView(view);

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

        recipeSteps = recipe.getSteps();
        numberOfSteps = recipeSteps.size() - 1;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (null == savedInstanceState) {
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.STEP_EXTRA, recipeStep);
            bundle.putInt(Constants.STEP_NUMBER_OF_STEPS, numberOfSteps);
            stepDetailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(
                    activityStepDetailBinding.activityStepDetailContainer.getId(),
                    stepDetailFragment).commit();
        }

        // Setup the toolbar
//        toolbar = findViewById(R.id.activity_step_detail_include_toolbar);
        initToolbar();

        View.OnClickListener prevButtonOnClickListener = v -> onPrevStepClick();

        View.OnClickListener nextButtonOnClickListener = v -> onNextStepClick();

//        Button prevButton = findViewById(R.id.activity_step_detail_prev_btn);
//        Button nextButton = findViewById(R.id.activity_step_detail_next_btn);
        activityStepDetailBinding.activityStepDetailPrevBtn.setOnClickListener(prevButtonOnClickListener);
        activityStepDetailBinding.activityStepDetailNextBtn.setOnClickListener(nextButtonOnClickListener);
        String stepOfTotal = (recipeStep.getId() + 1) + " of " + numberOfSteps;
        activityStepDetailBinding.activityStepDetailStepNumberTv.setText(stepOfTotal);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {

        setSupportActionBar(toolbarBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbarBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void replaceFragmentInActivity(FragmentManager stepDetailFragmentManager, StepDetailFragment stepDetailFragment, int id) {
        FragmentTransaction stepDetailFragmentTransaction = stepDetailFragmentManager.beginTransaction();
        stepDetailFragmentTransaction.replace(id, stepDetailFragment);
        stepDetailFragmentTransaction.commit();
    }

    private void gotoStep(RecipeStep step) {
        currentRecipeStepId = step.getId();
        recipeStep = step;
        FragmentTransaction recipeStepFragmentTransaction = getSupportFragmentManager().beginTransaction();
        StepDetailFragment recipeStepDetailFragment = StepDetailFragment.newInstance(step, numberOfSteps);
        recipeStepFragmentTransaction.replace(
                activityStepDetailBinding.activityStepDetailContainer.getId(),
                recipeStepDetailFragment);
        recipeStepFragmentTransaction.addToBackStack(null);
        recipeStepFragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putInt(Constants.RECIPE_CURRENT_STEP_EXTRA, currentRecipeStepId);
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

    public void onPrevStepClick() {
        currentRecipeStepId = recipeStep.getId();
        if (currentRecipeStepId > 0) {
            gotoStep(recipeSteps.get(currentRecipeStepId - 1));
        } else {
            finish();
        }
    }

    public void onNextStepClick() {
        currentRecipeStepId = recipeStep.getId();
//        int nextStepId = recipeStep.getId() + 1;
        if (currentRecipeStepId < recipeSteps.size() - 1) {
//            int nextStep = currentRecipeStepId + 1;
//            RecipeStep nextRecipeStep = recipeSteps.get(nextStepId);
            gotoStep(recipeSteps.get(currentRecipeStepId + 1));
        } else {
            finish();
        }
    }
}