package app.brkline.and_project4_baking_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.R;
import app.brkline.and_project4_baking_app.databinding.RecipeIngredientListItemBinding;
import app.brkline.and_project4_baking_app.models.Ingredient;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientsViewHolder> {

    private final Context context;
    private final List<Ingredient> ingredients;
//    RecipeIngredientListItemBinding ingredientListItemBinding;

    public RecipeIngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        ingredientListItemBinding = RecipeIngredientListItemBinding.inflate(layoutInflater, parent, false);
//        return new IngredientsViewHolder(ingredientListItemBinding);
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_ingredient_list_item, parent, false);
        return new IngredientsViewHolder(view);
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientTextView;
        TextView quantityTextView;
        TextView measureTextView;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.recipe_ingredient_list_item_ingredient_tv);
            quantityTextView = itemView.findViewById(R.id.recipe_ingredient_list_item_quantity_tv);
            measureTextView = itemView.findViewById(R.id.recipe_ingredient_list_item_measure_tv);
        }

        public void bindIngredients(Ingredient ingredient) {
            quantityTextView.setText(ingredient.getQuantity().toString());
            measureTextView.setText(ingredient.getMeasure());
            ingredientTextView.setText(ingredient.getIngredient());
        }
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.bindIngredients(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == ingredients) {
            return 0;
        } else {
            return ingredients.size();
        }

    }
}
