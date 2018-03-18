package com.seccanj.sudokusolver.model.rules;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.HypotheticLine;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class HiddenPairsInRows implements Rule {

    private static final Logger logger = LogManager.getLogger(HiddenPairsInRows.class);

    /**
	 * See {@linkplain https://www.sudokuoftheday.com/techniques/hidden-pairs-triples/}
	 */
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching " + HiddenPairsInRows.class.getName() + " for number " + n + " on square "
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
									result |= maybeCols.stream()
										.map(c -> {
											if (board.getHypothesis(currRow, c).size() != 2) {
												
												board.resetAllHypothesis(currRow, c);
												board.setHypothesis(currRow, c, hypotheticNum);
												board.setHypothesis(currRow, c, hypotheticOtherNum);
	
												return true;
											}
											
											return false;
										})
										.reduce(false, (p, c) -> p | c);

									if (result) {
										logger.info("--- Match reduction! "+hypotheticNum+" and "+hypotheticOtherNum +
											" in columns (" + maybeCols.stream().map(r -> r+",").reduce("", (a,b)->a+b) + ") and row " + currRow + " ("+HiddenPairsInRows.class.getName()+")");
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
