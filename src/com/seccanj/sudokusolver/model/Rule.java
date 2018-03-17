package com.seccanj.sudokusolver.model;

public interface Rule {

	public boolean match(Board board, int squareIdx, Square square, int n);

}
