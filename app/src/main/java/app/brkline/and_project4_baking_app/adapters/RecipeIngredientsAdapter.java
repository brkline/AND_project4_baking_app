package app.brkline.and_project4_baking_app.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.models.Ingredient;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    private final Context context;
    private final List<Ingredient> ingredients;

    public RecipeIngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
