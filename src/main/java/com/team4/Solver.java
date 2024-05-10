package com.team4;

import java.util.ArrayList;
import java.util.List;

/**
 * The Solver class for the Towers of Hanoi game.
 * It solves the Towers of Hanoi game using recursion.
 * It moves disks from one peg to another peg.
 * It uses the moveDisks method to solve the game.
 *
 * @Author Jonathan Jara
 */


public class Solver {

    private final List<int[]> moves;

    public Solver() {
        this.moves = new ArrayList<>(); // Initialize the moves list
    }

    public static void moveDisks(int[] diskPositions, int disksToMove, int targetPeg, List<int[]> answer) {
        for (int badDisk = disksToMove - 1; badDisk >= 0; --badDisk) {
            int currentPeg = diskPositions[badDisk];
            if (currentPeg != targetPeg) {
                // found the largest disk on the wrong peg

                // sum of the peg numbers is 3, so to find the other one:
                int otherPeg = 3 - targetPeg - currentPeg;

                // before we can move badDisk, we have to get the smaller
                // ones out of the way
                moveDisks(diskPositions, badDisk, otherPeg, answer);

                // Move
                diskPositions[badDisk] = targetPeg;
//                System.out.println(
//                        "Move " + badDisk + " from " + currentPeg + " to " + targetPeg
//                );
                int[] disks = new int[diskPositions.length];
                System.arraycopy(diskPositions, 0, disks, 0, diskPositions.length);
                answer.add(disks);

                //Now we can put the smaller ones in the right place
                moveDisks(diskPositions, badDisk, targetPeg, answer);
                break;
            }
        }
    }


    public List<int[]> solve(int[] n, int num) {
        moves.clear();
        moveDisks(n, num, 2, moves);
        return moves;
    }
//    public void hanoi(int from, int to, int buf, int nmv) {
//        if (nmv > 0) {
//            hanoi(from, buf, to, nmv - 1);
//            moves.add(new int[]{from+1, to+1, nmv}); // Add move as an int array
//            hanoi(buf, to, from, nmv - 1);
//        }
//    }
//
//    public List<int[]> getMoves() {
//        return moves;
//    }


    // Method to get the next move and increment the move index
//    public String getNextMove() {
//        if (currentMoveIndex < moves.size()) {
//            String move = Arrays.toString(moves.get(currentMoveIndex));
//            currentMoveIndex++; // Move to the next index
//            return move;
//        } else {
//            return "Congratulations, you completed the towers!"; // When all moves have been shown
//        }
//    }
//    public void resetMoves() {
//        currentMoveIndex = 0; // Reset the current move index to 0, reset works
//    }
    // maybe a method that just figures out the next move only?

}
