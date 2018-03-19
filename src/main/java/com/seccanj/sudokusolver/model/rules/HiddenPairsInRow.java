package com.seccanj.sudokusolver.model.rules;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.HypotheticLine;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class HiddenPairsInRow implements Rule {

    private static final Logger logger = LogManager.getLogger(HiddenPairsInRow.class);

    /**
	 * See {@linkplain <a href="https://www.sudokuoftheday.com/techniques/hidden-pairs-triples/">Hidden Pairs</a>}
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + HiddenPairsInRow.class.getName() + " for number " + n + " on square "
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
					Set<Integer> maybeCols = new HashSet<>();
					
					for (int col = 0; col < 9; col++) {
						if (board.getHypothesis(currRow, col).contains(hypotheticNum)) {
							maybeCols.add(col);
						}
					}
					
					if (maybeCols.size() == 2) {
						for (int hypotheticOtherNum : hypotheticLine) {
							if (hypotheticOtherNum != hypotheticNum) {
								boolean match = true;
								
								for (int col = 0; col < 9; col++) {
									if (board.getHypothesis(currRow, col).contains(hypotheticNum) ^
										board.getHypothesis(currRow, col).contains(hypotheticOtherNum)) {
										
										match = false;
										
										break;
									}
								}
								
								if (match) {
									for (int col = 0; col < 9; col++) {
										if (maybeCols.contains(col)) {
											if (board.getHypothesis(currRow, col).size() != 2) {
												board.resetAllHypothesis(currRow, col);
												board.setHypothesis(currRow, col, hypotheticNum);
												board.setHypothesis(currRow, col, hypotheticOtherNum);
												
												result = true;
											}
										} else {
											result |= board.resetHypothesis(currRow, col, hypotheticNum);
											result |= board.resetHypothesis(currRow, col, hypotheticOtherNum);
										}
									}

									if (result) {
										logger.info("--- Match reduction! "+hypotheticNum+" and "+hypotheticOtherNum +
											" in columns (" + maybeCols.stream().map(r -> r+",").reduce("", (a,b)->a+b) + ") and row " + currRow + " ("+HiddenPairsInRow.class.getName()+")");
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
