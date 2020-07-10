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
import app.brkline.and_project4_baking_app.databinding.RecipeIngredientItemBinding;
import app.brkline.and_project4_baking_app.models.Ingredient;

public class RecipeDetailFragment extends Fragment {

    private List<Ingredient> ingredients;
//    FragmentRecipeDetailBinding fragmentRecipeDetailBinding;
//    private RecipeIngredientItemBinding ingredientItemBinding;

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
//        fragmentRecipeDetailBinding = FragmentRecipeDetailBinding.inflate(inflater, container, false);
//        View view = fragmentRecipeDetailBinding.getRoot();
//        return view;
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        fragmentRecipeDetailBinding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecipeIngredientsAdapter();
        setupRecipeStepsAdapter();
    }

    private void setupRecipeStepsAdapter() {
//        RecyclerView recipeIngredientsRecyclerView = fragmentRecipeDetailBinding.recipeDetailFragmentIngredientsRv;
//        final RecipeIngredientsAdapter recipeIngredientsAdapter = new RecipeIngredientsAdapter(getContext(), ingredients);
//        recipeIngredientsRecyclerView.setAdapter(recipeIngredientsAdapter);
//        recipeIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        ViewCompat.setNestedScrollingEnabled(recipeIngredientsRecyclerView, false);
    }

    private void setupRecipeIngredientsAdapter() {
        RecyclerView ingredientRecyclerView = getActivity().findViewById(R.id.recipe_detail_fragment_ingredients_rv);
        final RecipeIngredientsAdapter ingredientsAdapter = new RecipeIngredientsAdapter(ingredients);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ViewCompat.setNestedScrollingEnabled(ingredientRecyclerView, false);
    }
}