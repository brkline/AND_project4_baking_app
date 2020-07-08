package app.brkline.and_project4_baking_app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import app.brkline.and_project4_baking_app.Constants;
import app.brkline.and_project4_baking_app.models.Recipe;

//@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase  extends RoomDatabase {

    private static RecipeDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract RecipeDao recipeDao();

    public static RecipeDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (null == INSTANCE) {
                INSTANCE = buildDatabase(context);
            }
            return INSTANCE;
        }
    }

    private static RecipeDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                RecipeDatabase.class,
                Constants.DATABASE_NAME).build();
    }

}
