import java.util.ArrayList;
import java.util.List;

/**
 * Created by kushagrathapar on 10/9/15.
 */
abstract public class TicTacToeStructure {

    private static final String BLANK_SPACE = "b";
    private static final String PLAYER_1 = "X";
    private static final String PLAYER_2 = "O";

    protected List<Point> availablePoints;
    protected int[][] board = new int[3][3];
    protected long nodesExpanded = 0;

    List<PointsAndScores> rootsChildrenScore = new ArrayList<>();

    abstract public int minimaxAlgorithm(int depth, int turn);

    public int evaluateBoard() {
        int score = 0;

        //Check all rows
        for (int i = 0; i < 3; ++i) {
            int blank = 0;
            int X = 0;
            int O = 0;
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    blank++;
                } else if (board[i][j] == 1) {
                    X++;
                } else {
                    O++;
                }

            }
            score += changeInScore(X, O);
        }

        //Check all columns
        for (int j = 0; j < 3; ++j) {
            int blank = 0;
            int X = 0;
            int O = 0;
            for (int i = 0; i < 3; ++i) {
                if (board[i][j] == 0) {
                    blank++;
                } else if (board[i][j] == 1) {
                    X++;
                } else {
                    O++;
                }
            }
            score += changeInScore(X, O);
        }

        int blank = 0;
        int X = 0;
        int O = 0;

        //Check diagonal (first)
        for (int i = 0, j = 0; i < 3; ++i, ++j) {
            if (board[i][j] == 1) {
                X++;
            } else if (board[i][j] == 2) {
                O++;
            } else {
                blank++;
            }
        }

        score += changeInScore(X, O);

        blank = 0;
        X = 0;
        O = 0;

        //Check Diagonal (Second)
        for (int i = 2, j = 0; i > -1; --i, ++j) {
            if (board[i][j] == 1) {
                X++;
            } else if (board[i][j] == 2) {
                O++;
            } else {
                blank++;
            }
        }

        score += changeInScore(X, O);

        return score;
    }

    private int changeInScore(int X, int O) {
        int change;
        if (X == 3) {
            change = 100;
        } else if (X == 2 && O == 0) {
            change = 10;
        } else if (X == 1 && O == 0) {
            change = 1;
        } else if (O == 3) {
            change = -100;
        } else if (O == 2 && X == 0) {
            change = -10;
        } else if (O == 1 && X == 0) {
            change = -1;
        } else {
            change = 0;
        }
        return change;
    }

    public boolean isGameOver() {
        //Game is over is someone has won, or board is full (draw)
        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    }

    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            //System.out.println("X Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                // System.out.println("X Row or Column win");
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                //  System.out.println("O Row or Column win");
                return true;
            }
        }

        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    }

    public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScore.size(); ++i) {
            if (MAX < rootsChildrenScore.get(i).score) {
                MAX = rootsChildrenScore.get(i).score;
                best = i;
            }
        }

        return rootsChildrenScore.get(best).point;
    }

    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if(board[i][j] == 0) {
                    System.out.print("|_| ");
                } else if(board[i][j] == 1) {
                    System.out.print("|X| ");
                } else {
                    System.out.print("|O| ");
                }
            }
            System.out.println();

        }
    }

    public void fillBoardConfiguration(String inputString) {
        int[][] boardConfiguration = new int[3][3];
        String[] input = inputString.split(",");
        int row, column;
        for (int i = 0; i < input.length; i++) {
            row = i / 3;
            column = i % 3;
            if (input[i].equalsIgnoreCase(BLANK_SPACE)) {
                boardConfiguration[row][column] = 0;
            }
            if (input[i].equalsIgnoreCase(PLAYER_1)) {
                boardConfiguration[row][column] = 1;
            }
            if (input[i].equalsIgnoreCase(PLAYER_2)) {
                boardConfiguration[row][column] = 2;
            }
        }
        this.board = boardConfiguration;
    }

    public boolean verifyInputString(String inputString) {
        if(inputString == null) {
            return false;
        }
        String input[] = inputString.split(",");
        if(input.length < 9) {
            return false;
        }
        for(String s : input) {
            if(!s.equalsIgnoreCase(BLANK_SPACE) && !s.equalsIgnoreCase(PLAYER_1) && !s.equalsIgnoreCase(PLAYER_2)){
                return false;
            }
        }
        return true;
    }

    public int getPosition(Point p) {
        int position = 1;
        if(p.x < 1) {
            position += p.y;
        } else if(p.x < 2) {
            position += 3 + p.y;
        } else {
            position += 6 + p.y;
        }
        return position;
    }

    public long getNodesExpanded() {
        return nodesExpanded;
    }
}
