package com.puzzle; /**
 * Created by Kushagra Thapar on 9/17/15.
 */

import java.util.Stack;

public class DFSImplementation extends AbstractEightPuzzleStructure {

    /**
     * Since, DFS uses LIFO, so we have used a stack here as the frontier, to add new nodes
     */
    public Stack<String> stackFrontier = new Stack<>();

    @Override
    public String performSearch(String input) {

        /**
         *  Add the initial state as the new node with its parent as null.
         */
        this.addNewNodeToFrontier(input, null);

        /**
         *  While the frontier is not empty, which means there are still nodes to expand,
         *  pull the current node from the frontier and perform actions in order of up, down, left and right
         *  to find the next possible states.
         */
        while (!this.stackFrontier.isEmpty()) {
            String currentState = this.stackFrontier.pop();
            this.performUpwardAction(currentState);
            this.performDownwardAction(currentState);
            this.performLeftwardAction(currentState);
            this.performRightwardAction(currentState);
        }

        return solutionFound ? this.finalState : null;
    }

    @Override
    public void addNewNodeToFrontier(String newNode, String oldNode) {
        int newLevel;
        if (nodeLevelMap.containsKey(newNode)) {
            //  This new node has already been visited, so skip this
        } else {

            stackFrontier.push(newNode);

            //  Check for root node
            if (oldNode == null) {
                newLevel = 0;
            } else {
                newLevel = nodeLevelMap.get(oldNode) + 1;
            }
            nodeLevelMap.put(newNode, newLevel);
            childParentMap.put(newNode, oldNode);
        }
    }

}
