package com.wvu.ai.csp.project.SudokuSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Shane Hogan on 4/9/16.
 */
public class App {
    //TODO replace size with user input
    private static int SIZE = 9;
    static  Scanner kbd = new Scanner(System.in);

    public static void main(String[] args){
        Board sudokuBoard;


        //TODO - Ask for board size & check that input is perfect square


        System.out.println("Create your sudoko table by entering the cells. " +
                "\nEnter the entire top row then hit enter." +
                "\nEach cell should contain only a single digit between 1-9. " +
                "\nIf the cell is meant to be blank, enter a zero.");
        sudokuBoard = generateUserSudokuBoard();

    }

    public static Board generateUserSudokuBoard(){
        Board newBoard = new Board(SIZE);

        for(int row = 0; row < SIZE; row++){
            for(int col = 0; col < SIZE; col++) {

                int inputValue = kbd.nextInt();

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
