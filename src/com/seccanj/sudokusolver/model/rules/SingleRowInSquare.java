package com.seccanj.sudokusolver.model.rules;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SingleRowInSquare implements Rule {

	/**
	 * Given a square and a number, if in the square there are hypothesis for the number only on a
	 * row remove hypothesis for the number from the same row in other squares.
	 * Same for a column.
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		System.out.println("Matching " + SingleRowInSquare.class.getName() + " for number " + n + " on square "
				+ squareIdx + " ...");

		boolean result = false;

		int firstRow = square.getFirstRowIdx();
		int firstCol = square.getFirstColIdx();

		int numRowWithHypothesys = 0;
		int rowWithHypothesis = -1;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.getHypothesis(firstRow + i, firstCol + j).contains(n)) {
					numRowWithHypothesys++;
					rowWithHypothesis = firstRow + i;
					break;
				}
			}
		}

		if (numRowWithHypothesys == 1) {
			for (int j=0; j<9; j++) {
				if (j < firstCol || j >= firstCol + 3) {
					// Remove hypothesis for every number on same row but different square
					result |= board.resetHypothesis(rowWithHypothesis, j, n);
				}
			}

			if (result) {
				System.out.println("--- Match reduction! on row " + rowWithHypothesis + " in square "+squareIdx+" for number "+n+" (SingleRowInSquare)");
			}
		}
		
		return result;
	}

}
