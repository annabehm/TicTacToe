/**
 * Represents the logic of the game in terms of detecting wins or draws.  Also
 * places new pieces for the human player or AI.
 */

public class Game {
    private Board board = new Board();
    private GameStatus status;
    private AI ai;
    private int testX= 0;
    private int testO= 0;
    private char playerPiece;

    /**
     * Construct a new Game according to the given parameters.
     */
    public Game(boolean playerIsX, boolean challenging) {

        if (playerIsX == true){
            playerPiece = 'X';
        }
        else{
            playerPiece = 'O';
        }

        if (challenging == true){
            ai = new SmartAI(!playerIsX);
        }
        else {
            ai = new DumbAI(!playerIsX);
        }

        status= GameStatus.IN_PROGRESS;

    }

    /**
     * Return a copy of the board's current contents.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the game's status.
     */
    public GameStatus getStatus() {

        //Check each row
        for(int i = 0; i < 3; i++) {
            if(board.get(i,0) == board.get(i,1) && board.get(i,1) == board.get(i,2)) {
                if (board.get(i,0) == 'X'){
                    testX += 1;
                }
                if (board.get(i,0) == 'O'){
                    testO += 1;
                }
            }
        }

        //Check each column
        for(int j = 0; j < 3; j++) {
            if(board.get(0,j) == board.get(1,j) && board.get(1,j) == board.get(2,j)) {
                if (board.get(0,j) == 'X'){
                    testX += 1;
                }
                if (board.get(0,j) == 'O'){
                    testO += 1;
                }
            }
        }

        //Check the diagonals
        if(board.get(0,0) == board.get(1,1) && board.get(1,1) == board.get(2,2)) {
            if (board.get(0,0) == 'X'){
                testX += 1;
            }
            if (board.get(0,0) == 'O'){
                testO += 1;
            }
        }
        if(board.get(2,0) == board.get(1,1) && board.get(1,1) ==  board.get(0,2)) {
            if (board.get(2,0) == 'X'){
                testX += 1;
            }
            if (board.get(2,0) == 'O'){
                testO += 1;
            }
        }

        //figure out Game Status
        if (testX> testO){
            status =  GameStatus.X_WON;
        }
        if (testO> testX){
            status =  GameStatus.O_WON;
        }
        if (testO == testX && board.isFull() == true){
            status =  GameStatus.DRAW;
        }

        return status;
    }
    
    /**
     * Place a piece for the player on the board.
     * @param i i-coordinate of desired position.
     * @param j j-coordinate of desired position
     * @return true only if the coordinates of the desired position are in
     * range and the corresponding cell is empty.
     *
     * @precondition status == IN_PROGRESS
     *
     */
    public boolean placePlayerPiece(int i, int j) {
        if (getStatus() == GameStatus.IN_PROGRESS) {
            if (i>=0 && i<3 && j>=0 && j<3) {
                if (board.get(i, j) == ' ') {
                        board = new Board(board, new Move(i,j, playerPiece));
                        return true;
                    }
                }
            }
        return false;
        }


    /**
     * @precondition status == IN_PROGRESS
     */
    public void aiPlacePiece() {
        if (status == GameStatus.IN_PROGRESS) {
            board = new Board (board, ai.chooseMove(board));
        }
    }
}
