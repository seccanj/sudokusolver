package com.seccanj.sudokusolver.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.seccanj.sudokusolver.model.Board;
import com.seccanj.sudokusolver.model.Line;
import com.seccanj.sudokusolver.model.Rule;
import com.seccanj.sudokusolver.model.Square;
import com.seccanj.sudokusolver.model.SquareImpl;

public class MandatoryPosition implements Rule {

	@Override
	public Board match(Board board) {

		System.out.println("Matching "+MandatoryPosition.class.getName()+"...");
		
		return board;
	}
	
	@Override
	public boolean match(Board board, int squareIdx, Square square, int n) {
		System.out.println("Matching "+MandatoryPosition.class.getName()+" for number " + n + " on square "+squareIdx+" ...");

		boolean result = false;
		
		SquareImpl hypothesis = new SquareImpl();
		
		int firstRow = ((int)squareIdx / 3) * 3;
		int firstCol = ((int)squareIdx % 3) * 3;
		
		List<Line> rows = new ArrayList<>();
		rows.add(board.row(firstRow));
		rows.add(board.row(firstRow+1));
		rows.add(board.row(firstRow+2));
		
		List<Line> cols = new ArrayList<>();
		cols.add(board.col(firstCol));
		cols.add(board.col(firstCol+1));
		cols.add(board.col(firstCol+2));
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (square.getValue(i, j) != 0 || rows.get(i).contains(n) || cols.get(j).contains(n)) {
					hypothesis.setHypotesis(i, j, -1);
				}
			}
		}

		int numImpossible = 0;
		int maybeRow = -1;
		int maybeCol = -1;
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (hypothesis.getValueHypotesis(i, j) == -1) {
					numImpossible++;
				} else {
					maybeRow = i;
					maybeCol = j;
				}
			}
		}
		
		if (numImpossible == 8) {
			board.numbers[firstRow + maybeRow][firstCol + maybeCol] = n;
			
			System.out.println("--- Match! ["+(firstRow + maybeRow)+"]["+(firstCol + maybeCol)+"] = "+n);
			
			result = true;
		}
		
		return result;
	}

}
