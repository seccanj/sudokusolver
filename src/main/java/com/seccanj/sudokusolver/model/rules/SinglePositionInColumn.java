package com.seccanj.sudokusolver.model.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SinglePositionInColumn implements Rule {

    private static final Logger logger = LogManager.getLogger(SinglePositionInColumn.class);

	/**
	 * Given a square and a number, examines all columns in the square and determines whether there is only one possible
	 * position in the column for the given number. If so, sets the number on the board.
	 * See also {@linkplain <a href="https://www.sudokuoftheday.com/techniques/single-position/">Single Position</a>}
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + SinglePositionInColumn.class.getName() + " for number " + n + " on square "
				+ squareIdx + " ...");

		boolean result = false;

		int firstColumn = square.getFirstColIdx();

		int maybeRow = -1;
		int maybeCol = -1;

		for (int j = 0; j < 3; j++) {
			int col = firstColumn + j;
			
			if (!board.col(col).contains(n)) {
				int numImpossible = 0;

				for (int row = 0; row < 9; row++) {
					if (board.getValue(row, col) != 0 || board.getSquare(row, col).contains(n) || board.row(row).contains(n)) {
						numImpossible++;
					} else {
						maybeRow = row;
						maybeCol = col;
					}
				}

				if (numImpossible == 8) {
					logger.info("--- Match! [" + (maybeRow) + "][" + (maybeCol) + "] = " + n + " ("+SinglePositionInColumn.class.getName()+")");

					board.setValue(maybeRow, maybeCol, n);

					result = true;
				}
			}
		}


		return result;
	}

}
