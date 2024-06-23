package com.example.task_trove;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView emptyView;
    private TaskAdapter taskAdapter;
    private ImageView add_btn;
    public static List<Task> taskList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        emptyView = view.findViewById(R.id.empty_view);
        add_btn = view.findViewById(R.id.add_btn);

        add_btn.setOnClickListener(v->{
            showAddTaskDialog();
        });

        taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", "Description 1", "2024-06-01","Hobby", 50));
        taskList.add(new Task("Task 2", "Description 2", "2024-06-02","Work", 10));
        taskList.add(new Task("Task 3", "Description 3", "2024-06-03", "Gym",40));
        taskList.add(new Task("Task 4", "Description 4", "2024-06-03","None", 50));
        taskList.add(new Task("Task 5", "Description 5", "2024-06-03","None", 40));
        taskAdapter = new TaskAdapter(getContext(), taskList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskAdapter);

        updateEmptyView();

        taskAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateEmptyView();
            }
        });

        return view;
    }

    private void updateEmptyView() {
        if (taskAdapter.getItemCount() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add New Task");

        // Inflate the custom layout for the dialog
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.add_task, null);
        builder.setView(dialogView);

        EditText editTaskName = dialogView.findViewById(R.id.edit_task_name);
        EditText editTaskDescription = dialogView.findViewById(R.id.edit_task_description);
        EditText editTasCategory = dialogView.findViewById(R.id.edit_task_category);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskName = editTaskName.getText().toString().trim();
                String taskDescription = editTaskDescription.getText().toString().trim();
                String taskCategory = editTasCategory.getText().toString();
                String taskDate = "";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    taskDate = String.valueOf(LocalDate.now());
                }
                // Validate input if needed
                if (!taskName.isEmpty() && !taskDescription.isEmpty()) {
                    // Create a new Task object or add it to your list
                    Task newTask = null;
                    if(!taskCategory.isEmpty()){
                        newTask = new Task(taskName, taskDescription, taskDate,taskCategory, 0);
                    }
                    else{
                        newTask = new Task(taskName, taskDescription, taskDate, 0);
                    }

                    // Add newTask to your RecyclerView adapter's dataset
                    taskList.add(newTask);
                    taskAdapter.notifyDataSetChanged(); // Notify adapter of data change

                    Toast.makeText(requireContext(), "Task added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Please enter task name and description", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
