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

        DFSImplementation e = new DFSImplementation();
        System.out.println("Input State is : \n" + e.printEachNodeAsState(str));

        if(!e.verifyInputState(str)) {
            System.out.println("Input State is not a valid state for a 8-puzzle problem. " +
                    "Please verify the input state. Exiting now....");
            System.exit(1);
        }

        /**
         *  Add the initial state as the new node with its parent as null.
         */
        e.addNewNodeToFrontier(str, null);

        /**
         *  While the frontier is not empty, which means there are still nodes to expand,
         *  pull the current node from the frontier and perform actions in order of up, down, left and right
         *  to find the next possible states.
         */
        while (!e.stackFrontier.isEmpty()) {
            String currentState = e.stackFrontier.pop();
            e.performUpwardAction(currentState);
            e.performDownwardAction(currentState);
            e.performLeftwardAction(currentState);
            e.performRightwardAction(currentState);
        }

        /**
         *  If Frontier is empty, then there is no such state which matches the goal state, hence the solution
         *  does not exist for this particular initial state.
         */
        System.out.println("There is no solution for this particular initial state. " +
                "Please run the program again with some other initial state");
    }
}
