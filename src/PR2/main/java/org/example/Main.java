package org.example; // Вказівка пакету, в якому знаходиться клас Main.
import java.util.List; // Імпорт класу List для роботи зі списками.

public class Main { // Головний клас програми.
    public static void main(String[] args) { // Точка входу в програму.
        // Шлях до CSV-файлу з транзакціями.
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        // Зчитування транзакцій із файлу за допомогою TransactionCSVReader.
        TransactionCSVReader.readTransactions(filePath);
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath); // Збереження зчитаних транзакцій у список.

        // Обчислення загального балансу за всіма транзакціями.
        TransactionAnalyzer.calculateTotalBalance(transactions);
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions); // Збереження результату обчислення.

        // Генерація та виведення звіту про баланс.
        TransactionReportGenerator.printBalanceReport(totalBalance);

        // Виведення загального балансу в консоль.
        System.out.println("Загальний баланс: " + totalBalance);

        // Визначення кількості транзакцій за вказаний місяць і рік.
        String monthYear = "01-2024"; // Формат місяць-рік.
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear, transactions); // Підрахунок кількості транзакцій.
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount); // Генерація та виведення звіту.

        // Пошук найбільших витрат серед транзакцій.
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions); // Отримання списку топових витрат.
        TransactionReportGenerator.printTopExpensesReport(topExpenses); // Генерація та виведення звіту.

        // Визначення найбільшої та найменшої витрати.
        Transaction maxExpense = TransactionAnalyzer.findMaxExpense(transactions); // Найбільша витрата.
        Transaction minExpense = TransactionAnalyzer.findMinExpense(transactions); // Найменша витрата.
        System.out.println("Найбільша витрата: " + maxExpense); // Виведення найбільшої витрати.
        System.out.println("Найменша витрата: " + minExpense); // Виведення найменшої витрати.

        // Генерація та виведення звіту про витрати за категоріями та місяцями.
        TransactionReportGenerator.printExpenseReportByCategoryAndMonth(transactions);
    }
}
