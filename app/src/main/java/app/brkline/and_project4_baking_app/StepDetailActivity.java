package app.brkline.and_project4_baking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        // Bottom navigation based on Medium post located here:
        // https://medium.com/@suragch/how-to-add-a-bottom-navigation-bar-in-android-958ed728ef6c
        BottomNavigationView bottomNavigationView = findViewById(R.id.activtity_step_detail_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case (R.id.menu_bottom_nav_prev):
                        break;
                    case (R.id.menu_bottom_nav_next):
                        break;
                }
                return true;
            }
        });
    }
}