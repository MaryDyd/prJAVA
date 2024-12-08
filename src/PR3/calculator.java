package PR3;

// Клас виключення для обробки некоректного вводу користувача.
class InvalidInputException extends Exception {
    // Конструктор приймає повідомлення про помилку.
    public InvalidInputException(String message) {
        super(message); // Передаємо повідомлення у базовий клас Exception.
    }
}

// Клас калькулятора, що реалізує базові математичні операції.
class Calculator {
    // Метод для додавання двох чисел.
    public double add(double a, double b) {
        return a + b; // Повертає суму a і b.
    }

    // Метод для віднімання двох чисел.
    public double subtract(double a, double b) {
        return a - b; // Повертає різницю a і b.
    }

    // Метод для множення двох чисел.
    public double multiply(double a, double b) {
        return a * b; // Повертає добуток a і b.
    }

    // Метод для ділення двох чисел.
    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) { // Перевірка на ділення на нуль.
            throw new ArithmeticException("Ділення на нуль неможливе."); // Генерація виключення.
        }
        return a / b; // Повертає результат ділення a на b.
    }

    // Метод для обчислення квадратного кореня.
    public double sqrt(double a) throws InvalidInputException {
        if (a < 0) { // Перевірка на від'ємне значення.
            throw new InvalidInputException("Неможливо знайти корінь з від'ємного числа."); // Генерація власного виключення.
        }
        return Math.sqrt(a); // Використання методу Math.sqrt для обчислення кореня.
    }
}
