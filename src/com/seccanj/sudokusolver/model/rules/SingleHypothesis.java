package com.seccanj.sudokusolver.model.rules;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.HypotheticLine;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SingleHypothesis implements Rule {

	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		System.out.println("Matching "+SingleHypothesis.class.getName()+" for number " + n + " on square "+squareIdx+" ...");

		return match(board);
	}

	public boolean match(Board board) {
		boolean hasChanges = false;
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				
				HypotheticLine h = board.getHypothesis(i, j);
				
				if (h.size() == 1) {
					int num = h.getFirstValue();
					
					System.out.println("--- Match! ["+(i)+"]["+(j)+"] = "+num + " (SingleHypothesis)");

					board.setValue(i, j, num);
					
					hasChanges = true;
				}
			}
		}
		
		return hasChanges;
	}

}
