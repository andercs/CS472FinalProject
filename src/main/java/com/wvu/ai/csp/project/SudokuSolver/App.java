package com.wvu.ai.csp.project.SudokuSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Shane Hogan on 4/9/16.
 */
public class App {
    private static int SIZE;
    private static Scanner KBD = new Scanner(System.in);

    public static void main(String[] args){
        Board sudokuBoard;
        int possibleSize;
        boolean validSize = false;

        System.out.println("What size sudoku board would you like? Enter in a number that is a perfect square.\n" +
                "The normal sudoku board is a 9 by 9 board.");
        while(!validSize){

            possibleSize = KBD.nextInt();
            if((long)Math.sqrt(possibleSize) * (long)Math.sqrt(possibleSize) == possibleSize){
                SIZE = possibleSize;
                validSize = true;
            }else{
                validSize = false;
                System.out.print("That size is not a perfect square, please select a different size.\n");
            }
        }

        System.out.println("Create your sudoko table by entering the cells. " +
                "\nEnter the entire top row then hit enter." +
                "\nEach cell should contain only a digit between 1-" + SIZE + ". " +
                "\nIf the cell is meant to be blank, enter a zero.");
        sudokuBoard = generateUserSudokuBoard();
        System.out.print(sudokuBoard.toString());
        KBD.close();
    }

    public static Board generateUserSudokuBoard(){
        Board newBoard = new Board(SIZE);

        for(int row = 0; row < SIZE; row++){
            for(int col = 0; col < SIZE; col++) {

                int inputValue = KBD.nextInt();

                if (inputValue >= 0 && inputValue <= SIZE) {
                    newBoard.addCell(inputValue, row, col);
                } else{
                    //Number is too large, reset everything
                    System.out.println("Invalid number passed in. Restarting.");
                    row = 0;
                    col = 0;
                    newBoard = new Board(SIZE);
                }
            }
            }

        return newBoard;
    }

    public List<Cell> generateListOfNames(Cell[][] board){
        List<Cell> nameList = new ArrayList<Cell>();
        for(int i = 0; i < SIZE; i++){
            nameList.addAll(Arrays.asList(board[i]).subList(0, SIZE));
        }
        return nameList;
    }

}
