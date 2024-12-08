package PR5;

// Клас, що реалізує скінченний автомат (Finite State Machine).
public class FiniteStateMachine {
    // Перелічення станів автомата.
    public enum State {
        S,     // Початковий стан
        ONE,   // Перший проміжний стан
        TWO,   // Другий проміжний стан
        THREE, // Третій проміжний стан
        F      // Фінальний стан
    }

    private State currentState; // Поточний стан автомата.

    // Конструктор, що ініціалізує автомат у початковому стані.
    public FiniteStateMachine() {
        this.currentState = State.S; // Установка початкового стану.
    }

    // Метод для обробки вхідного рядка.
    public void process(String input) {
        // Перебір символів рядка.
        for (char c : input.toCharArray()) {
            // Перехід між станами залежно від поточного стану та символу.
            switch (currentState) {
                case S: // Початковий стан.
                    if (c == 'T') {
                        currentState = State.ONE; // Перехід у стан ONE при отриманні 'T'.
                    }
                    break;
                case ONE: // Стан ONE.
                    if (c == 'E') {
                        currentState = State.TWO; // Перехід у стан TWO при отриманні 'E'.
                    } else if (c == 'T') {
                        currentState = State.ONE; // Залишаємося в стані ONE при повторному 'T'.
                    } else {
                        currentState = State.S; // Повернення у стан S для будь-якого іншого символу.
                    }
                    break;
                case TWO: // Стан TWO.
                    if (c == 'S') {
                        currentState = State.THREE; // Перехід у стан THREE при отриманні 'S'.
                    } else if (c == 'T') {
                        currentState = State.ONE; // Перехід назад у стан ONE при отриманні 'T'.
                    } else {
                        currentState = State.S; // Повернення у стан S для будь-якого іншого символу.
                    }
                    break;
                case THREE: // Стан THREE.
                    if (c == 'T') {
                        currentState = State.F; // Перехід у фінальний стан F при отриманні 'T'.
                    } else if (c == 'T') {
                        currentState = State.ONE; // Перехід у стан ONE при повторному 'T'.
                    } else {
                        currentState = State.S; // Повернення у стан S для будь-якого іншого символу.
                    }
                    break;
                case F: // Фінальний стан.
                    // Залишаємось у стані F, подальший вхідний символ не змінює стан.
                    break;
            }
        }
    }

    // Метод для перевірки, чи знаходиться автомат у фінальному стані.
    public boolean isFinalState() {
        return currentState == State.F; // Повертає true, якщо поточний стан — F.
    }

    // Метод для отримання поточного стану.
    public State getCurrentState() {
        return currentState; // Повертає поточний стан.
    }
}
