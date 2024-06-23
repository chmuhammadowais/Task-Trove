package com.example.task_trove;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<String> categoriesList;
    private TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        emptyView = view.findViewById(R.id.empty_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Simulate getting tasks
        List<Task> tasks = HomeFragment.taskList;

        // Get unique categories
        Set<String> uniqueCategories = CategoryUtils.getUniqueCategories(tasks);
        categoriesList = new ArrayList<>(uniqueCategories);

        categoryAdapter = new CategoryAdapter(getContext(), categoriesList, category -> {
            // Handle the click event
            Toast.makeText(getContext(), "Clicked category: " + category, Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(categoryAdapter);
        updateEmptyView();

        categoryAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateEmptyView();
            }
        });
        return view;
    }

    private void updateEmptyView() {
        if (categoryAdapter.getItemCount() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
