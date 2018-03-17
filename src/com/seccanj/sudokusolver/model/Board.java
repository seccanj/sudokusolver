package com.seccanj.sudokusolver.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {
	private int[][] numbers;
	private HypotheticLine[][] hypothesis;

	public Board(int rows, int cols) {
		numbers = new int[rows][];

		for (int i = 0; i < rows; i++) {
			numbers[i] = new int[cols];
		}

		hypothesis = new HypotheticLine[rows][];

		for (int i=0; i<rows; i++) {
			
			hypothesis[i] = new HypotheticLine[cols];
			
			for (int j=0; j<cols; j++) {
				hypothesis[i][j] = new HypotheticLineImpl();
			}
		}
	}

	public static Board initRadom() {
		Board b = new Board(9, 9);
		
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
					squares[idx] = new SquareImpl(idx);
				}
				
				((SquareImpl)squares[idx]).line[(i % 3)*3 + (j % 3)] = numbers[i][j];
			}
		}
	
		return squares;
	}
	
	public Square getSquare(int row, int col) {
		int squareIdx = ((int)(row / 3)) * 3 + ((int)(col / 3));
	
		return getSquares()[squareIdx];
	}
	
	public Iterable<Square> iterSquares() {
		return Arrays.asList(getSquares());
	}

	public int getValue(int row, int col) {
		return numbers[row][col];
	}

	public void setValue(int row, int col, int n) {
		numbers[row][col] = n;
		
		for (int i=0; i<9; i++) {
			// Remove hypothesis for every number on the set cell
			resetHypothesis(row, col, i+1);
			
			// Remove hypothesis for the set number on all cells in the same row
			resetHypothesis(row, i, n);
			
			// Remove hypothesis for the set number on all cells in the same column
			resetHypothesis(i, col, n);
		}

		Square square = getSquare(row, col);

		// Remove hypothesis for the set number on all cells in the same square
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				resetHypothesis(square.getFirstRowIdx() + i, square.getFirstColIdx() + j, n);
			}
		}

		System.out.println(toString());
	}
	
	public boolean containsHypothesis(int row, int col, int n) {
		return hypothesis[row][col].contains(n);
	}

	public HypotheticLine getHypothesis(int row, int col) {
		return hypothesis[row][col];
	}

	public void setHypothesis(int row, int col, int n) {
		hypothesis[row][col].setHypotesis(n);
	}
	
	public void resetHypothesis(int row, int col, int n) {
		hypothesis[row][col].resetHypotesis(n);
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
