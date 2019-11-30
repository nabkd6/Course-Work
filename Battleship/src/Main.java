import java.util.Scanner;
import java.awt.Point;
import java.util.Random;

public class Main {
    static Board currentBoard = new Board();

    public static void main(String[] args) {
        intro();
        setPlayerShips();
        setComputerShips();
        playGame();
    }

    public static void intro() {
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, the sea is empty");
        currentBoard.makeBoard();
        currentBoard.printBoard();

    }

    public static void setPlayerShips() {
        Scanner input = new Scanner(System.in);
        int counter = 0;
        while (counter < 5) {
            boolean occupied = true;
            while (occupied) {
                System.out.print("Enter X coordinate for your ship: ");
                int x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship: ");
                int y = input.nextInt();
                if (x < 10 && y < 10) {
                    if (currentBoard.boardCoordinates[x][y].equals(" ")) {
                        currentBoard.updateBoard(x, y, "playerShip");
                        occupied = false;
                    } else {
                        System.out.println("Cannot place a ship there. Please enter coordinates again.");
                    }
                } else {
                    System.out.println("Your coordinates must be less than 10.");
                }

            }
            counter++;
        }
        System.out.println("All ships deployed!");
        currentBoard.printBoard();


    }
    public static void setComputerShips(){
        System.out.println("Computer is deploying ships.");
        int counter = 0;
        while (counter < 5) {
            boolean occupied = true;
            while (occupied) {
                Random r = new Random();
                int x = r.nextInt(10);
                int y = r.nextInt(10);
                if (currentBoard.boardCoordinates[x][y].equals(" ")) {
                    currentBoard.updateBoard(x, y, "compShip");
                    System.out.println((counter + 1) + ". ship DEPLOYED");
                    occupied = false;
                }
            }
            counter++;
        }
    }
    public static void playGame(){
        int playerShips = 5;
        int compShips = 5;
        Scanner input = new Scanner(System.in);
        while (playerShips > 0 && compShips > 0){
            boolean validHumanGuess = false;
            while (!validHumanGuess) {
                System.out.println("YOUR TURN");
                System.out.print("Enter X coordinate for your ship: ");
                int x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship: ");
                int y = input.nextInt();
                if(x < 10 && y < 10){
                if (currentBoard.boardCoordinates[x][y].equals("compShip")){
                    System.out.println("Boom! You sunk the ship!");
                    compShips--;
                    validHumanGuess = true;
                    currentBoard.boardCoordinates[x][y] = "hit";
                } else if (currentBoard.boardCoordinates[x][y].equals("playerShip")){
                    System.out.println("Oh no, you sunk your own ship :(");
                    playerShips--;
                    validHumanGuess = true;
                    currentBoard.boardCoordinates[x][y] = "hit";
                } else if (currentBoard.boardCoordinates[x][y].equals(" ")){
                    System.out.println("Sorry, you missed.");
                    currentBoard.boardCoordinates[x][y] = "playerMiss";
                    validHumanGuess = true;
                } else if (currentBoard.boardCoordinates[x][y].equals("hit")){
                    System.out.println("Ship already sunk. Guess again.");
                } else if (currentBoard.boardCoordinates[x][y].equals("compMiss")){
                    System.out.println("Sorry, you missed.");
                    currentBoard.boardCoordinates[x][y] = "bothMiss";
                    validHumanGuess = true;
                }else {
                    System.out.println("You already guessed that. Guess again.");
                }
                } else{
                    System.out.println("Out of bounds, try again.");
                }
            }
            boolean validCompGuess = false;
            while (!validCompGuess) {
                Random r = new Random();
                int x = r.nextInt(10);
                int y = r.nextInt(10);
                System.out.println("COMPUTER TURN");
                if (currentBoard.boardCoordinates[x][y].equals("compShip")){
                    System.out.println("Computer sunk it's own ship!");
                    compShips--;
                    validCompGuess = true;
                    currentBoard.boardCoordinates[x][y] = "hit";
                } else if (currentBoard.boardCoordinates[x][y].equals("playerShip")){
                    System.out.println("Computer sunk your ship!");
                    playerShips--;
                    validCompGuess = true;
                    currentBoard.boardCoordinates[x][y] = "hit";
                } else if (currentBoard.boardCoordinates[x][y].equals(" ")){
                    System.out.println("Computer missed.");
                    currentBoard.boardCoordinates[x][y] = "compMiss";
                    validCompGuess = true;
                } else {}
            }
            currentBoard.printBoard();
            System.out.println("Your ships: " + playerShips + " | " + "Computer ships: " + compShips);
        }
        if (playerShips == 0){
            System.out.println("Computer Wins! Better luck next time.");
        }else{
            System.out.println("Hooray! You win the battle :)");
        }
    }
}



