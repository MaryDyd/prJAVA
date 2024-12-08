package PR5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class Main {

    @ParameterizedTest
    @CsvSource({
            "abcTESTabc, F",
            "abcTES, THREE",
            "TEST, F",
            "TTEST, F",
            "TES, THREE",
            "abcd, S",
            "TE, TWO",
            "TTTESTT, F",
            "abcTTESTabc, F",
            "T, ONE",
            "TE, TWO",
            "TES, THREE",
            "TEST, F",
            "TTESTT, F",
            "TESTTEST, F"
    })
    void testFiniteStateMachine(String input, String expectedState) {
        FiniteStateMachine fsm = new FiniteStateMachine();
        fsm.process(input);
        assertEquals(expectedState, fsm.getCurrentState().name());
    }

    @Test
    void testFinalState() {
        FiniteStateMachine fsm = new FiniteStateMachine();
        fsm.process("abcTESTabc");
        assertTrue(fsm.isFinalState());

        fsm = new FiniteStateMachine();
        fsm.process("abcTES");
        assertFalse(fsm.isFinalState());
    }

    @Test
    void testEdgeCases() {
        FiniteStateMachine fsm = new FiniteStateMachine();
        fsm.process("TTEST");
        assertTrue(fsm.isFinalState(), "Automaton should recognize 'TTEST'");

        fsm = new FiniteStateMachine();
        fsm.process("TESTTEST");
        assertTrue(fsm.isFinalState(), "Automaton should recognize 'TESTTEST'");

        fsm = new FiniteStateMachine();
        fsm.process("TESSTEST");
        assertTrue(fsm.isFinalState(), "Automaton should recognize 'TESSTEST'");
    }
}
