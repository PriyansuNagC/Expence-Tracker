import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(String description, double amount, String category) {
        Expense expense = new Expense(description, amount, category);
        expenses.add(expense);
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        } else {
            System.out.println("Invalid index. No expense removed.");
        }
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public Map<String, Double> getExpensesByCategory() {
        Map<String, Double> expensesByCategory = new HashMap<>();
        for (Expense expense : expenses) {
            expensesByCategory.put(
                    expense.getCategory(),
                    expensesByCategory.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount()
            );
        }
        return expensesByCategory;
    }

    public void listExpenses() {
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Remove Expense");
            System.out.println("3. List Expenses");
            System.out.println("4. Show Total Expenses");
            System.out.println("5. Show Expenses by Category");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.println("+--------------------------------------------------------------------------------+");
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    tracker.addExpense(description, amount, category);
                    System.out.println("Expense added.");
                    System.out.println("+--------------------------------------------------------------------------------+");
                    break;
                case 2:
                    System.out.println("+--------------------------------------------------------------------------------+");
                    System.out.print("Enter the index of the expense to remove: ");
                    int index = scanner.nextInt();
                    tracker.removeExpense(index - 1); // Adjusting for 0-based index
                    System.out.println("Expense removed.");
                    System.out.println("+--------------------------------------------------------------------------------+");
                    break;
                case 3:
                    System.out.println("+--------------------------------------------------------------------------------+");
                    System.out.println("All expenses:");
                    tracker.listExpenses();
                    System.out.println("+--------------------------------------------------------------------------------+");
                    break;
                case 4:
                    System.out.println("+--------------------------------------------------------------------------------+");
                    System.out.println("Total expenses: " + tracker.getTotalExpenses());
                    System.out.println("+--------------------------------------------------------------------------------+");
                    break;
                case 5:
                    System.out.println("+--------------------------------------------------------------------------------+");
                    System.out.println("Expenses by category:");
                    for (Map.Entry<String, Double> entry : tracker.getExpensesByCategory().entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    System.out.println("+--------------------------------------------------------------------------------+");
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
