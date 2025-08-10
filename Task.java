import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int idCounter = 1;
    private final int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(String title, String description, LocalDate dueDate) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public boolean isOverdue() {
        return !isCompleted && dueDate.isBefore(LocalDate.now());
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %d | Title: %s | Due: %s | Status: %s%s",
                id, title, dueDate.format(formatter),
                isCompleted ? "Completed" : "Pending",
                isOverdue() ? " (Overdue!)" : "");
    }
}

