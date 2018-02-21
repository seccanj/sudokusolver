package com.seccanj.sudokusolver.model;

import java.util.Arrays;
import java.util.Iterator;

public class LineImpl implements Line {

	Integer[] line;
	Integer[] hypothesis = new Integer[9];
	
	public LineImpl(int[] line) {
		this.line = Arrays.stream(line).boxed().toArray(Integer[]::new);
		init();
	}

	public LineImpl() {
		line = new Integer[9];
		init();
	}

	protected void init() {
		for (int i=0; i<9; i++) {
			hypothesis[i] = 0;
		}
	}
	
	@Override
	public boolean contains(int n) {
		int i = 0;
		while(i<9 && line[i] != n) {
			i++;
		}
		
		return i < 9;
	}
	
	public int and() {
		return Arrays.stream(hypothesis).reduce(0, (a, v) -> (a == -1 ? a : v));
	}
	
	public int or() {
		return Arrays.stream(hypothesis).reduce(0, (a, v) -> (a != -1 ? a : v));
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return Arrays.asList(line).iterator();
	}
	
	@Override
	public String toString() {
		return Arrays.stream(line).map(i -> i == 0 ? " " : String.valueOf(i)).reduce("| ", (res, s) -> res + s + " | ");
	}
}
