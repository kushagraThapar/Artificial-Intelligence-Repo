package com.puzzle; /**
 * Created by Kushagra Thapar on 9/17/15.
 */

import java.util.LinkedList;
import java.util.Queue;

public class BFSImplementation extends AbstractEightPuzzleStructure {

    public BFSImplementation() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Since, BFS uses FIFO, so we have used a queue here as the frontier, which is implemented
     * using linked list to add new Nodes.
     */
    public Queue<String> queueFrontier = new LinkedList<>();

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
        while (!this.queueFrontier.isEmpty() && !this.solutionFound) {
            String currentState = this.queueFrontier.remove();
            this.performUpwardAction(currentState);
            this.performDownwardAction(currentState);
            this.performLeftwardAction(currentState);
            this.performRightwardAction(currentState);
        }

        return this.solutionFound ? this.finalState : null;
    }

    @Override
    public void addNewNodeToFrontier(String newNode, String oldNode) {
        int newLevel;
        if (nodeLevelMap.containsKey(newNode)) {
            //  This new node has already been visited, so skip this
        } else {

            queueFrontier.add(newNode);

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
