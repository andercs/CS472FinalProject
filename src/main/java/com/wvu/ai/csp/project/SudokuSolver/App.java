package com.wvu.ai.csp.project.SudokuSolver;

import com.wvu.ai.csp.project.SudokuSolver.Cell;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Created by Shane Hogan on 4/9/16.
 */
public class App {
    private final static int SIZE = 9;

    public static void main(String[] args){
        Cell[][] sudokoBoard;

        System.out.println("Create your sudoko table by entering the cells. " +
                "\nEnter the entire top row then hit enter." +
                "\nEach cell should contain only a single digit between 1-9. " +
                "\nIf the cell is meant to be blank, enter a zero.");

        //Generate Sudoko Board
        sudokoBoard = generateUserSudokoBoard();

        //Print Sudoko Board
        printBoard(sudokoBoard);
    }

    public static Cell[][] generateUserSudokoBoard(){
        Cell[][] newBoard = new Cell[SIZE][SIZE];
        int cell = 0;
        Scanner kbd = new Scanner(System.in);
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                cell = kbd.nextInt();
                newBoard[i][j] = new Cell(cell);
                if(cell > 9){
                    //error
                }
            }
        }
        return newBoard;
    }

    public static void printBoard(Cell[][] board){
        for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++){
                System.out.print(board[i][j].getValue() + " ");
            }
            System.out.print("\n");
        }
    }

    public static boolean isSolveable(int[][] board){
        boolean solveable = false;

        return solveable;
    }
}
