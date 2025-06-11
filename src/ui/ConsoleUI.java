package ui;

import model.Task;
import service.TaskManager;
import storage.TaskStorage;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final TaskManager manager = new TaskManager();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        manager.loadTasks(TaskStorage.loadTasks());
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addTask();
                case "2" -> updateTask();
                case "3" -> deleteTask();
                case "4" -> markComplete();
                case "5" -> listTasksByStatus();
                case "6" -> listAllTasks();
                case "0" -> {
                    TaskStorage.saveTasks(manager.getAllTasks());
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n==== Task Tracker CLI ====");
        System.out.println("1. Add Task");
        System.out.println("2. Update Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Mark Task as Complete");
        System.out.println("5. List Tasks by Status");
        System.out.println("6. List All Tasks");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private void addTask() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Priority (Low/Medium/High): ");
        String priority = scanner.nextLine();
        System.out.print("Due Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        manager.addTask(title, desc, priority, date);
    }

    private void updateTask() {
        System.out.print("Enter Task ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New Title: ");
        String title = scanner.nextLine();
        System.out.print("New Description: ");
        String desc = scanner.nextLine();
        System.out.print("New Priority: ");
        String priority = scanner.nextLine();
        System.out.print("New Due Date: ");
        String date = scanner.nextLine();
        if (!manager.updateTask(id, title, desc, priority, date))
            System.out.println("Task not found.");
    }

    private void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (!manager.deleteTask(id))
            System.out.println("Task not found.");
    }

    private void markComplete() {
        System.out.print("Enter Task ID to mark complete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (!manager.markComplete(id))
            System.out.println("Task not found.");
    }

    private void listTasksByStatus() {
        System.out.print("Enter status (TODO, IN_PROGRESS, DONE): ");
        String status = scanner.nextLine();
        List<Task> tasks = manager.getTasksByStatus(status);
        if (tasks.isEmpty()) System.out.println("No tasks found.");
        tasks.forEach(System.out::println);
    }

    private void listAllTasks() {
        List<Task> tasks = manager.getAllTasks();
        if (tasks.isEmpty()) System.out.println("No tasks available.");
        tasks.forEach(System.out::println);
    }
}
