package com.seccanj.sudokusolver.model.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SingleColumnInSquare implements Rule {

    private static final Logger logger = LogManager.getLogger(SingleColumnInSquare.class);

	/**
	 * Given a square and a number, if in the square there are hypothesis for the number only on a
	 * column remove hypothesis for the number from the same column in other squares.
	 * Same for a row, see {@link SingleRowInSquare}.
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + SingleColumnInSquare.class.getName() + " for number " + n + " on square "
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
					columnWithHypothesis = firstCol + j;
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
			logger.info("--- Match reduction! on column " + columnWithHypothesis + " in square "+squareIdx+" for number "+n+" (" + SingleColumnInSquare.class.getName() + ")");
		}
		
		return result;
	}

}
