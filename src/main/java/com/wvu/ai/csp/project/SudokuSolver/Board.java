package com.wvu.ai.csp.project.SudokuSolver;

import java.util.List;

/**
 * Created by Shane Hogan on 4/9/16.
 */
public class Board {

    List<CellCollection> Row;
    List<CellCollection> Col;
    List<CellCollection> Region;

    //TODO - implement getBoard
    public Cell[][] getBoard(){
        Cell newBoard[][] = new Cell[9][9];
        return  newBoard;
    }

    //TODO - implement printBoard
    public void printBoard(){

    }

}
