package com.example.task_trove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private final Context context;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskDescription.setText(task.getDescription());
        holder.taskDate.setText(task.getDate());
        holder.taskProgress.setProgress(task.getStatus());

        holder.completeButton.setOnClickListener(v -> {
            task.setStatus(100);
            holder.taskProgress.setProgress(100);
            removeItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    private void removeItem(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, taskList.size());
        notifyDataSetChanged(); // Notify adapter to refresh the data
    }

    public void updateTaskList(List<Task> filteredTasks) {
        this.taskList = filteredTasks;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, taskDescription, taskDate;
        LinearProgressIndicator taskProgress;
        ImageView completeButton;
        CardView cardView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskDescription = itemView.findViewById(R.id.task_description);
            taskDate = itemView.findViewById(R.id.task_date);
            taskProgress = itemView.findViewById(R.id.task_progress);
            completeButton = itemView.findViewById(R.id.task_complete_button);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
