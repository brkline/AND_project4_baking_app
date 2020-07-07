package app.brkline.and_project4_baking_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.databinding.RecipeListItemBinding;
import app.brkline.and_project4_baking_app.models.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecipeListItemBinding recipeListItemBinding = RecipeListItemBinding.inflate(layoutInflater, parent, false);
        return new RecipeViewHolder(recipeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bindRecipe(recipe);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecipeListItemBinding recipeListItemBinding;

        public RecipeViewHolder(RecipeListItemBinding recipeListItemBinding) {
            super(recipeListItemBinding.getRoot());
            this.recipeListItemBinding = recipeListItemBinding;
        }

        void bindRecipe(Recipe recipe) {
            recipeListItemBinding.recipeListItemRecipeTitleTv.setText(recipe.getName());
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = recipes.get(getAdapterPosition());
        }
    }
}
