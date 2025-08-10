import java.time.LocalDate;
import java.util.*;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String title, String description, LocalDate dueDate) {
        tasks.add(new Task(title, description, dueDate));
        System.out.println("✅ Task added successfully.");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("❌ No tasks found.");
            return;
        }
        tasks.sort(Comparator.comparing(Task::getDueDate));
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public Task findTaskByIdOrTitle(String input) {
        try {
            int id = Integer.parseInt(input);
            for (Task task : tasks) {
                if (task.getId() == id) return task;
            }
        } catch (NumberFormatException e) {
            for (Task task : tasks) {
                if (task.getTitle().equalsIgnoreCase(input)) return task;
            }
        }
        return null;
    }

    public void updateTask(Task task, String newTitle, String newDesc, LocalDate newDate) {
        if (newTitle != null && !newTitle.isBlank()) task.setTitle(newTitle);
        if (newDesc != null) task.setDescription(newDesc);
        if (newDate != null) task.setDueDate(newDate);
        System.out.println("✅ Task updated successfully.");
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
        System.out.println("✅ Task deleted successfully.");
    }

    public void markTaskComplete(Task task) {
        task.markAsCompleted();
        System.out.println("✅ Task marked as completed.");
    }
}
