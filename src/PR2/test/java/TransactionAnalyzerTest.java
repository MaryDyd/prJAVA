import Pr2.main.java.org.example.Transaction;
import Pr2.main.java.org.example.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;


class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        TransactionAnalyzer.calculateTotalBalance(transactions);

        // Виклик методу, який потрібно протестувати
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        TransactionAnalyzer.calculateTotalBalance(transactions);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth("02-2023", transactions);
        int countMar = TransactionAnalyzer.countTransactionsByMonth("03-2023", transactions);

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");

    }

    @Test
    public void testFindTopExpenses() {
        // Створіть тестовий набір транзакцій
        List<Transaction> transactions = List.of(
                new Transaction("01-2024", -5000, "Витрати 1"),
                new Transaction("01-2024", -3000, "Витрати 2"),
                new Transaction("01-2024", -1000, "Витрати 3"),
                new Transaction("01-2024", -1500, "Витрати 4"),
                new Transaction("01-2024", -2000, "Витрати 5")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Перевірте, що знайдено 10 найбільших витрат
        Assertions.assertEquals(5, topExpenses.size());
    }
}
