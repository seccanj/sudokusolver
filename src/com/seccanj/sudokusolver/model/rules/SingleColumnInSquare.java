package com.seccanj.sudokusolver.model.rules;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SingleColumnInSquare implements Rule {

	/**
	 * Given a square and a number, if in the square there are hypothesis for the number only on a
	 * row remove hypothesis for the number from the same row in other squares.
	 * Same for a column.
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		System.out.println("Matching " + SingleColumnInSquare.class.getName() + " for number " + n + " on square "
				+ squareIdx + " ...");

		boolean result = false;

		int firstRow = square.getFirstRowIdx();
		int firstCol = square.getFirstColIdx();

		int numColumnWithHypothesys = 0;
		int columnWithHypothesis = -1;
		
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				if (board.getHypothesis(firstRow + i, firstCol + j).contains(n)) {
					numColumnWithHypothesys++;
					columnWithHypothesis = firstCol + i;
					break;
				}
			}
		}

		if (numColumnWithHypothesys == 1) {
			for (int i=0; i<9; i++) {
				if (i < firstRow || i >= firstRow + 3) {
					// Remove hypothesis for every number on same column but different square
					result |= board.resetHypothesis(i, columnWithHypothesis, n);
				}
			}
		}

		if (result) {
			System.out.println("--- Match reduction! on column " + columnWithHypothesis + " in square "+squareIdx+" for number "+n+" (SingleColumnInSquare)");
		}
		
		return result;
	}

}
