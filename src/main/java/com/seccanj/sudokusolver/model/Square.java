package com.seccanj.sudokusolver.model;

import java.util.List;

public interface Square extends Line {

	public int getValue(int i, int j);

	public void setValue(int i, int j, int value);
	
	public List<Line> getRows(Board board);
	
	public List<Line> getCols(Board board);
	
	public int getFirstRowIdx();

	public int getFirstColIdx();
	
}
