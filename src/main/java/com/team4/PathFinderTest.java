package com.team4;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
class PathFinderTest {
    @Test
    public void testGetPossibleMoves_Empty() {
        List<Stack<Integer>> state = new ArrayList<>();
        state.add(new Stack<>());
        state.add(new Stack<>());
        state.add(new Stack<>());

        List<String> moves = PathFinder.getPossibleMoves(state);
        assertEquals(0, moves.size(), "There should be no moves possible.");
    }

    @Test
    public void testGetPossibleMoves_OneDisk() {
        List<Stack<Integer>> state = new ArrayList<>();
        Stack<Integer> t1 = new Stack<>();
        t1.push(1);
        state.add(t1);
        state.add(new Stack<>());
        state.add(new Stack<>());

        List<String> moves = PathFinder.getPossibleMoves(state);
        assertEquals(2, moves.size(), "There should be two possible moves.");
        assertEquals("Move disk from tower 1 to tower 2", moves.get(0), "Check first move description.");
        assertEquals("Move disk from tower 1 to tower 3", moves.get(1), "Check second move description.");
    }

    @Test
    public void testGetPossibleMoves_MultipleDisksAndPegs() {
        List<Stack<Integer>> state = new ArrayList<>();
        Stack<Integer> t1 = new Stack<>();
        Stack<Integer> t2 = new Stack<>();
        Stack<Integer> t3 = new Stack<>();
        t1.push(3);
        t1.push(2);
        t1.push(1);
        t2.push(4);
        state.add(t1);
        state.add(t2);
        state.add(t3);

        List<String> moves = PathFinder.getPossibleMoves(state);
        assertEquals(3, moves.size(), "There should be three possible moves.");
        assertEquals("Move disk from tower 1 to tower 2", moves.get(0), "Check move from peg 1 to peg 3.");
        assertEquals("Move disk from tower 1 to tower 3", moves.get(1), "Check move from peg 2 to peg 1.");
        assertEquals("Move disk from tower 2 to tower 3", moves.get(2), "Check move from peg 2 to peg 3.");
    }
}
