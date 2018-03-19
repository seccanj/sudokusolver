package com.seccanj.sudokusolver.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Square;
import com.seccanj.sudokusolver.model.rules.HypothesisMaker;
import com.seccanj.sudokusolver.model.rules.RuleDictionary;
import com.seccanj.sudokusolver.model.rules.SingleCandidate;

public class SudokuSolver {

    private static final Logger logger = LogManager.getLogger(SudokuSolver.class);

    public static void main(String[] args) {
		
		RuleDictionary rules = new RuleDictionary();
		
		Board board = Board.initRadom();
		
		logger.info("\nInitial board:\n"+board.toString());

		logger.info("\n");

		// Algorithm:
		//   For each number 1-9, set hypothesis
		//   For each number 1-9, launch all rules, until nothing gets solved

		HypothesisMaker h = new HypothesisMaker();
		for (int num=1; num<=9; num++) {
			Square[] squares = board.getSquares();

			for (int s=0; s<9; s++) {
				int squareIdx = s;
				
				if (!squares[s].contains(num)) {
					h.makeHypothesis(board, squareIdx, squares[squareIdx], num);
				}
			}
		}
		
		boolean hasChanges;
		
		do {
			hasChanges = false;
			
			hasChanges |= new SingleCandidate().match(board);
			
			for (int num=1; num<=9; num++) {
				int val = num;
				
				for (int s=0; s<9; s++) {
					int squareIdx = s;
					
					if (!board.getSquares()[s].contains(val)) {
						 hasChanges |= rules.getRules().stream()
							 .map(r -> r.match(board, squareIdx, board.getSquares()[squareIdx], val))
							 .reduce(false, (p, c) -> p || c);
					}
				}
			}
		} while (hasChanges);
		
		/*
		rules.getRules().forEach(r -> {
			r.match(b);	
		});
		*/

		logger.info("\nFinal board:\n"+board.toString());
		
		/*
		logger.trace("\n\n\n");

		Arrays.stream(b.getCols()).forEach(s -> logger.trace(s.toString()));
		
		logger.trace("\n\n\n");

		Arrays.stream(b.getSquares()).forEach(s -> logger.trace(s.toString()));
		*/
	}
}
