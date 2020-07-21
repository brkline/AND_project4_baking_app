package app.brkline.and_project4_baking_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.brkline.and_project4_baking_app.R;
import app.brkline.and_project4_baking_app.models.RecipeStep;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {

    private List<RecipeStep> recipeSteps;
    private Context context;
    private RecipeStepClickListener recipeStepClickListener;

    public RecipeStepsAdapter(Context context,
                              List<RecipeStep> recipeSteps,
                              RecipeStepClickListener recipeStepClickListener) {
        this.context = context;
        this.recipeSteps = recipeSteps;
        this.recipeStepClickListener = recipeStepClickListener;
    }

    @NonNull
    @Override
    public RecipeStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeStepsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recipe_steps_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsViewHolder holder, int position) {
        holder.bindSteps(recipeSteps.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == recipeSteps) {
            return 0;
        } else {
            return recipeSteps.size();
        }
    }

    public interface RecipeStepClickListener {
        void onRecipeStepClick(RecipeStep recipeStep);
    }

    class RecipeStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView shortDescriptionTextView;
        ImageButton viewStepDetailImageButton;

        public RecipeStepsViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDescriptionTextView = itemView.findViewById(R.id.recipe_steps_list_item_short_description_tv);
            viewStepDetailImageButton = itemView.findViewById(R.id.recipe_steps_list_item_view_step_detail_ib);
            viewStepDetailImageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            RecipeStep recipeStep = recipeSteps.get(getAdapterPosition());
            recipeStepClickListener.onRecipeStepClick(recipeStep);
//            Intent intent = new Intent(context, StepDetailActivity.class);
//            intent.putExtra(Constants.STEP_EXTRA, recipeStep);
//            context.startActivity(intent);
        }

        public void bindSteps(RecipeStep recipeStep) {
            shortDescriptionTextView.setText(recipeStep.getShortDescription());
        }
    }
}
