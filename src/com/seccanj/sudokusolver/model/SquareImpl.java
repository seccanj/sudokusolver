package com.seccanj.sudokusolver.model;

import java.util.ArrayList;
import java.util.List;

public class SquareImpl extends LineImpl implements Square {

	public int squareIdx;
	
	public SquareImpl(int squareIdx) {
		super();
		this.squareIdx = squareIdx;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// Arrays.stream(line).forEach(n -> sb.append(n + ", "));
		
		sb.append("\n-------------\n");
		for (int i=0; i<3; i++) {

			sb.append("|");
			
			for (int j=0; j<3; j++) {
				
				int v = line[i*3 + j];
				sb.append(v == 0 ? "   " : " "+String.valueOf(v)+" ");

				sb.append("|");
			}
			
			sb.append("\n-------------\n");
		}
		
		sb.append("\n\n");

		return sb.toString();
	}

	public int getValue(int i, int j) {
		return line[i*3 + j];
	}

	public void setValue(int i, int j, int value) {
		line[i*3 + j] = value;
	}
	
	@Override
	public List<Line> getRows(Board board) {
		int firstRow = getFirstRowIdx();
		
		List<Line> rows = new ArrayList<>();
		rows.add(board.row(firstRow));
		rows.add(board.row(firstRow+1));
		rows.add(board.row(firstRow+2));
		
		return rows;
	}

	@Override
	public List<Line> getCols(Board board) {
		int firstCol = getFirstColIdx();
		
		List<Line> cols = new ArrayList<>();
		cols.add(board.col(firstCol));
		cols.add(board.col(firstCol+1));
		cols.add(board.col(firstCol+2));
		
		return cols;
	}

	@Override
	public int getFirstRowIdx() {
		return ((int)squareIdx / 3) * 3;
		
	}

	@Override
	public int getFirstColIdx() {
		return ((int)squareIdx % 3) * 3;
	}
	
	@Override
	public int getValueHypotesis(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHypotesis(int i, int j, int value) {
		// TODO Auto-generated method stub
		
	}
}
