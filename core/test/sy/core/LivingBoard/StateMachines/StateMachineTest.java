package sy.core.LivingBoard.StateMachines;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateMachineTest {
    private StateMachine stateMachine;
    private State1 state1;
    private State2 state2;


    @BeforeEach
    void setUp() {
        stateMachine = new StateMachine();
        state1 = new State1(stateMachine);
        state2 = new State2(stateMachine);
        stateMachine.addState(0, state1);
        stateMachine.addState(1, state2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCurrentState() {
        assertNull(stateMachine.getCurrentState());
    }

    @Test
    void transition() {
        stateMachine.transition(0);
        assertEquals(stateMachine.getCurrentState(), state1);
        stateMachine.transition(1);
        assertEquals(stateMachine.getCurrentState(), state2);
    }

    @Test
    void transitionState() {
        stateMachine.transition(0);
        assertTrue(state1.wasTransitionInCalled);
        assertFalse(state1.wasTransitionOutCalled);
        stateMachine.transition(1);
        assertTrue(state1.wasTransitionOutCalled);
        assertTrue(state2.wasTransitionInCalled);
        assertFalse(state2.wasTransitionOutCalled);
    }

    @Test
    void getState() {
        assertEquals(stateMachine.getState(0), state1);
        assertEquals(stateMachine.getState(1), state2);
        assertNull(stateMachine.getState(3));
    }

    @Test
    void updateDraw() {
        stateMachine.transition(0);
        stateMachine.update(0);
        stateMachine.draw(0);

        assertTrue(state1.wasUpdateCalled);
        assertTrue(state1.wasDrawCalled);
        assertFalse(state2.wasUpdateCalled);
        assertFalse(state2.wasDrawCalled);
    }

    private class State1 extends State {
        public boolean wasUpdateCalled;
        public boolean wasDrawCalled;
        public boolean wasTransitionInCalled;
        public boolean wasTransitionOutCalled;

        public State1(StateMachine stateMachine) {
            super(stateMachine);
        }

        @Override
        public void transitionIn() {
            wasTransitionInCalled = true;
        }

        @Override
        public void transitionOut() {
            wasTransitionOutCalled = true;
        }

        @Override
        public void update(float delta) {
            wasUpdateCalled = true;
        }

        @Override
        public void draw(float delta) {
            wasDrawCalled = true;
        }
    }

    private class State2 extends State {
        public boolean wasUpdateCalled;
        public boolean wasDrawCalled;
        public boolean wasTransitionInCalled;
        public boolean wasTransitionOutCalled;

        public State2(StateMachine stateMachine) {
            super(stateMachine);
        }

        @Override
        public void transitionIn() {
            wasTransitionInCalled = true;
        }

        @Override
        public void transitionOut() {
            wasTransitionOutCalled = true;
        }


        @Override
        public void update(float delta) {
            wasUpdateCalled = true;
        }

        @Override
        public void draw(float delta) {
            wasDrawCalled = true;
        }
    }
}