package PR4; // Оголошення пакету, в якому знаходиться клас Main

import com.github.javafaker.Faker; // Імпорт бібліотеки Faker для генерації фейкових даних
import java.util.List; // Імпорт інтерфейсу List для роботи зі списками
import java.util.ArrayList; // Імпорт класу ArrayList для створення списків
import java.util.stream.Collectors; // Імпорт Stream API для роботи з потоками

// Основний клас програми
public class Main {
    public static void main(String[] args) {
        // Створюємо об'єкт Faker для генерації фейкових даних
        Faker faker = new Faker();

        // Створюємо список товарів
        List<Product> products = new ArrayList<>();
        products.add(generateRandomProduct("electronics", faker)); // Додаємо випадковий товар категорії "електроніка"
        products.add(generateRandomProduct("clothing", faker)); // Додаємо випадковий товар категорії "одяг"

        // Використовуємо Stream API для обробки замовлень
        products.stream()
                .map(OrderProcessor::new) // Для кожного товару створюємо об'єкт OrderProcessor
                .forEach(OrderProcessor::process); // Викликаємо метод process для обробки замовлення

        // Альтернативний спосіб обробки замовлень за допомогою лямбда-виразів
        products.forEach(product -> new OrderProcessor<>(product).process()); // Створюємо обробник і одразу обробляємо товар
    }

    // Метод для генерації випадкового товару на основі заданої категорії
    public static Product generateRandomProduct(String category, Faker faker) {
        switch (category) { // Перевіряємо категорію товару
            case "electronics": // Якщо категорія "електроніка"
                return new Electronics(faker.commerce().productName(), faker.commerce().price()); // Повертаємо об'єкт Electronics
            case "clothing": // Якщо категорія "одяг"
                return new Clothing(faker.commerce().productName(), faker.commerce().price()); // Повертаємо об'єкт Clothing
            default: // У разі невідомої категорії
                throw new IllegalArgumentException("Unknown category: " + category); // Генеруємо виняток
        }
    }
}

// Абстрактний клас Product, що описує базові властивості товару
abstract class Product {
    private String name; // Назва товару
    private double price; // Ціна товару

    // Конструктор для ініціалізації назви та ціни
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Метод для отримання назви товару
    public String getName() {
        return name;
    }

    // Метод для отримання ціни товару
    public double getPrice() {
        return price;
    }
}

// Клас Electronics, що представляє товари категорії "електроніка"
class Electronics extends Product {
    public Electronics(String name, double price) { // Конструктор класу
        super(name, price); // Виклик конструктора батьківського класу
    }
}

// Клас Clothing, що представляє товари категорії "одяг"
class Clothing extends Product {
    public Clothing(String name, double price) { // Конструктор класу
        super(name, price); // Виклик конструктора батьківського класу
    }
}

// Клас для обробки замовлень з використанням узагальнень
class OrderProcessor<T extends Product> {
    private T product; // Поле для збереження товару

    // Конструктор для ініціалізації товару
    public OrderProcessor(T product) {
        this.product = product;
    }

    // Метод для обробки замовлення
    public void process() {
        System.out.println("Processing order for: " + product.getName() + " with price: " + product.getPrice());
        // Виводимо в консоль інформацію про оброблюваний товар
    }
}
