import java.util.Arrays;
import java.util.Scanner;

public class Board {
    public String[][] boardCoordinates;
    public int width=10;
    public int height=10;
    public void makeBoard(){
        boardCoordinates = new String [width][height];
        for (int row = 0; row<height; row++){
            for(int col = 0; col<width;col++){
                boardCoordinates[row][col] = " ";
            }
        }
    }k
    public void printBoard(){
        System.out.println("  0123456789  ");
        for(int row = 0; row <width; row++){
            System.out.print(row + "|");
            for(int col = 0; col<height;col++){
                if (boardCoordinates[row][col].equals(" ")){
                    System.out.print(" ");
                } else if (boardCoordinates[row][col].equals("playerShip")){
                    System.out.print("@");
                } else if (boardCoordinates[row][col].equals("compShip")){
                    System.out.print(" ");
                } else if (boardCoordinates[row][col].equals("hit")){
                    System.out.print("!");
                } else if (boardCoordinates[row][col].equals("compMiss")){
                    System.out.print(" ");
                } else if (boardCoordinates[row][col].equals("playerMiss")){
                    System.out.print("-");
                } else if (boardCoordinates[row][col].equals("bothMiss")){
                    System.out.print("-");
                }
                else {
                    System.out.print(boardCoordinates[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789  ");
    }
    public void updateBoard(int row, int col, String val){
        boardCoordinates[row][col] = val;
    }
}
