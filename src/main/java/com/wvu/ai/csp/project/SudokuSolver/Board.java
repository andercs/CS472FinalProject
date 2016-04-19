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

	public Board(int size) {
		this.size = size;
		sizeRoot = (int)Math.sqrt(size);
		rows = new ArrayList<CellCollection>();
		columns = new ArrayList<CellCollection>();
		regions = new ArrayList<CellCollection>();
		populateBoard();
	}

	private void populateBoard() {
		for (int i = 0; i < size; i++) {
			rows.add(new CellCollection());
			columns.add(new CellCollection());
			regions.add(new CellCollection());
		}
	}

	public void addCell(int value, int rowNum, int colNum) {
		Cell newCell = new Cell(value, String.valueOf(rowNum + "," + colNum));
		rows.get(rowNum).add(newCell);
		columns.get(colNum).add(newCell);
		regions.get(getRegionNumber(rowNum, colNum)).add(newCell);
	}

	private int getRegionNumber(int rowNum, int colNum) {
		int regionRow = Math.floorDiv(rowNum, sizeRoot);
		int regionCol = Math.floorDiv(colNum, sizeRoot);
		return regionRow * sizeRoot + regionCol;
	}

	public int getSize() {
		return size;
	}

	public Cell getCell(int rowNum, int colNum) {
		return rows.get(rowNum).getCollection().get(colNum);
	}

	public List<CellCollection> getRegions() {
		return regions;
	}

	public CellCollection getRegion(int regionNumber) {
		return regions.get(regionNumber);
	}

	public List<CellCollection> getColumns() {
		return columns;
	}

	public CellCollection getColumn(int columnNumber) {
		return columns.get(columnNumber);
	}

	public List<CellCollection> getRows() {
		return rows;
	}

	public CellCollection getRow(int rowNumber) {
		return rows.get(rowNumber);
	}

	@Override
	public String toString() {
		String stringValue = "";
		for (int i = 0; i < size; i++) {
			if (i % (size / sizeRoot) == 0 && i != 0) {
				stringValue += "\n";
			}
			for (int j = 0; j < size; j++) {
				if (j % (size / sizeRoot) == 0 && j != 0) {
					stringValue += "\t";
				}
				int currentCellValue = rows.get(i).getCollection().get(j).getValue();
				if (currentCellValue == 0) {
					stringValue += "_ ";
				} else {
					stringValue += rows.get(i).getCollection().get(j).getValue() + " ";
				}
			}
			stringValue += "\n";
		}
		return stringValue;
	}

}
