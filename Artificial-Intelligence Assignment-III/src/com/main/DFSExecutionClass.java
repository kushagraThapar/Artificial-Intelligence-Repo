package com.main;

import com.puzzle.AbstractEightPuzzleStructure;
import com.puzzle.DFSImplementation;

/**
 * Created by Kushagra Thapar on 9/17/15.
 */
public class DFSExecutionClass {
    /**
     * Taking the initial state as what has been described in the problem statement.
     * <p>
     * 7   2   4
     * 5   0   6
     * 8   3   1
     * <p>
     * as "724506831" Where 0 represents the start state
     */
    private final static String INITIAL_STATE = "724506831";

    public static void main(String[] args) {

        /**
         *  Taking the initial state as the starting state for the problem
         */
        String str = INITIAL_STATE;

        if(args.length == 1) {
            str = args[0];
        }

        AbstractEightPuzzleStructure e = new DFSImplementation();
        System.out.println("Input State is : \n" + e.printEachNodeAsState(str));

        if(!e.verifyInputState(str)) {
            System.out.println("Input State is not a valid state for a 8-puzzle problem. " +
                    "Please verify the input state. Exiting now....");
            System.exit(1);
        }

        e.performSearch(str);

        /**
         *  If Frontier is empty, then there is no such state which matches the goal state, hence the solution
         *  does not exist for this particular initial state.
         */
        System.out.println("There is no solution for this particular initial state. " +
                "Please run the program again with some other initial state");

        /**
         * Method to print runtime and memory analysis when there is no solution found
         */
        e.printRuntimeAndMemoryAnalysis();
    }
}
