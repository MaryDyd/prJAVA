package org.example;

public class Main {

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!\n");

        // Testing the StateMachine with an example input
        StateMachine stateMachine = new StateMachine();
        String input = "TETS";
        StateMachine.State finalState = stateMachine.processString(input);

        System.out.println("Final state: " + finalState);

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}

class StateMachine {
    private State currentState;

    public enum State {
        S, ONE, TWO, THREE, F
    }

    public StateMachine() {
        this.currentState = State.S;
    }

    public State processString(String input) {
        for (char c : input.toCharArray()) {
            processChar(c);
        }
        return currentState;
    }

    private void processChar(char c) {
        switch (currentState) {
            case S:
                if (c == 'T') {
                    currentState = State.ONE;
                }
                break;
            case ONE:
                if (c == 'E') {
                    currentState = State.TWO;
                } else if (c == 'T') {
                    currentState = State.ONE;
                } else {
                    currentState = State.S;
                }
                break;
            case TWO:
                if (c == 'S') {
                    currentState = State.THREE;
                } else if (c == 'T') {
                    currentState = State.ONE;
                } else {
                    currentState = State.S;
                }
                break;
            case THREE:
                if (c == 'T') {
                    currentState = State.F;
                } else {
                    currentState = State.S;
                }
                break;
        }
    }
}
