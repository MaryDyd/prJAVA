package org.example; // Пакет, до якого належить клас TransactionReportGenerator.

import java.util.HashMap; // Для створення словника.
import java.util.List; // Для роботи зі списками.
import java.util.Map; // Для роботи зі словниками.

public abstract class TransactionReportGenerator { // Абстрактний клас для генерації звітів.

    // Метод для друку звіту про загальний баланс.
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance); // Виводимо загальний баланс.
    }

    // Метод для друку кількості транзакцій за місяць і рік.
    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count); // Виводимо кількість транзакцій.
    }

    // Метод для друку 10 найбільших витрат.
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) { // Проходимося по списку витрат.
            System.out.println(expense.getDescription() + ": " + expense.getAmount()); // Виводимо опис і суму витрати.
        }
    }

    // Метод для друку звіту про витрати за категоріями та місяцями.
    public static void printExpenseReportByCategoryAndMonth(List<Transaction> transactions) {
        // Словник для зберігання витрат за категоріями та місяцями.
        Map<String, Map<String, Double>> report = new HashMap<>();

        for (Transaction transaction : transactions) { // Проходимося по кожній транзакції.
            if (transaction.getAmount() < 0) { // Якщо це витрата (від'ємна сума).
                String month = transaction.getDate().substring(3); // Отримуємо місяць і рік у форматі MM-yyyy.
                String description = transaction.getDescription(); // Отримуємо опис витрати.

                // Додаємо категорію в звіт, якщо її ще немає.
                report.putIfAbsent(description, new HashMap<>());
                // Додаємо місяць до категорії, якщо його ще немає.
                report.get(description).putIfAbsent(month, 0.0);
                // Оновлюємо суму витрат у цій категорії за місяць.
                double currentAmount = report.get(description).get(month);
                report.get(description).put(month, currentAmount + Math.abs(transaction.getAmount()));
            }
        }

        // Виведення звіту.
        System.out.println("Звіт про витрати:");
        for (String category : report.keySet()) { // Проходимося по кожній категорії.
            System.out.print(category + ": ");
            for (String month : report.get(category).keySet()) { // Проходимося по кожному місяцю в категорії.
                double amount = report.get(category).get(month); // Отримуємо суму витрат.
                System.out.print(month + " - " + amount + " грн ");
                // Додаємо зірочки для візуалізації (одна зірка на 1000 грн).
                int stars = (int) (amount / 1000);
                System.out.print("(" + "*".repeat(stars) + ") ");
            }
            System.out.println(); // Перехід на новий рядок після кожної категорії.
        }
    }
}
