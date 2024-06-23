package com.example.task_trove;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CategoryUtils {
    public static Set<String> getUniqueCategories(List<Task> tasks) {
        Set<String> categories = new HashSet<>();
        for (Task task : tasks) {
            if (task.getCategory() != null) {
                categories.add(task.getCategory());
            }
        }
        return categories;
    }
    public static Set<String> getCategoryItems(List<Task> tasks, String category){
        Set<String> categories = new HashSet<>();
        for (Task task : tasks) {
            if (Objects.equals(task.getCategory(), category)) {
                categories.add(task.getCategory());
            }
        }
        return categories;
    }
}
