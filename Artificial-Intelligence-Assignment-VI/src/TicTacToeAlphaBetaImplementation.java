/**
 * Created by kushagrathapar on 10/8/15.
 */

import java.util.List;

/**
 * This class implements the Minimax algorithm with alpha beta pruning search to solve tic tac toe game
 */
public class TicTacToeAlphaBetaImplementation extends TicTacToeStructure{

    //Set this to some value if you want to have some specified depth limit for search
    int uptoDepth = -1;

    @Override
    public int minimaxAlgorithm(int depth, int turn) {
        return alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, depth, turn);
    }

    public int alphaBetaMinimax(int alpha, int beta, int depth, int turn) {

        if (beta <= alpha) {

            if (turn == 1) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        if (depth == uptoDepth || isGameOver()) {
            return evaluateBoard();
        }

        List<Point> pointsAvailable = getAvailableStates();

        if (pointsAvailable.isEmpty()) {
            return 0;
        }

        if (depth == 0) rootsChildrenScore.clear();

        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;

        for (Point point : pointsAvailable) {
            nodesExpanded++;
            int currentScore = 0;

            if (turn == 1) {
                placeAMove(point, 1);
                currentScore = alphaBetaMinimax(alpha, beta, depth + 1, 2);
                maxValue = Math.max(maxValue, currentScore);

                //Set alpha
                alpha = Math.max(currentScore, alpha);

                if (depth == 0) {
                    rootsChildrenScore.add(new PointsAndScores(currentScore, point));
                }
            } else if (turn == 2) {
                placeAMove(point, 2);
                currentScore = alphaBetaMinimax(alpha, beta, depth + 1, 1);
                minValue = Math.min(minValue, currentScore);

                //Set beta
                beta = Math.min(currentScore, beta);
            }
            //reset board
            board[point.x][point.y] = 0;

            //If a pruning has been done, don't evaluate the rest of the sibling states
            if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) {
                break;
            }
        }
        return turn == 1 ? maxValue : minValue;
    }
}
