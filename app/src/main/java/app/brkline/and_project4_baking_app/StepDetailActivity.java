package app.brkline.and_project4_baking_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import app.brkline.and_project4_baking_app.databinding.ActivityStepDetailBinding;
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

    ActivityStepDetailBinding activityStepDetailBinding;
    ToolbarBinding toolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        initToolbar();

        View.OnClickListener prevButtonOnClickListener = v -> onPrevStepClick();

        View.OnClickListener nextButtonOnClickListener = v -> onNextStepClick();

        activityStepDetailBinding.activityStepDetailPrevBtn.setOnClickListener(prevButtonOnClickListener);
        activityStepDetailBinding.activityStepDetailNextBtn.setOnClickListener(nextButtonOnClickListener);
        String stepOfTotal = (recipeStep.getId() + 1) + " of " + numberOfSteps;
        activityStepDetailBinding.activityStepDetailStepNumberTv.setText(stepOfTotal);
        int screenOrientation = getResources().getConfiguration().orientation;
        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideUi();
        }
    }

    private void hideUi() {
        activityStepDetailBinding.activityStepDetailBottomBarCl.setVisibility(View.GONE);
        toolbarBinding.toolbar.setVisibility(View.INVISIBLE);
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

    // When we go to our next step, we need to make sure we set recipeStep since the
    // onCreate method in this activity is not executed when we just replace the fragment.
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

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.step_detail_activity_no_step_text, Toast.LENGTH_SHORT).show();
    }

    // For our prev and next click, we are going to move through the steps
    // until we are either at the beginning or end.  If we reach either,
    // then we'll just end the activity.
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
        if (currentRecipeStepId < recipeSteps.size() - 1) {
            gotoStep(recipeSteps.get(currentRecipeStepId + 1));
        } else {
            finish();
        }
    }
}