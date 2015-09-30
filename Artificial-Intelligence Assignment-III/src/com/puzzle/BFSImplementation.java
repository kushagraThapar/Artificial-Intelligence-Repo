package com.puzzle; /**
 * Created by Kushagra Thapar on 9/17/15.
 */

import com.puzzle.AbstractEightPuzzleStructure;

import java.util.LinkedList;
import java.util.Queue;

public class BFSImplementation extends AbstractEightPuzzleStructure {

    /**
     * Since, BFS uses FIFO, so we have used a queue here as the frontier, which is implemented
     * using linked list to add new Nodes.
     */
    public Queue<String> queueFrontier = new LinkedList<>();

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
