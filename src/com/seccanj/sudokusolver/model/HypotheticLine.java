package com.seccanj.sudokusolver.model;

public interface HypotheticLine extends Line {
	
	void setHypotesis(int n);
	
	boolean resetHypotesis(int n);
	
}
