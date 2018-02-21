package com.seccanj.sudokusolver.model.rules;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;

public class SampleRule implements Rule {

	@Override
	public Board match(Board board) {
		System.out.println("Matching "+SampleRule.class.getName()+"...");
		return board;
	}
	
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		System.out.println("Matching "+SampleRule.class.getName()+"...");
		
		return false;
	}

}
