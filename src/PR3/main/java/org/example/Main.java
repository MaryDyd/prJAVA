package org.example;

import java.util.InputMismatchException; // Для обробки виключень введення.
import java.util.Scanner; // Для читання введення користувача.

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(); // Створення об'єкта калькулятора.
        Scanner scanner = new Scanner(System.in); // Ініціалізація сканера для читання введення.

        try {
            // Запитуємо у користувача два числа.
            System.out.print("Введіть перше число: ");
            double num1 = scanner.nextDouble();

            System.out.print("Введіть друге число: ");
            double num2 = scanner.nextDouble();

            // Запитуємо у користувача операцію.
            System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
            String operation = scanner.next();

            double result = 0; // Змінна для збереження результату обчислення.

            // Виконання операції залежно від введеного символу.
            switch (operation) {
                case "+":
                    result = calculator.add(num1, num2); // Додавання.
                    System.out.println("Результат додавання: " + result);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2); // Віднімання.
                    System.out.println("Результат віднімання: " + result);
                    break;
                case "*":
                    result = calculator.multiply(num1, num2); // Множення.
                    System.out.println("Результат множення: " + result);
                    break;
                case "/":
                    result = calculator.divide(num1, num2); // Ділення.
                    System.out.println("Результат ділення: " + result);
                    break;
                case "sqrt":
                    // Виконуємо квадратний корінь тільки з першого числа.
                    result = calculator.sqrt(num1);
                    System.out.println("Квадратний корінь з числа: " + result);
                    break;
                default:
                    System.out.println("Невірна операція."); // Повідомлення про помилковий ввід операції.
            }

        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage()); // Обробка арифметичних помилок.
        } catch (InvalidInputException e) {
            System.out.println("Помилка: " + e.getMessage()); // Обробка помилок, пов'язаних з некоректними введеними даними.
        } catch (InputMismatchException e) {
            System.out.println("Помилка: введено некоректне число."); // Обробка помилок формату числа.
        } finally {
            System.out.println("Операція завершена."); // Завершальне повідомлення.
            scanner.close(); // Закриття сканера.
        }
    }
}
