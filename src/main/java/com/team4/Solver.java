package com.team4;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private List<String> moves; // List to store move instructions
    private int currentMoveIndex = 0; //track the current move

    public Solver() {
        this.moves = new ArrayList<>(); // Initialize the moves list
    }

    public void hanoi(int from, int to, int buf, int nmv) {
        if (nmv > 0) {
            hanoi(from, buf, to, nmv-1);
            moves.add("Move disk " + nmv + " from Tower " + (from + 1) + " to Tower " + (to + 1));
            hanoi(buf, to, from, nmv-1);
        }
    }

    public List<String> getMoves() {
        return moves;
    }


    // Method to get the next move and increment the move index
    public String getNextMove() {
        if (currentMoveIndex < moves.size()) {
            String move = moves.get(currentMoveIndex);
            currentMoveIndex++; // Move to the next index
            return move;
        } else {
            return "Congratulations, you completed the towers!"; // When all moves have been shown
        }
    }
    public void resetMoves() {
        currentMoveIndex = 0; // Reset the current move index to 0, reset works
    }
    // maybe a method that just figures out the next move only?
}
