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
