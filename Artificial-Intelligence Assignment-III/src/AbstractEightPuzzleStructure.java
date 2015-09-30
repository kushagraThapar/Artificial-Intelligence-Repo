import java.util.*;

/**
 * Created by Kushagra Thapar on 9/17/15.
 */
abstract public class AbstractEightPuzzleStructure {

    /**
     * We need to store the depth of the solution, so a map has been used to store the depth of the
     * nodes. Hash Map is used to ignore the repeated ones.
     */
    public Map<String, Integer> nodeLevelMap = new HashMap<>();

    /**
     * We need to store the parent of each child, so in order to print the each state of the solution.
     * So this map contains the parent to child relationship, with child as the key and its parent as the value.
     * So, technically it creates a self linked map, containing values, which will be used as keys also.
     */
    public Map<String, String> childParentMap = new HashMap<>();

    /**
     * We need to store the actions performed on each node, which resulted into a new state.
     * So, this map stores actions corresponding to the each state.
     */
    public Map<String, String> stateActionMap = new HashMap<>();

    /**
     * Keeping in mind the good coding practices, so used private static final variables instead of literals
     */
    protected static final String SOLUTION = "012345678";
    protected static final String BLANK_SPACE = "0";

    /**
     * Possible Actions on any node which will lead it to a new state
     */
    protected static final String UP = "UP";
    protected static final String DOWN = "DOWN";
    protected static final String LEFT = "LEFT";
    protected static final String RIGHT = "RIGHT";


    /**
     * This method adds a new node to the frontier. It also stores the depth of the new node in the nodeLevelMap
     * and maintains the parent child relationship as well by storing the old node as parent for this new node
     * in the childParentMap
     *
     * @param newNode
     * @param oldNode
     */
    abstract public void addNewNodeToFrontier(String newNode, String oldNode);

    /**
     * This method implements the up action performed on the current state. It moves the blank space
     * in the upward direction, if there is one possible.
     * It then adds the new node in the frontier and checks if that new node is the final state.
     * If it is the final state, it terminates the program.
     *
     * @param currentNode
     */
    public void performUpwardAction(String currentNode) {
        int blankSpaceIndex = currentNode.indexOf(BLANK_SPACE);
        if (blankSpaceIndex > 2) {
            String newNode = currentNode.substring(0, blankSpaceIndex - 3) + "0" +
                    currentNode.substring(blankSpaceIndex - 2, blankSpaceIndex) +
                    currentNode.charAt(blankSpaceIndex - 3) + currentNode.substring(blankSpaceIndex + 1);
            if (!nodeLevelMap.containsKey(newNode)) {
                stateActionMap.put(newNode, UP);
            }
            checkFinalState(currentNode, newNode);
        }
    }


    /**
     * This method implements the down action performed on the current state. It moves the blank space
     * in the downward direction, if there is one possible.
     * It then adds the new node in the frontier and checks if that new node is the final state.
     * If it is the final state, it terminates the program.
     *
     * @param currentNode
     */
    public void performDownwardAction(String currentNode) {
        int blankSpaceIndex = currentNode.indexOf("0");
        if (blankSpaceIndex < 6) {
            String newNode = currentNode.substring(0, blankSpaceIndex) +
                    currentNode.substring(blankSpaceIndex + 3, blankSpaceIndex + 4) +
                    currentNode.substring(blankSpaceIndex + 1, blankSpaceIndex + 3) + "0" +
                    currentNode.substring(blankSpaceIndex + 4);
            if (!nodeLevelMap.containsKey(newNode)) {
                stateActionMap.put(newNode, DOWN);
            }
            checkFinalState(currentNode, newNode);
        }
    }

    /**
     * This method implements the left action performed on the current state. It moves the blank space
     * in the leftward direction, if there is one possible.
     * It then adds the new node in the frontier and checks if that new node is the final state.
     * If it is the final state, it terminates the program.
     *
     * @param currentNode
     */
    public void performLeftwardAction(String currentNode) {
        int blankSpaceIndex = currentNode.indexOf("0");
        if (blankSpaceIndex != 0 && blankSpaceIndex != 3 && blankSpaceIndex != 6) {
            String newNode = currentNode.substring(0, blankSpaceIndex - 1) + "0" +
                    currentNode.charAt(blankSpaceIndex - 1) +
                    currentNode.substring(blankSpaceIndex + 1);
            if (!nodeLevelMap.containsKey(newNode)) {
                stateActionMap.put(newNode, LEFT);
            }
            checkFinalState(currentNode, newNode);
        }
    }

    /**
     * This method implements the right action performed on the current state. It moves the blank space
     * in the rightward direction, if there is one possible.
     * It then adds the new node in the frontier and checks if that new node is the final state.
     * If it is the final state, it terminates the program.
     *
     * @param currentNode
     */
    public void performRightwardAction(String currentNode) {
        int blankSpaceIndex = currentNode.indexOf("0");
        if (blankSpaceIndex != 2 && blankSpaceIndex != 5 && blankSpaceIndex != 8) {
            String newNode = currentNode.substring(0, blankSpaceIndex) + currentNode.charAt(blankSpaceIndex + 1) +
                    "0" + currentNode.substring(blankSpaceIndex + 2);
            if (!nodeLevelMap.containsKey(newNode)) {
                stateActionMap.put(newNode, RIGHT);
            }
            checkFinalState(currentNode, newNode);
        }
    }

    /**
     * This method adds the new node to the frontier, and then checks its state. If it matches with the
     * final desired state, this method calls the print method followed by termination of the program.
     *
     * @param oldNode
     * @param newNode
     */
    protected void checkFinalState(String oldNode, String newNode) {
        addNewNodeToFrontier(newNode, oldNode);
        if (newNode.equals(SOLUTION)) {
            System.out.println("Solution Exists at Level " + nodeLevelMap.get(newNode) + " of the tree");
            printFullStackTraceForGoal(newNode);
        }
    }

    /**
     * This method prints the full stack trace of the final goal achieved. It prints the level at which the
     * intermediate states were present by the use of nodeLevelMap and it prints the parent and child relationships
     * by using the childParentMap. It also prints the action performed on each state to get the resultant state
     *
     * @param newNode
     */
    protected void printFullStackTraceForGoal(String newNode) {
        List<String> fullStackTrace = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        String childNode = newNode;
        StringBuilder builder = new StringBuilder();
        while (childNode != null) {
            builder.append("\nLevel [" + nodeLevelMap.get(childNode) + "]\n");
            builder.append(printEachNodeAsState(childNode));
            fullStackTrace.add(builder.toString());
            actions.add(stateActionMap.get(childNode));
            childNode = childParentMap.get(childNode);
            builder = new StringBuilder();
        }
        Collections.reverse(fullStackTrace);
        Collections.reverse(actions);
        int i = 0;
        for (String s : fullStackTrace) {
            if (i < actions.size() && actions.get(i) != null) {
                System.out.println("Action performed on this state [" + actions.get(i) + "]");
            }
            System.out.println(s);
            i++;
        }
        System.exit(0);
    }


    /**
     * Prints each node as matrix display
     *
     * @param node
     */
    protected String printEachNodeAsState(String node) {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (char c : node.toCharArray()) {
            //  This means we need to change the row now, initialize index to 0 again
            if (index == 3) {
                index = 0;
                builder.append("\n");
            }
            builder.append(c + " ");
            index++;
        }
        builder.append("\n");
        return builder.toString();
    }


    /**
     * This method verifies the given input which should represent a state of the 8-puzzle problem
     * @param state
     * @return
     */
    protected boolean verifyInputState(String state) {
        if(state == null || state.isEmpty())
            return false;

        char[] stateCharacters = state.toCharArray();

        if(stateCharacters.length != 9)
            return false;

        Set<Character> stateCharactersCharacterSet = new HashSet<>();
        for(char c : stateCharacters) {
            if(Integer.parseInt(String.valueOf(c)) < 0 || Integer.parseInt(String.valueOf(c)) > 8)
                return false;
            if(stateCharactersCharacterSet.contains(c))
                return false;
            stateCharactersCharacterSet.add(c);
        }
        return true;
    }
}
