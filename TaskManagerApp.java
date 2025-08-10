import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManagerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        System.out.println("📋 Welcome to Smart Task Manager");
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> handleAddTask();
                case 2 -> manager.viewTasks();
                case 3 -> handleUpdateTask();
                case 4 -> handleDeleteTask();
                case 5 -> handleCompleteTask();
                case 6 -> {
                    System.out.println("👋 Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \n------ Task Manager Menu ------
                1. Add Task
                2. View All Tasks
                3. Update Task
                4. Delete Task
                5. Mark Task as Completed
                6. Exit
                ------------------------------""");
    }

    private static void handleAddTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        System.out.print("Enter task description (optional): ");
        String description = scanner.nextLine().trim();

        LocalDate dueDate = getValidDate("Enter due date (dd-MM-yyyy): ");
        if (dueDate == null) return;

        manager.addTask(title, description, dueDate);
    }

    private static void handleUpdateTask() {
        System.out.print("Enter task ID or title to update: ");
        String input = scanner.nextLine();
        Task task = manager.findTaskByIdOrTitle(input);
        if (task == null) {
            System.out.println("❌ Task not found.");
            return;
        }

        System.out.print("Enter new title (leave blank to keep current): ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter new description (leave blank to keep current): ");
        String description = scanner.nextLine().trim();

        LocalDate dueDate = getOptionalDate("Enter new due date (dd-MM-yyyy) or leave blank: ");

        manager.updateTask(task, title.isEmpty() ? null : title, description.isEmpty() ? null : description, dueDate);
    }

    private static void handleDeleteTask() {
        System.out.print("Enter task ID or title to delete: ");
        String input = scanner.nextLine();
        Task task = manager.findTaskByIdOrTitle(input);
        if (task == null) {
            System.out.println("❌ Task not found.");
            return;
        }
        manager.deleteTask(task);
    }

    private static void handleCompleteTask() {
        System.out.print("Enter task ID or title to mark as completed: ");
        String input = scanner.nextLine();
        Task task = manager.findTaskByIdOrTitle(input);
        if (task == null) {
            System.out.println("❌ Task not found.");
            return;
        }
        manager.markTaskComplete(task);
    }

    private static LocalDate getValidDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Use dd-MM-yyyy.");
            }
        }
    }

    private static LocalDate getOptionalDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) return null;
        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format.");
            return null;
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}
