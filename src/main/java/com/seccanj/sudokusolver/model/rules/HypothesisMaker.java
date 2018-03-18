package com.seccanj.sudokusolver.model.rules;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Line;
import com.seccanj.sudokusolver.model.Square;

public class HypothesisMaker {

    private static final Logger logger = LogManager.getLogger(HypothesisMaker.class);

	/**
	 * Given a square and a number, makes hypothesis on which numbers could be in each
	 * position of the square. Updates the board's hypothesis accordingly.
	 */
	public void makeHypothesis(Board board, int squareIdx, Square square, int n) {
		int firstRow = square.getFirstRowIdx();
		int firstCol = square.getFirstColIdx();
		
		List<Line> rows = square.getRows(board);
		List<Line> cols = square.getCols(board);
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (square.getValue(i, j) == 0 && !rows.get(i).contains(n) && !cols.get(j).contains(n) && !square.contains(n)) {
					board.setHypothesis(firstRow + i, firstCol + j, n);
					logger.info("Number " + n + " could be in ["+(firstRow + i)+", "+(firstCol + j)+"]");
				} else {
					board.resetHypothesis(firstRow + i, firstCol + j, n);
				}
			}
		}
	}
}
