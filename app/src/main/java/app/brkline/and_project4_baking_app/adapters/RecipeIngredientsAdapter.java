package app.brkline.and_project4_baking_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.R;
import app.brkline.and_project4_baking_app.databinding.RecipeListItemBinding;
import app.brkline.and_project4_baking_app.models.Ingredient;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;
    private Context context;

    public RecipeIngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecipeListItemBinding recipeListItemBinding = RecipeListItemBinding.inflate(layoutInflater, parent, false);
        return new IngredientViewHolder(recipeListItemBinding);
          // Data Binding inflate example from Mentor - Doesn't seem to work if I am using just ViewBinding.
//        RecipeListItemBinding recipeListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recipe_list_item, parent, false);
//        return new RecipeViewHolder(recipeListItemBinding);
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

        RecipeListItemBinding recipeListItemBinding;

        public IngredientViewHolder(RecipeListItemBinding recipeListItemBinding) {
            super(recipeListItemBinding.getRoot());
            this.recipeListItemBinding = recipeListItemBinding;
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
            if (!ingredient.getImage().isEmpty()) {
                recipeListItemBinding.recipeListItemRecipeIv.setImageResource(R.drawable.chocolate_cake_with_raspberries);
            } else {
                recipeListItemBinding.recipeListItemRecipeIv.setImageResource(R.drawable.chocolate_cake_with_raspberries);
            }
            recipeListItemBinding.recipeListItemRecipeTitleTv.setText(ingredient.getName());
        }

//        @Override
//        public void onClick(View view) {
//            Ingredient ingredient = ingredients.get(getAdapterPosition());
//        }
    }
}
