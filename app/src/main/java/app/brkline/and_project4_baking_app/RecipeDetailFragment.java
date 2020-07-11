package app.brkline.and_project4_baking_app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.brkline.and_project4_baking_app.adapters.RecipeIngredientsAdapter;
import app.brkline.and_project4_baking_app.databinding.FragmentRecipeDetailBinding;
import app.brkline.and_project4_baking_app.models.Ingredient;

public class RecipeDetailFragment extends Fragment {

    private List<Ingredient> ingredients;
    FragmentRecipeDetailBinding fragmentRecipeDetailBinding;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public RecipeDetailFragment(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public static RecipeDetailFragment newInstance() {
        return new RecipeDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentRecipeDetailBinding = FragmentRecipeDetailBinding.inflate(inflater, container, false);
        View view = fragmentRecipeDetailBinding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentRecipeDetailBinding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecipeIngredientsAdapter();
        setupRecipeStepsAdapter();
    }

    private void setupRecipeStepsAdapter() {
        RecyclerView recipeIngredientsRecyclerView = fragmentRecipeDetailBinding.recipeDetailFragmentIngredientsRv;
        final RecipeIngredientsAdapter recipeIngredientsAdapter = new RecipeIngredientsAdapter(getContext(), ing);
        recipeIngredientsRecyclerView.setAdapter(recipeIngredientsAdapter);
        recipeIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ViewCompat.setNestedScrollingEnabled(recipeIngredientsRecyclerView, false);
    }

    private void setupRecipeIngredientsAdapter() {

    }
}