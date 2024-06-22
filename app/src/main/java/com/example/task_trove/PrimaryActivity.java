package com.example.task_trove;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PrimaryActivity extends AppCompatActivity {
    ImageView home_btn, categories_btn, search_btn, analytics_btn, account_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        home_btn = findViewById(R.id.home_btn);
        categories_btn = findViewById(R.id.categories_btn);
        search_btn = findViewById(R.id.search_btn);
        analytics_btn = findViewById(R.id.analytics_btn);
        account_btn = findViewById(R.id.account_btn);

        // Set the HomeFragment as the default fragment
        if (savedInstanceState == null) {
            setFragment(new HomeFragment());
        }

        // Set up button click listeners
        home_btn.setOnClickListener(v -> setFragment(new HomeFragment()));
        categories_btn.setOnClickListener(v -> setFragment(new CategoriesFragment()));
        search_btn.setOnClickListener(v -> setFragment(new SearchFragment()));
        analytics_btn.setOnClickListener(v -> setFragment(new AnalyticsFragment()));
        account_btn.setOnClickListener(v -> setFragment(new AccountFragment()));
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
