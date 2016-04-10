package com.wvu.ai.csp.project.SudokuSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane Hogan on 4/9/16.
 */
public class CellCollection {

    private List<Cell> collection;

    public CellCollection(){
        collection =  new ArrayList<Cell>();
    }

    public void add(Cell newCell) {
         collection.add(newCell);
    }

    public List<Cell> getCollection(){
        return collection;
    }


}
