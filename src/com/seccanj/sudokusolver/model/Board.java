package com.seccanj.sudokusolver.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {
	public int[][] numbers;

	public Board() {}; 
	
	public Board(int rows, int cols) {
		numbers = new int[rows][];

		for (int i = 0; i < rows; i++) {
			numbers[i] = new int[cols];
		}
	}

	public static Board initRadom() {
		Board b = new Board();
		
		/*
		b.numbers = new int[][] {
			{5, 3, 0, 0, 7, 0, 0, 0, 0},
			{6, 0, 0, 1, 9, 5, 0, 0, 0},
			{0, 9, 8, 0, 0, 0, 0, 6, 0},
			{8, 0, 0, 0, 6, 0, 0, 0, 3},
			{4, 0, 0, 8, 0, 3, 0, 0, 1},
			{7, 0, 0, 0, 2, 0, 0, 0, 6},
			{0, 6, 0, 0, 0, 0, 2, 8, 0},
			{0, 0, 0, 4, 1, 9, 0, 0, 5},
			{0, 0, 0, 0, 8, 0, 0, 7, 9}				
		};
		*/

		b.numbers = new int[][] {
			{0, 0, 2, 1, 0, 0, 0, 0, 6},
			{7, 0, 5, 0, 2, 0, 0, 1, 0},
			{0, 3, 1, 0, 0, 6, 2, 0, 0},
			{4, 0, 0, 5, 0, 0, 1, 9, 0},
			{5, 0, 9, 0, 0, 3, 0, 0, 7},
			{2, 8, 0, 7, 1, 9, 0, 0, 0},
			{0, 2, 0, 0, 5, 1, 0, 0, 9},
			{6, 5, 0, 0, 0, 0, 7, 3, 1},
			{0, 9, 8, 3, 0, 0, 0, 4, 0}				
		};
		
		return b;
	}
	
	
	public Line row(int index) {
		return new LineImpl(numbers[index]);
	}

	public Line col(int index) {
		LineImpl col = new LineImpl();

		for (int i=0; i<9; i++) {
			col.line[i] = numbers[i][index];
		}
		
		return col;
	}

	public Line[] getCols() {
		Line[] cols = new Line[9];

		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (cols[j] == null) {
					cols[j] = new LineImpl();
				}
				
				((LineImpl)cols[j]).line[i] = numbers[i][j];
			}
		}
	
		return cols;
	}
	
	public Iterable<Line> iterRows() {
		return Arrays.stream(numbers).map(r -> new LineImpl(r)).collect(Collectors.toList());
	}

	public Iterable<Line> iterCols() {
		return Arrays.asList(getCols());
	}

	public Square[] getSquares() {
		Square[] squares = new Square[9];

		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				int idx = ((int)(i / 3)) * 3 + ((int)(j / 3));
				if (squares[idx] == null) {
					squares[idx] = new SquareImpl();
				}
				
				((SquareImpl)squares[idx]).line[(i % 3)*3 + (j % 3)] = numbers[i][j];
			}
		}
	
		return squares;
	}
	
	public Iterable<Square> iterSquares() {
		return Arrays.asList(getSquares());
	}

	/*
	public Iterable<Line> iterRowsAndCols() {
	}

	public Iterable<Square> iterSquares() {
	}
	*/

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("-------------------------------------\n");
		iterRows().forEach(l -> {
			sb.append(l.toString());
			sb.append("\n");
			sb.append("-------------------------------------\n");
		});

		return sb.toString();
	}

}
