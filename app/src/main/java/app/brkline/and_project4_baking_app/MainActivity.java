package app.brkline.and_project4_baking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import app.brkline.and_project4_baking_app.adapters.RecipeAdapter;
import app.brkline.and_project4_baking_app.databinding.ActivityMainBinding;
import app.brkline.and_project4_baking_app.models.Recipe;
import app.brkline.and_project4_baking_app.network.ApiInterface;
import app.brkline.and_project4_baking_app.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static final String LOG_TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding activityMainBinding;
    List<Recipe> recipes;
    RecipeAdapter recipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        getRecipes();
    }

    // Code based on the following Medium post:
    // https://medium.com/@sontbv/retrofit-2-for-beginners-creating-an-android-api-client-3c4370e1118
    private void getRecipes() {
        ApiInterface apiInterface = RetrofitInstance.createService(ApiInterface.class);
        Call<List<Recipe>> call = apiInterface.downloadRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    recipes = response.body();
                    if (null != recipes) {
                        setupRecyclerViewAdapter(recipes);
                    }
                } else {
                    Log.e(LOG_TAG, response.message());
                    Toast.makeText(MainActivity.this, "An unexpected error occurred.  Please try your request again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                Toast.makeText(MainActivity.this, "An unexpected error occurred.  Please try your request again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerViewAdapter(List<Recipe> recipes) {
            if (!recipes.isEmpty()) {
                activityMainBinding.mainActivityRecipeEmptyTv.setVisibility(View.GONE);
                activityMainBinding.mainActivityRecipeRv.setVisibility(View.VISIBLE);
                GridLayoutManager recipeGridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                LinearLayoutManager recipeLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recipeLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                activityMainBinding.mainActivityRecipeRv.setLayoutManager(recipeLinearLayoutManager);
                recipeAdapter = new RecipeAdapter(MainActivity.this, recipes);
                activityMainBinding.mainActivityRecipeRv.setAdapter(recipeAdapter);
            } else {
                activityMainBinding.mainActivityRecipeRv.setVisibility(View.GONE);
                activityMainBinding.mainActivityRecipeEmptyTv.setVisibility(View.VISIBLE);
            }
    }


//    private class getRecipesTask extends AsyncTask<String, Void, List<Recipe>> {
//
//        @Override
//        protected List<Recipe> doInBackground(String... strings) {
//
//            if (strings.length == 0) {
//                return null;
//            }
//
//            // Need to use Retrofit here to retrieve the recipes if they aren't
//            // in the database.
//        }
//
//        @Override
//        protected void onPostExecute(List<Recipe> recipes) {
//            if (!recipes.isEmpty()) {
//                activityMainBinding.mainActivityRecipeEmptyTv.setVisibility(View.GONE);
//                RecipeAdapter recipeAdapter = new RecipeAdapter(MainActivity.this, recipes);
//                GridLayoutManager recipeGridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
//                RecyclerView recipeRecyclerView = activityMainBinding.mainActivityRecipeRv;
//                recipeRecyclerView.setLayoutManager(recipeGridLayoutManager);
//                recipeRecyclerView.setVisibility(View.VISIBLE);
//                recipeRecyclerView.setAdapter(recipeAdapter);
//            } else {
//                activityMainBinding.mainActivityRecipeRv.setVisibility(View.GONE);
//                activityMainBinding.mainActivityRecipeEmptyTv.setVisibility(View.VISIBLE);
//            }
//        }
//    }
}