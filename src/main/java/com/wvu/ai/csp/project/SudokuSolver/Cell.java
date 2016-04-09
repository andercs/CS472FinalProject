package com.wvu.ai.csp.project.SudokuSolver;

/**
 * Created by Shane Hogan on 4/9/16.
 */
public class Cell {

    private int value;
    private String name;

    public Cell(int value, String name){
        this.value = value;
        this.name = name;
    }


    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public boolean isAssigned(){
        return value != 0;
    }
}
