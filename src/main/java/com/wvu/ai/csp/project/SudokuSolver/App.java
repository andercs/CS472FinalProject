package com.wvu.ai.csp.project.SudokuSolver;

import com.wvu.ai.csp.project.SudokuSolver.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Shane Hogan on 4/9/16.
 */
public class App {
    private final static int SIZE = 9;
    static  Scanner kbd = new Scanner(System.in);

    public static void main(String[] args){
        Cell[][] sudokuBoard;

        System.out.println("Create your sudoko table by entering the cells. " +
                "\nEnter the entire top row then hit enter." +
                "\nEach cell should contain only a single digit between 1-9. " +
                "\nIf the cell is meant to be blank, enter a zero.");

        //Generate Sudoko Board
        sudokuBoard = generateUserSudokuBoard();

        //Print Sudoko Board
        printBoard(sudokuBoard);
    }

    public static Cell[][] generateUserSudokuBoard(){
        Cell[][] newBoard = new Cell[SIZE][SIZE];
        int cell;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                cell = kbd.nextInt();
                if(cell > 9){
                    System.out.println("Invalid number passed in. Restarting.");
                    i = 0;
                    j = 0;
                }
                newBoard[i][j] = new Cell(cell,String.valueOf((i+1) + "," + (j+1)));
            }
        }
        return newBoard;
    }

    public static void printBoard(Cell[][] board){
        for(int i = 0; i < SIZE; i ++){
            if(i%3==0 && i !=0){
                System.out.print("\n");
            }
            for(int j = 0; j < SIZE; j++){
                if(j%3 == 0 && j != 0){
                    System.out.print("\t");
                }
                System.out.print(board[i][j].getName() + " ");
            }
            System.out.print("\n");
        }
    }

    public List<Cell> generateListOfNames(Cell[][] board){
        List<Cell> nameList = new ArrayList<Cell>();
        for(int i = 0; i < SIZE; i++){
            nameList.addAll(Arrays.asList(board[i]).subList(0, SIZE));
        }
        return nameList;
    }

}
