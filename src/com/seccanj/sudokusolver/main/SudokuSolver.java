package com.seccanj.sudokusolver.main;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Square;
import com.seccanj.sudokusolver.model.rules.RuleDictionary;

public class SudokuSolver {

	
	public static void main(String[] args) {
		
		RuleDictionary rules = new RuleDictionary();
		
		Board board = Board.initRadom();
		
		System.out.println(board.toString());

		System.out.println("\n");

		boolean hasChanges;
		
		do {
			hasChanges = false;
			
			for (int num=1; num<=9; num++) {
				Square[] squares = board.getSquares();
				
				int val = num;
				
				for (int s=0; s<9; s++) {
					int squareIdx = s;
					
					 if (!squares[s].contains(val)) {
						 hasChanges |= rules.getRules().stream()
							 .map(r -> r.match(board, squareIdx, squares[squareIdx], val))
							 .reduce(false, (previous, current) -> previous || current);
					 }				
				}
			}
		} while (hasChanges);
		
		/*
		rules.getRules().forEach(r -> {
			r.match(b);	
		});
		*/

		System.out.println(board.toString());
		
		/*
		System.out.println("\n\n\n");

		Arrays.stream(b.getCols()).forEach(s -> System.out.println(s.toString()));
		
		System.out.println("\n\n\n");

		Arrays.stream(b.getSquares()).forEach(s -> System.out.println(s.toString()));
		*/
	}
}
