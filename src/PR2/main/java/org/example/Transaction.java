package org.example; // Вказівка на пакет, до якого належить клас Transaction.

import lombok.AllArgsConstructor; // Імпорт анотації для автоматичного створення конструктора.
import lombok.Data; // Імпорт анотації для автоматичного генерування геттерів, сеттерів, equals, hashCode, toString.

@AllArgsConstructor // Анотація Lombok для автоматичного створення конструктора з усіма полями.
@Data // Анотація Lombok для автоматичного створення геттерів, сеттерів та інших службових методів.
public class Transaction { // Клас, що представляє одну транзакцію.
    private String date; // Поле для зберігання дати транзакції у вигляді рядка.
    private double amount; // Поле для зберігання суми транзакції.
    private String description; // Поле для зберігання опису транзакції.

    // Перевизначення методу toString для зручного виводу об'єкта.
    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
