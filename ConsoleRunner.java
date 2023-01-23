/**
 * ConsoleRunner:  Prompts the user to determine the parameters of the Game
 * constructor.  Creates a Game and manages the alternating calls to the
 * ‘place’ methods in Game.  Prompts the user for inputs and outputs the state
 * of the board to the console.
 */

import java.util.Scanner;
public class ConsoleRunner {

    /**
     * Should the human player be the X?  Note that X always
     * goes first.
     */
    private boolean playerIsX;

    private Game game;

    // Use to process text input from the user.
    private Scanner scanner = new Scanner(System.in);

    private int[] getPlayerChoice() {
        int[] inputtedChoice = new int[2];
        System.out.println("Enter desired x-coordinate:");
        int i = scanner.nextInt();
        System.out.println("Enter desired y-coordinate:");
        int j = scanner.nextInt();
        inputtedChoice[0] = i;
        inputtedChoice[1] = j;

        return inputtedChoice;

    }

    private void playerTurn() {
        while (true) {
            int[] pc = getPlayerChoice();
            if (game.placePlayerPiece(pc[0], pc[1])) {
                System.out.println("After your move:\n");
                System.out.println(game.getBoard().toString());
                checkStatus();
                break;
            }
            System.out.println("Please choose a space on the board, that is not already occupied.");
        }
    }

    private void aiTurn() {
        game.aiPlacePiece();
        System.out.println("After AI move:\n");
        System.out.println(game.getBoard().toString());
        checkStatus();
    }


    private void checkStatus() {
        if (game.getStatus() == GameStatus.X_WON && playerIsX) {
            System.out.println("You won!");
            System.exit(0);
        } else if (game.getStatus() == GameStatus.X_WON && !playerIsX) {
            System.out.println("AI wins!");
            System.exit(0);
        } else if(game.getStatus()==GameStatus.O_WON && !playerIsX) {
            System.out.println("You won!");
            System.exit(0);
        }else if (game.getStatus()==GameStatus.O_WON && playerIsX) {
            System.out.println("AI wins!");
            System.exit(0);
        } else if (game.getStatus()==GameStatus.DRAW) {
        System.out.println("It's a Draw!");
        System.exit(0);}
    }


    /**
     * Constructor
     */
    public ConsoleRunner() {    

        while (true){
            System.out.println("Do you want to play as X (Y/N):");
            String playerX = scanner.next();
            if (playerX.equals("Y")){
                playerIsX = true;
                break;
            }
            if (playerX.equals("N")){
                playerIsX = false;
                break;
            }
            System.out.println("You did not enter 'Y' or 'N', please choose one of these options.\n");

        }

        boolean challengeLevel;
        while (true){
            System.out.println("Do you want a challenge (Y/N): ");
            String challenged = scanner.next();
            if (challenged.equals("Y")){
                challengeLevel = true;
                break;
            }
            else if (challenged.equals("N")){
                challengeLevel = false;
                break;
            }
            System.out.println("You did not enter 'Y' or 'N', please choose one of these options.\n");
        }
        game = new Game (playerIsX, challengeLevel);
        System.out.println(game.getBoard().toString());
    }

    /**
     * Enter the main control loop which returns only at the end of the game
     * when one party has won or there has been a draw.
     */
    public void mainLoop() {

        while(true){
            if (playerIsX) {
                playerTurn();
                aiTurn();
            } else {
                aiTurn();
                playerTurn();
            }
        }
    }
}
