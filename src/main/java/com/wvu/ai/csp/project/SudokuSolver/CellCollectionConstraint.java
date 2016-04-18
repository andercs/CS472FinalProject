package com.wvu.ai.csp.project.SudokuSolver;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CellCollectionConstraint implements Constraint {

	private List<Variable> scope;

	public CellCollectionConstraint(Variable... variables) {
		scope = Arrays.asList(variables);
	}

	public CellCollectionConstraint(List<Variable> variables) {
		scope = variables;
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		boolean constraintSatisfied = true;
		Set<Integer> variableAssignments = new HashSet<>();

		for (Variable variable : scope) {
			Object varValue = assignment.getAssignment(variable);
			if (varValue instanceof Integer) {
				Integer varInt = (Integer)varValue;
				if (!variableAssignments.add(varInt) && varInt != 0) {
					constraintSatisfied = false;
					break;
				}
			}
		}

		return constraintSatisfied;
	}

}
