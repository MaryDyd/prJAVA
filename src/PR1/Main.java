package PR1; // Визначення пакету, до якого належить клас.

// Імпорти для автоматичної генерації коду, зокрема конструкторів, геттерів і сеттерів.
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// Імпорти для роботи зі списками, сканером і потоками.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main { // Головний клас програми.
    public static void main(String[] args) { // Точка входу в програму.
        Scanner scanner = new Scanner(System.in); // Ініціалізація сканера для вводу даних користувачем.

        // Створення об'єктів категорій.
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення товарів із прив'язкою до категорій.
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        // Створення списку товарів.
        List<Product> productList = List.of(product1, product2, product3);

        // Ініціалізація кошика (Cart) і історії замовлень (OrderHistory).
        Cart cart = new Cart();
        OrderHistory orderHistory = new OrderHistory();

        // Основний цикл програми для взаємодії з користувачем.
        while (true) {
            // Виведення меню для вибору опцій.
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів за назвою");
            System.out.println("8 - Пошук товарів за категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt(); // Зчитування вибору користувача.
            scanner.nextLine(); // Очищення буфера після зчитування числа.

            switch (choice) { // Обробка вибору користувача.
                case 1 -> productList.forEach(System.out::println); // Виведення списку товарів.
                case 2 -> {
                    // Додавання товару до кошика за ID.
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int idToAdd = scanner.nextInt();
                    productList.stream()
                            .filter(product -> product.getId() == idToAdd) // Пошук товару за ID.
                            .findFirst()
                            .ifPresentOrElse(cart::addProduct, // Якщо товар знайдено, додаємо до кошика.
                                    () -> System.out.println("Товар з таким ID не знайдено"));
                }
                case 3 -> {
                    // Видалення товару з кошика за ID.
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int idToRemove = scanner.nextInt();
                    productList.stream()
                            .filter(product -> product.getId() == idToRemove) // Пошук товару за ID.
                            .findFirst()
                            .ifPresentOrElse(cart::removeProduct, // Якщо товар знайдено, видаляємо з кошика.
                                    () -> System.out.println("Товар з таким ID не знайдено в кошику"));
                }
                case 4 -> System.out.println(cart); // Виведення вмісту кошика.
                case 5 -> {
                    // Створення замовлення та очищення кошика.
                    Order order = new Order(cart);
                    orderHistory.addOrder(order); // Додавання замовлення до історії.
                    System.out.println("Замовлення оформлено!");
                    cart.clear(); // Очищення кошика.
                }
                case 6 -> System.out.println(orderHistory); // Виведення історії замовлень.
                case 7 -> {
                    // Пошук товару за назвою.
                    System.out.println("Введіть назву товару для пошуку:");
                    String nameToSearch = scanner.nextLine().toLowerCase();
                    List<Product> foundByName = productList.stream()
                            .filter(product -> product.getName().toLowerCase().contains(nameToSearch)) // Пошук за назвою.
                            .collect(Collectors.toList());
                    if (foundByName.isEmpty()) {
                        System.out.println("Товари з такою назвою не знайдено.");
                    } else {
                        foundByName.forEach(System.out::println); // Виведення знайдених товарів.
                    }
                }
                case 8 -> {
                    // Пошук товару за категорією.
                    System.out.println("Введіть ID категорії для пошуку:");
                    int categoryId = scanner.nextInt();
                    List<Product> foundByCategory = productList.stream()
                            .filter(product -> product.getCategory().getId() == categoryId) // Пошук за категорією.
                            .collect(Collectors.toList());
                    if (foundByCategory.isEmpty()) {
                        System.out.println("Товари з такою категорією не знайдено.");
                    } else {
                        foundByCategory.forEach(System.out::println); // Виведення знайдених товарів.
                    }
                }
                case 0 -> {
                    // Вихід з програми.
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                }
                default -> System.out.println("Невідома опція. Спробуйте ще раз."); // Обробка неправильного вводу.
            }
        }
    }
}

// Клас для опису категорій товарів.
@Data
@AllArgsConstructor // Генерація конструктора з усіма полями.
@NoArgsConstructor  // Генерація конструктора без аргументів.
class Category {
    private int id; // Унікальний ідентифікатор категорії.
    private String name; // Назва категорії.
}

// Клас для опису товарів.
@Data
@AllArgsConstructor // Генерація конструктора з усіма полями.
class Product {
    private int id; // Унікальний ідентифікатор товару.
    private String name; // Назва товару.
    private double price; // Ціна товару.
    private String description; // Опис товару.
    private Category category; // Категорія, до якої належить товар.
}

// Клас для управління кошиком покупця.
@Data
class Cart {
    private final List<Product> products = new ArrayList<>(); // Список товарів у кошику.

    public void addProduct(Product product) { // Додавання товару до кошика.
        products.add(product);
    }

    public void removeProduct(Product product) { // Видалення товару з кошика.
        products.remove(product);
    }

    public double getTotalPrice() { // Обчислення загальної вартості товарів у кошику.
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clear() { // Очищення кошика.
        products.clear();
    }

    @Override
    public String toString() { // Переозначення методу toString для відображення вмісту кошика.
        StringBuilder sb = new StringBuilder("Кошик містить:\n");
        products.forEach(product -> sb.append(product).append("\n"));
        sb.append("Загальна вартість: ").append(getTotalPrice());
        return sb.toString();
    }
}

// Клас для управління історією замовлень.
@Data
class OrderHistory {
    private final List<Order> orders = new ArrayList<>(); // Список замовлень.

    public void addOrder(Order order) { // Додавання нового замовлення до історії.
        orders.add(order);
    }

    @Override
    public String toString() { // Переозначення методу toString для відображення історії замовлень.
        StringBuilder sb = new StringBuilder("Історія замовлень:\n");
        orders.forEach(order -> sb.append(order).append("\n\n"));
        return sb.toString();
    }
}

// Клас для опису замовлення.
@Data
class Order {
    private final List<Product> products; // Список товарів у замовленні.
    private final double totalPrice; // Загальна вартість замовлення.
    private String status; // Статус замовлення (наприклад, "Нове").

    public Order(Cart cart) { // Конструктор для створення замовлення з кошика.
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
    }
}
