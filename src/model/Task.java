package model;

public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;

    public Task(int id, String title, String description, String status, String priority, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }
    public String getDueDate() { return dueDate; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String toCSV() {
        return "ID: " + id + " | Title: " + title + " | Description: " + description +
               " | Status: " + status + " | Priority: " + priority + " | DueDate: " + dueDate;
    }

    public static Task fromCSV(String line) {
        try {
            String[] parts = line.split("\\|");
            int id = Integer.parseInt(parts[0].split(":")[1].trim());
            String title = parts[1].split(":")[1].trim();
            String description = parts[2].split(":")[1].trim();
            String status = parts[3].split(":")[1].trim();
            String priority = parts[4].split(":")[1].trim();
            String dueDate = parts[5].split(":")[1].trim();
            return new Task(id, title, description, status, priority, dueDate);
        } catch (Exception e) {
            System.out.println("Error parsing task line: " + line);
            return new Task(0, "Invalid", "Parse Failed", "TODO", "Low", "N/A");
        }
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " - " + description +
               " (" + status + ", " + priority + ", Due: " + dueDate + ")";
    }
}
