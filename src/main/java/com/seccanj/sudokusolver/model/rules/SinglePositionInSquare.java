package com.seccanj.sudokusolver.model.rules;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Line;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SinglePositionInSquare implements Rule {

    private static final Logger logger = LogManager.getLogger(SinglePositionInSquare.class);

	/**
	 * Given a square and a number, determines whether there is only one possible
	 * position in the square for the given number. If so, sets the number on the
	 * board.
	 * See also {@linkplain <a href="https://www.sudokuoftheday.com/techniques/single-position/">Single Position</a>}
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + SinglePositionInSquare.class.getName() + " for number " + n + " on square "
				+ squareIdx + " ...");

		boolean result = false;

		int firstRow = square.getFirstRowIdx();
		int firstCol = square.getFirstColIdx();

		List<Line> rows = square.getRows(board);
		List<Line> cols = square.getCols(board);

		int numImpossible = 0;
		int maybeRow = -1;
		int maybeCol = -1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.getValue(firstRow + i, firstCol + j) != 0 || square.contains(n) || rows.get(i).contains(n) || cols.get(j).contains(n)) {
					numImpossible++;
				} else {
					maybeRow = firstRow + i;
					maybeCol = firstCol + j;
				}
			}
		}

		if (numImpossible == 8) {
			logger.info("--- Match! [" + (maybeRow) + "][" + (maybeCol) + "] = " + n + " ("+SinglePositionInSquare.class.getName()+")");

			board.setValue(maybeRow, maybeCol, n);

			result = true;
		}

		return result;
	}

}
