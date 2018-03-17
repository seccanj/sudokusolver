package com.seccanj.sudokusolver.model;

import java.util.Arrays;
import java.util.Iterator;

public class LineImpl implements Line {

	Integer[] line;
	
	public LineImpl(int[] line) {
		this.line = Arrays.stream(line).boxed().toArray(Integer[]::new);
	}

	public LineImpl() {
		line = new Integer[9];
	}

	@Override
	public boolean contains(int n) {
		int i = 0;
		while(i<9 && line[i] != n) {
			i++;
		}
		
		return i < 9;
	}
	
	/*
	public int and() {
		return Arrays.stream(hypothesis).reduce(0, (a, v) -> (a == -1 ? a : v));
	}
	
	public int or() {
		return Arrays.stream(hypothesis).reduce(0, (a, v) -> (a != -1 ? a : v));
	}
	*/
	
	public int size() {
		return Arrays.stream(line)
				.map(n -> ((n > 0) ? 1 : 0))
				.reduce(0, (s, n) -> s + n);
	}
	
	public int getFirstValue() {
		return Arrays.stream(line)
				.filter(n -> (n > 0))
				.findFirst()
				.get();
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
