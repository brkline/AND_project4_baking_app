package app.brkline.and_project4_baking_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.Constants;
import app.brkline.and_project4_baking_app.R;
import app.brkline.and_project4_baking_app.databinding.RecipeListItemBinding;
import app.brkline.and_project4_baking_app.models.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private Context context;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecipeListItemBinding recipeListItemBinding = RecipeListItemBinding.inflate(layoutInflater, parent, false);
        return new RecipeViewHolder(recipeListItemBinding);
          // Data Binding inflate example from Mentor - Doesn't seem to work if I am using just ViewBinding.
//        RecipeListItemBinding recipeListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recipe_list_item, parent, false);
//        return new RecipeViewHolder(recipeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bindRecipe(recipe);
    }

    @Override
    public int getItemCount() {
        return null == recipes?0:recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecipeListItemBinding recipeListItemBinding;

        public RecipeViewHolder(RecipeListItemBinding recipeListItemBinding) {
            super(recipeListItemBinding.getRoot());
            this.recipeListItemBinding = recipeListItemBinding;
            recipeListItemBinding.getRoot()
                    .setOnClickListener(view ->{
                        Intent intent = new Intent(context, Recipe.class);
                        intent.putExtra(Constants.RECIPE_POSITION_SELECTED, recipes.get(getAdapterPosition()));
                        Recipe recipe = recipes.get(getAdapterPosition());
                        intent.putExtra(Constants.RECIPE_EXTRA, recipe);
                        context.startActivity(intent);
                    });
        }

        void bindRecipe(Recipe recipe) {
            if (!recipe.getImage().isEmpty()) {
                recipeListItemBinding.recipeListItemRecipeIv.setImageResource(R.drawable.chocolate_cake_with_raspberries);
            } else {
                recipeListItemBinding.recipeListItemRecipeIv.setImageResource(R.drawable.chocolate_cake_with_raspberries);
            }
            recipeListItemBinding.recipeListItemRecipeTitleTv.setText(recipe.getName());
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = recipes.get(getAdapterPosition());
        }
    }
}
