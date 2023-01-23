/**
 * Realization of AI interface using simplistic random placement strategy.
 */

import java.util.Random;

public class DumbAI implements AI {
    
    private Random random = new Random();

    private char aiPiece;


    /**
     * Construct a DumbAI.
     * 
     * @param aiIsX Indicates whether the AI player's piece is
     *              the 'X'.
     */
    public DumbAI(boolean aiIsX) {
        /*
         * TBD
         */
        if (aiIsX != true){
            aiPiece = 'O';

        }
        else {
            aiPiece = 'X';
        }
    }

    public Move chooseMove(Board board) {
        int i;
        int j;
        while(true){
            i = random.nextInt(3);
            j = random.nextInt(3);

            if (board.get(i,j) == ' '){
                return new Move(i,j, aiPiece);
            }
        }

    }
}
