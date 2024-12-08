package org.example;

import org.example.StateMachine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;

public class StateMachineTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testStateMachine(String input, StateMachine.State expectedState) {
        StateMachine machine = new StateMachine();
        StateMachine.State result = machine.processString(input);
        assertEquals(expectedState, result);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("F", StateMachine.State.S),
                Arguments.of("TF", StateMachine.State.S),
                Arguments.of("TEF", StateMachine.State.S),
                Arguments.of("TESF", StateMachine.State.S),

                Arguments.of("T", StateMachine.State.ONE),
                Arguments.of("TE", StateMachine.State.TWO),
                Arguments.of("TES", StateMachine.State.THREE),

                Arguments.of("TEST", StateMachine.State.F),
                Arguments.of("TESTk", StateMachine.State.F),

                Arguments.of("", StateMachine.State.S),

                Arguments.of("TESTEST", StateMachine.State.F),
                Arguments.of("TTEST", StateMachine.State.F)
        );
    }
}