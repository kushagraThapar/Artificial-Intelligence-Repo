package com.tests;

import com.puzzle.AbstractEightPuzzleStructure;
import com.puzzle.DFSImplementation;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kushagrathapar on 10/4/15.
 */
public class DFSTestClass {

    public AbstractEightPuzzleStructure structure;
    public List<String> inputArrays = new ArrayList<>();
    public List<String> noSolutionFoundArray = new ArrayList<>();

    @BeforeSuite
    public void initialization() {
        System.out.println("Before Suit");
        structure = new DFSImplementation();
        System.out.println("Eight puzzle structure has been initialized");
    }


    @BeforeTest
    public void inputsCustomization() {
        System.out.println("Before Test");
        System.out.println("Preparing inputs for the DFS Search");
        inputArrays.add("724506831");
        inputArrays.add("234542321");
        inputArrays.add("123456780");
        inputArrays.add("876543210");
        inputArrays.add("456372180");
        noSolutionFoundArray.add("743201865");
        noSolutionFoundArray.add("234501876");
    }

    @Test
    public void solveEightPuzzleUsingBFS () {
        for(String input : inputArrays) {
            structure = new DFSImplementation();
            System.out.println("Verifying input State -> [" + input + "]");
            if(structure.verifyInputState(input)) {
                System.out.println("Solving Eight Puzzle with input -> [" + input + "]");
                String finalState = structure.performSearch(input);
                Assert.assertEquals(finalState, AbstractEightPuzzleStructure.SOLUTION);
            } else {
                System.out.println("This is not a valid input state -> [" + input + "]");
                Assert.assertFalse(AbstractEightPuzzleStructure.SOLUTION.equals(structure.getFinalState()));
            }
        }
        for(String input : noSolutionFoundArray) {
            structure = new DFSImplementation();
            if(structure.verifyInputState(input)) {
                System.out.println("Solving Eight Puzzle with input -> [" + input + "]");
                String finalState = structure.performSearch(input);
                Assert.assertEquals(finalState, null);
            } else {
                System.out.println("This is not a valid input state -> [" + input + "]");
                Assert.assertFalse(AbstractEightPuzzleStructure.SOLUTION.equals(structure.getFinalState()));
            }
        }
    }


    @AfterTest
    public void memoryClearance() {
        System.out.println("After test");
        System.out.println("Freeing inputs");
        inputArrays.clear();
    }


    @AfterSuite
    public void finishingOffTests() {
        System.out.println("After Suite");
        System.out.println("Freeing structure");
        structure = null;
    }
}
