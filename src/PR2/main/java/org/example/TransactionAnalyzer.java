package org.example; // Пакет, до якого належить клас TransactionAnalyzer.

import java.util.Comparator; // Для порівняння об'єктів при сортуванні.
import java.util.List; // Для роботи зі списками.
import java.time.LocalDate; // Для роботи з датами.
import java.time.format.DateTimeFormatter; // Для форматування дат.
import java.util.stream.Collectors; // Для збору результатів у список.

public abstract class TransactionAnalyzer { // Абстрактний клас для аналізу транзакцій.

    // Метод для розрахунку загального балансу.
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0; // Початковий баланс.
        for (Transaction transaction : transactions) { // Проходимося по кожній транзакції.
            balance += transaction.getAmount(); // Додаємо суму транзакції до балансу.
        }
        return balance; // Повертаємо загальний баланс.
    }

    // Метод для підрахунку кількості транзакцій у певному місяці та році.
    public static int countTransactionsByMonth(String monthYear, List<Transaction> transactions) {
        int count = 0; // Лічильник транзакцій.
        for (Transaction transaction : transactions) { // Проходимося по кожній транзакції.
            // Конвертація дати транзакції у формат LocalDate.
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            // Отримання місяця та року у форматі MM-yyyy.
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) { // Якщо місяць і рік співпадають.
                count++; // Збільшуємо лічильник.
            }
        }
        return count; // Повертаємо кількість транзакцій.
    }

    // Метод для пошуку топ-10 витрат.
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream() // Створюємо стрім зі списку транзакцій.
                .filter(t -> t.getAmount() < 0) // Фільтруємо лише витрати (від'ємні значення).
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортуємо за сумою (у зростаючому порядку).
                .limit(10) // Вибираємо перші 10 записів.
                .collect(Collectors.toList()); // Збираємо результат у список.
    }

    // Метод для знаходження найбільшої витрати.
    public static Transaction findMaxExpense(List<Transaction> transactions) {
        Transaction maxExpense = null; // Ініціалізуємо змінну для найбільшої витрати.
        for (Transaction transaction : transactions) { // Проходимося по кожній транзакції.
            if (transaction.getAmount() < 0) { // Перевіряємо, чи це витрата.
                if (maxExpense == null || transaction.getAmount() > maxExpense.getAmount()) { // Якщо це найбільша витрата.
                    maxExpense = transaction; // Оновлюємо найбільшу витрату.
                }
            }
        }
        return maxExpense; // Повертаємо найбільшу витрату.
    }

    // Метод для знаходження найменшої витрати.
    public static Transaction findMinExpense(List<Transaction> transactions) {
        Transaction minExpense = null; // Ініціалізуємо змінну для найменшої витрати.
        for (Transaction transaction : transactions) { // Проходимося по кожній транзакції.
            if (transaction.getAmount() < 0) { // Перевіряємо, чи це витрата.
                if (minExpense == null || transaction.getAmount() < minExpense.getAmount()) { // Якщо це найменша витрата.
                    minExpense = transaction; // Оновлюємо найменшу витрату.
                }
            }
        }
        return minExpense; // Повертаємо найменшу витрату.
    }
}
