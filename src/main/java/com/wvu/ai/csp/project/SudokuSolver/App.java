package com.wvu.ai.csp.project.SudokuSolver;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.BacktrackingStrategy;
import aima.core.search.csp.CSP;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.MinConflictsStrategy;
import aima.core.search.csp.Variable;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Shane Hogan on 4/9/16.
 */
public class App {
	private static int SIZE;
	private static int MIN_CONFLICTS_MAX_STEPS = 1000000;
	private static Scanner KBD = new Scanner(System.in);

	public static void main(String[] args) {
		Board sudokuBoard;
		int possibleSize;
		boolean validSize = false;

		System.out.println();
		System.out.println("What size sudoku board would you like? Enter in a number that is a perfect square.\n"
				+ "For example, the normal sudoku board is a 9 by 9 board, so you would enter the number '9'.");
		while (!validSize) {

			possibleSize = KBD.nextInt();
			if ((long)Math.sqrt(possibleSize) * (long)Math.sqrt(possibleSize) == possibleSize) {
				SIZE = possibleSize;
				validSize = true;
			} else {
				validSize = false;
				System.out.print("That size is not a perfect square, please select a different size.\n");
			}
		}

		System.out.println();
		System.out.println("Create your sudoku table by entering the cells. " + "\nEnter the entire top row then hit enter." + "\nEach cell should contain only a digit between 1-"
				+ SIZE + ". " + "\nIf the cell is meant to be blank, enter a zero.");
		sudokuBoard = generateUserSudokuBoard();
		System.out.println();

		System.out.println("Your board:");
		System.out.print(sudokuBoard.toString());
		System.out.println();

		CSP sudokuCSP = new SudokuCSP(sudokuBoard);

		System.out.println("Simple Backtracking Search Algorithm:");

		Assignment partialAssignment1 = constructPartialAssignment(sudokuCSP, sudokuBoard);
		long startTime = System.currentTimeMillis();
		Assignment completeAssignment1 = new BacktrackingStrategy().solve(sudokuCSP, partialAssignment1);
		long endTime = System.currentTimeMillis();

		System.out.println("Solution reached in: " + (endTime - startTime) + " ms");
		printCompleteAssignment(sudokuCSP, completeAssignment1);
		System.out.println();

		// NOTE: Applying the AC3 Algorithm with the ImprovedBacktrackingSearch is not possible with this library. It specifically
		// requests binary constraints, whereas we have constraints of size n, where n equals the size entered by the user.
		// To work with our constraints, we would need to establish k-consistency where k is equal to the size, n, entered by the
		// user. AC3 offers only 2-consistency (Arc Consistency), and this is far below the usual

		System.out.println("Improved Backtracking Algorithm (Minimum Remaining Values Heuristic):");

		Assignment partialAssignment2 = constructPartialAssignment(sudokuCSP, sudokuBoard);
		startTime = System.currentTimeMillis();
		Assignment completeAssignment2 = new ImprovedBacktrackingStrategy(true, false, false, false).solve(sudokuCSP, partialAssignment2);
		endTime = System.currentTimeMillis();

		System.out.println("Solution reached in: " + (endTime - startTime) + " ms");
		printCompleteAssignment(sudokuCSP, completeAssignment2);
		System.out.println();

		System.out.println("Improved Backtracking Algorithm (Least Constraining Value Heuristic):");

		Assignment partialAssignment3 = constructPartialAssignment(sudokuCSP, sudokuBoard);
		startTime = System.currentTimeMillis();
		Assignment completeAssignment3 = new ImprovedBacktrackingStrategy(false, false, false, true).solve(sudokuCSP, partialAssignment3);
		endTime = System.currentTimeMillis();

		System.out.println("Solution reached in: " + (endTime - startTime) + " ms");
		printCompleteAssignment(sudokuCSP, completeAssignment3);
		System.out.println();

		System.out.println("Improved Backtracking Algorithm (MRV and LCV):");

		Assignment partialAssignment4 = constructPartialAssignment(sudokuCSP, sudokuBoard);
		startTime = System.currentTimeMillis();
		Assignment completeAssignment4 = new ImprovedBacktrackingStrategy(true, false, false, true).solve(sudokuCSP, partialAssignment4);
		endTime = System.currentTimeMillis();

		System.out.println("Solution reached in: " + (endTime - startTime) + " ms");
		printCompleteAssignment(sudokuCSP, completeAssignment4);
		System.out.println();

		System.out.println("MinConflicts Algorithm:");
		Assignment partialAssignment5 = constructPartialAssignment(sudokuCSP, sudokuBoard);
		MinConflictsStrategy iterativeImprovementStrategy = new MinConflictsStrategy(MIN_CONFLICTS_MAX_STEPS);
		Assignment completeAssignment5 = iterativeImprovementStrategy.solve(sudokuCSP, partialAssignment5);

		if (completeAssignment5 != null) {
			printCompleteAssignment(sudokuCSP, completeAssignment5);
		} else {
			System.out.println("No solution could be found using the MinConflicts algorithm in " + MIN_CONFLICTS_MAX_STEPS + " iterations.");
		}
		System.out.println();

		KBD.close();
	}

	private static Board generateUserSudokuBoard() {
		Board newBoard = new Board(SIZE);

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {

				int inputValue = KBD.nextInt();

				if (inputValue >= 0 && inputValue <= SIZE) {
					newBoard.addCell(inputValue, row, col);
				} else {
					// Number is too large, reset everything
					System.out.println("Invalid number passed in. Restarting.");
					row = 0;
					col = 0;
					newBoard = new Board(SIZE);
				}
			}
		}

		return newBoard;
	}

	private static Assignment constructPartialAssignment(CSP sudokuCSP, Board sudokuBoard) {
		Assignment partialAssignment = new Assignment();

		for (Variable sudokuCellVariable : sudokuCSP.getVariables()) {
			String cellName = sudokuCellVariable.getName();
			String[] cellCoordinates = cellName.split(",");
			Cell sudokuCell = sudokuBoard.getCell(Integer.parseInt(cellCoordinates[0]), Integer.parseInt(cellCoordinates[1]));
			if (sudokuCell.isAssigned()) {
				partialAssignment.setAssignment(sudokuCellVariable, sudokuCell.getValue());
			}
		}

		return partialAssignment;
	}

	private static void printCompleteAssignment(CSP sudokuCSP, Assignment completeAssignment) {
		List<Variable> sudokuVariables = sudokuCSP.getVariables();
		int sizeRoot = (int)Math.sqrt(SIZE);
		for (int i = 0; i < sudokuVariables.size(); i += SIZE) {
			if (i % (SIZE * sizeRoot) == 0 && i != 0) {
				System.out.println();
			}
			for (int j = 0; j < SIZE; j++) {
				if (j % (SIZE / sizeRoot) == 0 && j != 0) {
					System.out.print("\t");
				}
				System.out.print(completeAssignment.getAssignment(sudokuVariables.get(i + j)) + " ");
			}
			System.out.println();
		}
	}

}
