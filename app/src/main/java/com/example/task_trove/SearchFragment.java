package com.example.task_trove;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment {

    private TaskAdapter taskAdapter;
    private List<Task> allTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the task list from HomeFragment
        allTasks = HomeFragment.taskList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        TextInputEditText searchEdt = view.findViewById(R.id.search_edt);

        // Set up RecyclerView with an initially empty list
        taskAdapter = new TaskAdapter(getContext(), new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskAdapter);

        // Set up search functionality
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterTasks(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        return view;
    }

    private void filterTasks(String query) {
        if (query.isEmpty()) {
            taskAdapter.updateTaskList(new ArrayList<>()); // Clear the list if query is empty
        } else {
            List<Task> filteredTasks = allTasks.stream()
                    .filter(task -> task.getName().toLowerCase().contains(query.toLowerCase()) ||
                            task.getDescription().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            taskAdapter.updateTaskList(filteredTasks);
        }
    }
}
