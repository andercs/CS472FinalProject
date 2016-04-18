package com.wvu.ai.csp.project.SudokuSolver;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import java.util.LinkedList;
import java.util.List;

public class SudokuCSP extends CSP {

	private final Object[] domainValues;

	public SudokuCSP(Board sudokuBoard) {
		LinkedList<Object> possibleValues = new LinkedList<>();
		for (int i = 1; i <= sudokuBoard.getSize(); i++) {
			possibleValues.add(i);
		}
		domainValues = possibleValues.toArray(new Object[possibleValues.size()]);
		loadVariables(sudokuBoard);
		generateRowConstraints(sudokuBoard);
		generateColumnConstraints(sudokuBoard);
		generateRegionConstraints(sudokuBoard);
	}

	private void loadVariables(Board sudokuBoard) {
		for (CellCollection row : sudokuBoard.getRows()) {
			for (Cell cell : row.getCollection()) {
				Variable newVariable = new Variable(cell.getName());
				addVariable(newVariable);
				if (cell.isAssigned()) {
					setDomain(newVariable, new Domain(new Object[]{}));
				} else {
					setDomain(newVariable, new Domain(domainValues));
				}
			}
		}
	}

	private void generateRowConstraints(Board sudokuBoard) {
		List<Variable> cellVariables = getVariables();
		int sudokuBoardSideSize = sudokuBoard.getSize();
		for (int rowNum = 0; rowNum < cellVariables.size(); rowNum += sudokuBoardSideSize) {
			List<Variable> rowVariables = new LinkedList<>();
			for (int colNum = 0; colNum < sudokuBoardSideSize; colNum++) {
				rowVariables.add(cellVariables.get(rowNum + colNum));
			}
			addConstraint(new CellCollectionConstraint(rowVariables));
		}
	}

	private void generateColumnConstraints(Board sudokuBoard) {
		List<Variable> cellVariables = getVariables();
		int sudokuBoardSideSize = sudokuBoard.getSize();
		for (int colNum = 0; colNum < sudokuBoardSideSize; colNum++) {
			List<Variable> colVariables = new LinkedList<>();
			for (int rowNum = 0; rowNum < cellVariables.size(); rowNum += sudokuBoardSideSize) {
				colVariables.add(cellVariables.get(rowNum + colNum));
			}
			addConstraint(new CellCollectionConstraint(colVariables));
		}
	}

	private void generateRegionConstraints(Board sudokuBoard) {
		List<Variable> cellVariables = getVariables();
		int sudokuBoardSideSize = sudokuBoard.getSize();
		int sudokuRegionSideSize = (int)Math.sqrt(sudokuBoardSideSize);
		for (int i = 0; i < cellVariables.size(); i += sudokuBoardSideSize * sudokuRegionSideSize) {
			for (int j = 0; j < sudokuBoardSideSize; j += sudokuRegionSideSize) {
				List<Variable> regionVariables = new LinkedList<>();
				int verticalIteration = 0;
				// ((rowNum / sudokuBoardSideSize) - (sudokuRegionSideSize * verticalIteration))
				for (int rowNum = i; verticalIteration < sudokuRegionSideSize; rowNum += sudokuBoardSideSize) {
					int horizontalIteration = 0;
					// (colNum - (sudokuRegionSideSize * horizontalIteration))
					for (int colNum = j; horizontalIteration < sudokuRegionSideSize; colNum++) {
						regionVariables.add(cellVariables.get(rowNum + colNum));
						horizontalIteration++;
					}
					verticalIteration++;
				}
				addConstraint(new CellCollectionConstraint(regionVariables));
			}
		}
	}

}
