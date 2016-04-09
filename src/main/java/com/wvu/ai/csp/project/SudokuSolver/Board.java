package com.wvu.ai.csp.project.SudokuSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane Hogan on 4/9/16.
 */
public class Board {

    private int size;
    private int sizeRoot;
    private List<CellCollection> rows;
    private List<CellCollection> columns;
    private List<CellCollection> regions;


    public Board(int size){
        this.size = size;
        this.sizeRoot = (int)Math.sqrt(size);
        rows = new ArrayList<CellCollection>();
        columns = new ArrayList<CellCollection>();
        regions = new ArrayList<CellCollection>();
        populateBoard();
    }

    private void populateBoard(){
        for(int i = 0; i < size; i++){
            rows.add(new CellCollection());
            columns.add(new CellCollection());
            regions.add(new CellCollection());
        }
    }

    public void addCell(int value, int rowNum, int colNum){
        Cell newCell = new Cell(value, String.valueOf(rowNum + "," + colNum));
        rows.get(rowNum).add(newCell);
        columns.get(colNum).add(newCell);
        regions.get(getRegionNumber(rowNum,colNum)).add(newCell);
    }

    private int getRegionNumber(int rowNum, int colNum){
        int regionRow =  Math.floorDiv(rowNum, sizeRoot);
        int regionCol =  Math.floorDiv(colNum, sizeRoot);
        return regionRow * sizeRoot + regionCol;
    }

    public List<CellCollection> getRegions() {
        return regions;
    }

    public List<CellCollection> getColumns() {
        return columns;
    }

    public List<CellCollection> getRows() {
        return rows;
    }

    //TODO - implement getBoard
    public Cell[][] getBoard(){
        Cell newBoard[][] = new Cell[9][9];
        return  newBoard;
    }


    //TODO
    @Override
    public String toString(){

        return null;
    }

}
