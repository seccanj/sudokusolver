package com.seccanj.sudokusolver.model.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SinglePositionInRow implements Rule {

    private static final Logger logger = LogManager.getLogger(SinglePositionInRow.class);

	/**
	 * Given a square and a number, examines all rows in the square and determines whether there is only one possible
	 * position in the row for the given number. If so, sets the number on the board.
	 * See also {@linkplain <a href="https://www.sudokuoftheday.com/techniques/single-position/">Single Position</a>}
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + SinglePositionInRow.class.getName() + " for number " + n + " on square "
				+ squareIdx + " ...");

		boolean result = false;

		int firstRow = square.getFirstRowIdx();

		int maybeRow = -1;
		int maybeCol = -1;

		for (int i = 0; i < 3; i++) {
			int row = firstRow + i;
			
			if (!board.row(row).contains(n)) {
				int numImpossible = 0;

				for (int col = 0; col < 9; col++) {
					if (board.getValue(row, col) != 0 || board.getSquare(row, col).contains(n) || board.col(col).contains(n)) {
						numImpossible++;
					} else {
						maybeRow = row;
						maybeCol = col;
					}
				}

				if (numImpossible == 8) {
					logger.info("--- Match! [" + (maybeRow) + "][" + (maybeCol) + "] = " + n + " ("+SinglePositionInRow.class.getName()+")");

					board.setValue(maybeRow, maybeCol, n);

					result = true;
				}
			}
		}


		return result;
	}

}
