package com.example.task_trove;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Task> tasks = HomeFragment.taskList;
        Set<String> uniqueCategories = CategoryUtils.getUniqueCategories(tasks);
        List<String> categoriesList = new ArrayList<>(uniqueCategories);

        // Handle the click event
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categoriesList, this::navigateToHomeFragmentWithCategory);
        recyclerView.setAdapter(categoryAdapter);

        return view;
    }

    private void navigateToHomeFragmentWithCategory(String category) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("categoryFilter", category);
        homeFragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.container, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
