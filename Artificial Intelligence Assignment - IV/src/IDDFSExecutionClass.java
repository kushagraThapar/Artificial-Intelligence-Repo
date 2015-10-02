import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kushagrathapar on 9/25/15.
 */
public class IDDFSExecutionClass {

    /**
     * Taking the initial state as what has been described in the problem statement.
     * <p>
     * 1   0   2   4
     * 5   7   3   8
     * 9   6   11  12
     * 13  10  14  15
     * <p>
     * as "1024573896BCDAEF" Where
     * 0 represents the blank space,
     * A to F represents the numbers from 10 to 15 respectively.
     */
    private static final String INITIAL_STATE = "1024573896BCDAEF";

    public static final int MAX_LIMIT = 100;

    public static void main(String[] args) {
        String inputPuzzle;

        /**
         *  Taking the initial state as the starting state for the problem
         */
        inputPuzzle = INITIAL_STATE;


        /**
         * Initialize the number character map
         */
        initializeNumberStringMap();

        if (args.length == 1) {
            inputPuzzle = args[0];
            inputPuzzle = verifyInputState(inputPuzzle);
            if (inputPuzzle == null) {
                System.out.println("Input State is not a valid state for a 15-puzzle problem. \n" +
                        "Please verify the input state. \n. " +
                        "Sample Input: 1, 0, 2, 4, 5, 7, 3, 8, 9, 6, 11, 12, 13, 10, 14, 15\nExiting now....");
                System.exit(1);
            }
        }
        System.out.println("Initial state [" + inputPuzzle + "]");

        int i = 0;
        while (i < MAX_LIMIT) {
            IterativeDeepeningDFS iterativeDeepeningDFS = new IterativeDeepeningDFS();
            iterativeDeepeningDFS.limit = i++;
            if (iterativeDeepeningDFS.initializeIDDFS(inputPuzzle)) {
                break;
            }
        }
    }

    /**
     * This method initializes the number character map.
     */
    public static void initializeNumberStringMap() {
        IterativeDeepeningDFS.numberCharacterMap.put(10, "A");
        IterativeDeepeningDFS.numberCharacterMap.put(11, "B");
        IterativeDeepeningDFS.numberCharacterMap.put(12, "C");
        IterativeDeepeningDFS.numberCharacterMap.put(13, "D");
        IterativeDeepeningDFS.numberCharacterMap.put(14, "E");
        IterativeDeepeningDFS.numberCharacterMap.put(15, "F");
    }

    /**
     * This method verifies the given input which should represent a state of the 8-puzzle problem
     *
     * @param state
     * @return
     */
    public static String verifyInputState(String state) {
        String newInput = "";
        if (state == null || state.isEmpty())
            return null;

        String[] inputArray = state.split(",");

        if (inputArray.length != 16)
            return null;

        Set<String> stateCharactersCharacterSet = new HashSet<>();
        for (String str : inputArray) {
            if (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 15)
                return null;
            if (stateCharactersCharacterSet.contains(str))
                return null;
            stateCharactersCharacterSet.add(str);
            if (Integer.parseInt(str) >= 10) {
                newInput += IterativeDeepeningDFS.numberCharacterMap.get(Integer.parseInt(str));
            } else {
                newInput += str;
            }
        }
        return newInput;
    }
}
