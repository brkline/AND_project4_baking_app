package app.brkline.and_project4_baking_app.network;

import java.util.List;

import app.brkline.and_project4_baking_app.Constants;
import app.brkline.and_project4_baking_app.models.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

// Code based on the following Medium post:
// https://medium.com/@sontbv/retrofit-2-for-beginners-creating-an-android-api-client-3c4370e1118
public interface ApiInterface {
    @GET("baking.json")
    Call<List<Recipe>> downloadRecipes();
}
