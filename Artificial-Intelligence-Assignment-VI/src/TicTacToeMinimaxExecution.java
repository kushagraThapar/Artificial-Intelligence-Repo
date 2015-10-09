/**
 * Created by kushagrathapar on 10/8/15.
 */
public class TicTacToeMinimaxExecution {

    /**
     * This is the initial state of the board which is mentioned in the question.
     * b represents the blank space
     * X represents the turn of player 1
     * O represents the turn of player 2
     */
    private final static String INITIAL_STATE = "b,b,b,X,b,b,b,O,b";

    public static void main(String[] args) {
        String inputString = INITIAL_STATE;
        if (args.length == 1) {
            inputString = args[0];
        }
        TicTacToeStructure ticTacToeStructure = new TicTacToeMinimaxImplementation();
        if (!ticTacToeStructure.verifyInputString(inputString)) {
            System.out.println("The input is not in the proper format. Please enter string as 'b,b,b,X,b,b,b,O,b' without the quotes\n" +
                    "b represents the blank space here and X and O represents the turns of player 1 and 2 respectively");
            System.exit(1);
        }
        ticTacToeStructure.fillBoardConfiguration(inputString);
        System.out.println("Initial State:");
        ticTacToeStructure.displayBoard();
        ticTacToeStructure.minimaxAlgorithm(0, 1);

        Point bestMove = ticTacToeStructure.returnBestMove();
        ticTacToeStructure.placeAMove(bestMove, 1);
        System.out.println("\n\nFinal State:");
        ticTacToeStructure.displayBoard();
        int position = ticTacToeStructure.getPosition(bestMove);
        System.out.println("Best Move is at position -> [" + position + "]");
        System.out.println("Total nodes expanded -> [" + ticTacToeStructure.getNodesExpanded() + "]");
    }
}
