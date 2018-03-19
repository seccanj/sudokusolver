package com.seccanj.sudokusolver.model.rules;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.HypotheticLine;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class HiddenPairsInColumn implements Rule {

    private static final Logger logger = LogManager.getLogger(HiddenPairsInColumn.class);

    /**
	 * See {@linkplain <a href="https://www.sudokuoftheday.com/techniques/hidden-pairs-triples/">Hidden Pairs</a>}
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + HiddenPairsInColumn.class.getName() + " for number " + n + " on square "
				+ squareIdx + " ...");

		boolean result = false;

		int firstRow = square.getFirstRowIdx();
		int firstCol = square.getFirstColIdx();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int currRow = firstRow + i;
				int currCol = firstCol + j;

				HypotheticLine hypotheticLine = board.getHypothesis(currRow, currCol);
				
				for (int hypotheticNum : hypotheticLine) {
					Set<Integer> maybeRows = new HashSet<>();
					
					for (int row = 0; row < 9; row++) {
						if (board.getHypothesis(row, currCol).contains(hypotheticNum)) {
							maybeRows.add(row);
						}
					}
					
					if (maybeRows.size() == 2) {
						for (int hypotheticOtherNum : hypotheticLine) {
							if (hypotheticOtherNum != hypotheticNum) {
								boolean match = true;
								
								for (int row = 0; row < 9; row++) {
									if (board.getHypothesis(row, currCol).contains(hypotheticNum) ^
										board.getHypothesis(row, currCol).contains(hypotheticOtherNum)) {
										
										match = false;
										
										break;
									}
								}
								
								if (match) {
									for (int row = 0; row < 9; row++) {
										if (maybeRows.contains(row)) {
											if (board.getHypothesis(row, currCol).size() != 2) {
												board.resetAllHypothesis(row, currCol);
												board.setHypothesis(row, currCol, hypotheticNum);
												board.setHypothesis(row, currCol, hypotheticOtherNum);
												
												result = true;
											}
										} else {
											result |= board.resetHypothesis(row, currCol, hypotheticNum);
											result |= board.resetHypothesis(row, currCol, hypotheticOtherNum);
										}
									}
											

									if (result) {
										logger.info("--- Match reduction! "+hypotheticNum+" and "+hypotheticOtherNum +
											" in rows (" + maybeRows.stream().map(r -> r+",").reduce("", (a,b)->a+b) + ") and column " + currCol + " ("+HiddenPairsInColumn.class.getName()+")");
									}
								}
							}
						}
					}
				}
			}
		}

		return result;
	}

}
