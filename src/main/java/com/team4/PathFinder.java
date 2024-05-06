package com.team4;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class PathFinder {

    public static List<String> getPossibleMoves(List<Stack<Integer>> state) {
        List<String> possibleMoves = new ArrayList<>();
        int numPegs = state.size();

        for (int source = 0; source < numPegs; source++) {
            if (!state.get(source).isEmpty()) {
                int topDisk = state.get(source).peek();
                for (int destination = 0; destination < numPegs; destination++) {
                    if (source != destination) {
                        if (state.get(destination).isEmpty() || state.get(destination).peek() > topDisk) {
                            possibleMoves.add("Move disk from tower " + (source + 1) + " to tower " + (destination + 1));
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }
}