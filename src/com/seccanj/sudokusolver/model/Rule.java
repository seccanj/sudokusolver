package com.seccanj.sudokusolver.model;

public interface Rule {

	public Board match(Board b);

	public boolean match(Board board, int squareIdx, Square square, int n);

}
