package com.seccanj.sudokusolver.model.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.HypotheticLine;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SingleHypothesis implements Rule {

    private static final Logger logger = LogManager.getLogger(SingleHypothesis.class);

	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		logger.debug("Matching "+SingleHypothesis.class.getName()+" for number " + n + " on square "+squareIdx+" ...");

		return match(board);
	}

	public boolean match(Board board) {
		boolean hasChanges = false;
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				
				HypotheticLine h = board.getHypothesis(i, j);
				
				if (h.size() == 1) {
					int num = h.getFirstValue();
					
					logger.info("--- Match! ["+(i)+"]["+(j)+"] = "+num + " ("+SingleHypothesis.class.getName()+")");

					board.setValue(i, j, num);
					
					hasChanges = true;
				}
			}
		}
		
		return hasChanges;
	}

}
