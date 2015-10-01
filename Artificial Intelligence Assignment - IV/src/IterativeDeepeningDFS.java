import java.util.*;

/**
 * Created by kushagrathapar on 9/25/15.
 */
public class IterativeDeepeningDFS {

    /**
     * String storing the final state of the IDDFS problem.
     */
    public static final String FINAL_STATE = "123456789ABCDEF0";

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
    int limit;
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
     * Default Constructor
     */
    IterativeDeepeningDFS() {
    }

    /**
     * This method takes the input string as argument.
     * Initializes the frontier, stateHistory map, levelDepth map and performs IDDFS Search
     *
     * @param str
     * @return
     */
    public boolean initializeIDDFS(String str) {
        levelDepth = new HashMap<String, Integer>();
        stateHistory = new HashMap<String, String>();
        this.INITIAL_STATE = str;
        frontier = new LinkedList<String>();
        addNewNodeToFrontier(str, null);//add root
        return performIDDFSearch();
    }

    boolean performIDDFSearch() {
        while (!frontier.isEmpty()) {
            /**
             * remove first node of our openlist
             */
            currentState = frontier.removeLast();

            /**
             * Check if goal found
             */
            if (currentState.equals(FINAL_STATE)) {
                solutionFound = true;
                printSolution(currentState);
                break;
            }

            /**
             * Check if the limit has been reached.
             * If reached, then exit
             */
            if (levelDepth.get(currentState) == limit) {
                solutionFound = false;
                continue;
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

        if (solutionFound) {
            System.out.println("Solution Exists");
        } else {
            System.out.println("Solution not found. Depth limit set is reached of [" + limit + "]");
        }
        return solutionFound;
    }

    private void addNewNodeToFrontier(String newState, String oldState) {
        //  Check for repeated states. Ignore them
        if (!levelDepth.containsKey(newState)) {
            newNodeCounter++;
            frontier.add(newState);
            stateHistory.put(newState, oldState);
            newValue = oldState == null ? 0 : levelDepth.get(oldState) + 1;
            levelDepth.put(newState, newValue);
        }

    }

    void printSolution(String currState) {
        if (solutionFound) {
            System.out.println("Solution in " + levelDepth.get(currState) + " step(s)");
            System.out.println("Nodes generated: " + nodes);
            System.out.println("Unique Nodes: " + newNodeCounter);
        } else {
            System.out.println("Solution not found!");
            System.out.println("Depth Limit Reached " + limit);
            System.out.println("Nodes generated: " + nodes);
            System.out.println("Unique Nodes: " + newNodeCounter);
        }

        String traceState = currState;
        LinkedList<String> finalAnswer;
        finalAnswer = new LinkedList<String>();
        while (traceState != null) {
            finalAnswer.add(traceState);
            traceState = stateHistory.get(traceState);
        }

        while (!finalAnswer.isEmpty()) {
            traceState = finalAnswer.removeLast();
            System.out.println("Step " + levelDepth.get(traceState));
            try {
                dumpInMatrixForm(traceState);
            } catch (NullPointerException e) {
            }
        }


    }

    public void dumpInMatrixForm(String stateStr) {
        for (int z = 0; z < 16; z++) {
            if (String.valueOf(stateStr.charAt(z)).equals("0")) {
                System.out.print("  ");
            } else {
                System.out.print(String.valueOf(stateStr.charAt(z)) + " ");
            }
            if ((z + 1) % 4 == 0) {
                System.out.println();
            }
        }
    }
}
