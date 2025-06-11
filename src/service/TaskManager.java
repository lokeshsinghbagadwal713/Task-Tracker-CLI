package service;

import model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void loadTasks(List<Task> loadedTasks) {
        tasks = loadedTasks;
        nextId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getTasksByStatus(String status) {
        return tasks.stream()
                    .filter(task -> task.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
    }

    public void addTask(String title, String description, String priority, String dueDate) {
        tasks.add(new Task(nextId++, title, description, "TODO", priority, dueDate));
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    public boolean updateTask(int id, String title, String description, String priority, String dueDate) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setTitle(title);
                t.setDescription(description);
                t.setPriority(priority);
                t.setDueDate(dueDate);
                return true;
            }
        }
        return false;
    }

    public boolean markComplete(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus("DONE");
                return true;
            }
        }
        return false;
    }
}
