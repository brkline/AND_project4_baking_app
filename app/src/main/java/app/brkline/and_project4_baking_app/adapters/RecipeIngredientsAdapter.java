package app.brkline.and_project4_baking_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.R;
import app.brkline.and_project4_baking_app.databinding.RecipeIngredientItemBinding;
import app.brkline.and_project4_baking_app.databinding.RecipeListItemBinding;
import app.brkline.and_project4_baking_app.models.Ingredient;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;

    public RecipeIngredientsAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecipeIngredientItemBinding ingredientItemBinding = RecipeIngredientItemBinding.inflate(layoutInflater, parent, false);
        return new IngredientViewHolder(ingredientItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.bindRecipe(ingredient);
    }

    @Override
    public int getItemCount() {
        return null == ingredients ?0: ingredients.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        RecipeIngredientItemBinding ingredientItemBinding;

        public IngredientViewHolder(RecipeIngredientItemBinding ingredientItemBinding) {
            super(ingredientItemBinding.getRoot());
            this.ingredientItemBinding = ingredientItemBinding;
//            recipeListItemBinding.getRoot()
//                    .setOnClickListener(view ->{
//                        Intent intent = new Intent(context, Recipe.class);
//                        intent.putExtra(Constants.RECIPE_POSITION_SELECTED, ingredients.get(getAdapterPosition()));
//                        Ingredient ingredient = ingredients.get(getAdapterPosition());
//                        intent.putExtra(Constants.RECIPE_EXTRA, ingredient);
//                        context.startActivity(intent);
//                    });
        }

        void bindRecipe(Ingredient ingredient) {
            ingredientItemBinding.recipeIngredientItemQuantityTv.setText(ingredient.getQuantity().toString());
            ingredientItemBinding.recipeIngredientItemMeasureTv.setText(ingredient.getMeasure());
            ingredientItemBinding.recipeIngredientItemIngredientTv.setText(ingredient.getIngredient());
        }

//        @Override
//        public void onClick(View view) {
//            Ingredient ingredient = ingredients.get(getAdapterPosition());
//        }
    }
}
