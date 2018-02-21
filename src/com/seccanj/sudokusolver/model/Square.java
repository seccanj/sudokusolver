package com.seccanj.sudokusolver.model;

public interface Square extends Line {

	public int getValue(int i, int j);

	public void setValue(int i, int j, int value);
	
	public int getValueHypotesis(int i, int j);

	public void setHypotesis(int i, int j, int value);
	
}
