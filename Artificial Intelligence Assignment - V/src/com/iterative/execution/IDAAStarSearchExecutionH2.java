package com.iterative.execution;

import com.iterative.implementation.IDAAStarSearchImplementationH2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kushagrathapar on 10/2/15.
 */
public class IDAAStarSearchExecutionH2 {

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
    private static final String INITIAL_STATE = "26A3147B859FCDE0"; //"1024573896BCDAEF";

    private static final double MEGABYTE = 1048576F;

    public static double bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

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
                        "Sample Input: 2,6,10,3,1,4,7,11,8,5,9,15,12,13,14,0 \nExiting now....");
                System.exit(1);
            }
        }
        System.out.println("Initial state [" + inputPuzzle + "]");

        IDAAStarSearchImplementationH2 idaaStarSearchImplementationH2 = new IDAAStarSearchImplementationH2();
        idaaStarSearchImplementationH2.initializeAStarSearch(inputPuzzle);

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Processors available for this application are -> [" + runtime.availableProcessors() + "]");

        //  Calling the garbage collector
        runtime.gc();

        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();

        long memoryUsed = totalMemory - freeMemory;

        System.out.println("Memory used by this application is (bytes) -> [" + memoryUsed + "]");
        System.out.println("Memory used by this application is (MegaBytes) -> [" + bytesToMegabytes(memoryUsed) + "]");

        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken (seconds) -> [" + (endTime - startTime) / 1000.0 + "]");
    }

    /**
     * This method initializes the number character map.
     */
    public static void initializeNumberStringMap() {
        IDAAStarSearchImplementationH2.numberCharacterMap.put(10, "A");
        IDAAStarSearchImplementationH2.numberCharacterMap.put(11, "B");
        IDAAStarSearchImplementationH2.numberCharacterMap.put(12, "C");
        IDAAStarSearchImplementationH2.numberCharacterMap.put(13, "D");
        IDAAStarSearchImplementationH2.numberCharacterMap.put(14, "E");
        IDAAStarSearchImplementationH2.numberCharacterMap.put(15, "F");
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
                newInput += IDAAStarSearchImplementationH2.numberCharacterMap.get(Integer.parseInt(str));
            } else {
                newInput += str;
            }
        }
        return newInput;
    }
}
