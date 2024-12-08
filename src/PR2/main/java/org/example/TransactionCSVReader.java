package PR2.main.java.org.example; // Вказуємо пакет, до якого належить клас TransactionCSVReader.

import java.io.BufferedReader; // Для читання тексту з вхідного потоку.
import java.io.IOException; // Для обробки виключень вводу/виводу.
import java.io.InputStreamReader; // Для перетворення потоку байтів у текстовий.
import java.net.URL; // Для роботи з URL.
import java.util.ArrayList; // Для використання динамічного списку.
import java.util.List; // Для загального інтерфейсу списків.

public abstract class TransactionCSVReader { // Абстрактний клас для читання транзакцій з CSV.

    // Метод для читання транзакцій з CSV-файлу за посиланням.
    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>(); // Ініціалізація списку транзакцій.

        try {
            URL url = new URL(filePath); // Створення URL-об'єкта для завантаження даних.
            // Відкриття потоку для читання з URL.
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line; // Змінна для зберігання поточного рядка.
                while ((line = br.readLine()) != null) { // Читаємо рядок, поки файл не закінчиться.
                    String[] values = line.split(","); // Розділяємо рядок на частини за комами.
                    // Створюємо новий об'єкт Transaction з отриманих значень.
                    Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
                    transactions.add(transaction); // Додаємо транзакцію до списку.
                }
            }
        } catch (IOException e) { // Обробка можливих виключень.
            e.printStackTrace(); // Виведення інформації про помилку.
        }

        return transactions; // Повертаємо список транзакцій.
    }
}
