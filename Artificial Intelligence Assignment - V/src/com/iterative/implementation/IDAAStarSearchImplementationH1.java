package com.iterative.implementation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by kushagrathapar on 10/2/15.
 */

/**
 * This algorithm uses heuristic function as the count of misplaced tiles
 */
public class IDAAStarSearchImplementationH1 {

    /**
     * String storing the final state of the A* problem.
     */
    public static final String FINAL_STATE = "0123456789ABCDEF";

    /**
     * String storing the initial State
     */
    public String INITIAL_STATE;

    /**
     * Linked List has been used as the frontier to store the nodes in form of stack
     */
    LinkedList<String> frontier;

    /**
     * stores current State and level
     */
    Map<String, Integer> levelDepth;

    /**
     * stores current state and its parent mapping.
     * Current state as the key, whereas parent state as its value
     */
    Map<String, String> stateHistory;

    /**
     * Algorithm execution variables
     */

    int nodes = 0;
    public static final int LIMIT = 100;
    int newNodeCounter = -1;
    int newValue;
    int a;

    /**
     * Stores the current state
     */
    String currentState;

    /**
     * Stores if solution has been found or not.
     * Initialized to false
     */
    boolean solutionFound = false;

    /**
     * A map to store characters A to F corresponding to numbers 10 to 15.
     * It is a 1:1 mapping. So we will use value to get the key back at some point later.
     */
    public static final Map<Integer, String> numberCharacterMap = new HashMap<>();

    /**
     * Default Constructor
     */
    public IDAAStarSearchImplementationH1() {}

    /**
     * This method takes the input string as argument.
     * Initializes the frontier, stateHistory map, levelDepth map and performs A Star Search
     * using heuristics as misplaced tiles
     * @param str
     * @return
     */
    public boolean initializeAStarSearch(String str) {
        levelDepth = new HashMap<>();
        stateHistory = new HashMap<>();
        this.INITIAL_STATE = str;
        frontier = new LinkedList<>();
        addNewNodeToFrontier(str, null);//add root
        return performAStarSearch();
    }

    boolean performAStarSearch() {
        int lowHeuristicValue = 0;
        while(currentState == null || (!solutionFound && (levelDepth.get(currentState) <= LIMIT))) {

            resetSearchVariables();

            while (!frontier.isEmpty()) {
                /**
                 * remove first node of our openlist
                 */
                currentState = frontier.removeFirst();

                /**
                 * Check if goal found
                 */
                if (currentState.equals(FINAL_STATE)) {
                    solutionFound = true;
                    printSolution(currentState);
                    break;
                }

                int heuristicVal = getHeuristicVal(currentState);
                if(heuristicVal > lowHeuristicValue) {
                    lowHeuristicValue = heuristicVal;
                    break;
                }

                /**
                 * Check if the LIMIT has been reached.
                 * If reached, then exit
                 */
                if (levelDepth.get(currentState) == LIMIT) {
                    solutionFound = false;
                } else {
                    a = currentState.indexOf("0");

                    /**
                     * This block implements the down action performed on the current state.
                     * It moves the blank space in the downward direction, if there is one possible.
                     */

                    if (a < 12) {
                        String nextState = currentState.substring(0, a) + currentState.substring(a + 4, a + 5)
                                + currentState.substring(a + 1, a + 4) + "0"
                                + currentState.substring(a + 5);
                        addNewNodeToFrontier(nextState, currentState);
                        nodes++;
                    }

                    /**
                     * This block implements the left action performed on the current state.
                     * It moves the blank space in the leftward direction, if there is one possible.
                     */

                    if (a != 0 && a != 4 && a != 8 && a != 12) {
                        String nextState = currentState.substring(0, a - 1) + "0" + currentState.charAt(a - 1)
                                + currentState.substring(a + 1);
                        addNewNodeToFrontier(nextState, currentState);
                        nodes++;
                    }

                    /**
                     * This block implements the right action performed on the current state.
                     * It moves the blank space in the rightward direction, if there is one possible.
                     */

                    if (a != 3 && a != 7 && a != 11 && a != 15) {
                        String nextState = currentState.substring(0, a) + currentState.charAt(a + 1) + "0"
                                + currentState.substring(a + 2);
                        addNewNodeToFrontier(nextState, currentState);
                        nodes++;
                    }

                    /**
                     * This block implements the up action performed on the current state.
                     * It moves the blank space in the upward direction, if there is one possible.
                     */

                    if (a > 3) {
                        String nextState = currentState.substring(0, a - 4) + "0" + currentState.substring(a - 3, a)
                                + currentState.charAt(a - 4) + currentState.substring(a + 1);
                        addNewNodeToFrontier(nextState, currentState);
                        nodes++;
                    }
                }

            }
        }

        if (solutionFound) {
            System.out.println("Solution Exists");
        } else {
            System.out.println("Solution not found. Depth LIMIT set is reached of [" + LIMIT + "]");
        }
        return solutionFound;
    }

    private void addNewNodeToFrontier(String newState, String oldState) {
        //  Check for repeated states. Ignore them
        if (!levelDepth.containsKey(newState)) {
            newNodeCounter++;
            stateHistory.put(newState, oldState);
            newValue = oldState == null ? 0 : levelDepth.get(oldState) + 1;
            levelDepth.put(newState, newValue);
            frontier.add(newState);
        }
    }

    public int getHeuristicVal(String currentBoard) {
        int heuristicValue = 0;
        int length = currentBoard.length();

        for (int i = 0; i < length; i++) {
            if (currentBoard.charAt(i) != FINAL_STATE.charAt(i)) {
                heuristicValue++;
            }
        }

        return levelDepth.get(currentBoard) + heuristicValue;
    }

    /**
     * Resets the value of the variables
     */
    public void resetSearchVariables() {
        levelDepth = new HashMap<>();
        stateHistory = new HashMap<>();
        frontier = new LinkedList<>();
        addNewNodeToFrontier(this.INITIAL_STATE, null);//add root
    }

    public void printSolution(String currState) {
        if (solutionFound) {
            System.out.println("Solution in " + levelDepth.get(currState) + " step(s)");
            System.out.println("Nodes generated: " + nodes);
            System.out.println("Unique Nodes: " + newNodeCounter);
        } else {
            System.out.println("Solution not found!");
            System.out.println("Depth Limit Reached " + LIMIT);
            System.out.println("Nodes generated: " + nodes);
            System.out.println("Unique Nodes: " + newNodeCounter);
        }

        String traceState = currState;
        LinkedList<String> finalAnswer;
        finalAnswer = new LinkedList<>();
        while (traceState != null) {
            finalAnswer.add(traceState);
            traceState = stateHistory.get(traceState);
        }

        while (!finalAnswer.isEmpty()) {
            traceState = finalAnswer.removeLast();
            System.out.println("Step " + levelDepth.get(traceState));
            try {
                dumpInMatrixForm(traceState);
            } catch (NullPointerException ignored) {
            }
        }


    }

    public void dumpInMatrixForm(String stateStr) {
        for (int z = 0; z < 16; z++) {
            if (String.valueOf(stateStr.charAt(z)).equals("0")) {
                System.out.print("    ");
            } else {
                String output = String.valueOf(stateStr.charAt(z));
                if (numberCharacterMap.values().contains(output)) {
                    for (Map.Entry<Integer, String> entry : numberCharacterMap.entrySet()) {
                        if (entry.getValue().contains(output)) {
                            output = String.valueOf(entry.getKey());
                        }
                    }
                }
                System.out.print(output + "   ");
            }
            if ((z + 1) % 4 == 0) {
                System.out.println();
            }
        }
    }
}
