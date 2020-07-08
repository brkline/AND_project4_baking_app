package app.brkline.and_project4_baking_app.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.brkline.and_project4_baking_app.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Code based on the following Medium post:
// https://medium.com/@jacinth9/android-retrofit-2-0-tutorial-89de3c714c63
// and // https://medium.com/@sontbv/retrofit-2-for-beginners-creating-an-android-api-client-3c4370e1118
public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder().create();

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor);

    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createService(Class<T> serviceClass) {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.RECIPE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(serviceClass);
    }
}
