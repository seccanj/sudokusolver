package com.seccanj.sudokusolver.main;

import java.util.List;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;
import com.seccanj.sudokusolver.model.rules.RuleDictionary;

public class SudokuSolver {

	
	public static void main(String[] args) {
		
		RuleDictionary rules = new RuleDictionary();
		
		Board b = Board.initRadom();
		
		System.out.println(b.toString());

		System.out.println("\n");

		boolean hasChanges = false;
		
		do {
			hasChanges = false;
			
			for (int i=1; i<=9; i++) {
				Square[] squares = b.getSquares();
				
				int val = i;
				
				for (int s=0; s<9; s++) {
					 if (!squares[s].contains(val)) {
						 List<Rule> allRules = rules.getRules();
						 
						 for (int r=0; r<allRules.size(); r++) {
							 hasChanges = hasChanges | allRules.get(r).match(b, s, squares[s], val);
						 }
					 }				
				}
			}
		} while (hasChanges);
		
		/*
		rules.getRules().forEach(r -> {
			r.match(b);	
		});
		*/

		System.out.println(b.toString());
		
		/*
		System.out.println("\n\n\n");

		Arrays.stream(b.getCols()).forEach(s -> System.out.println(s.toString()));
		
		System.out.println("\n\n\n");

		Arrays.stream(b.getSquares()).forEach(s -> System.out.println(s.toString()));
		*/
	}
	

}
