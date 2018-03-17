package com.seccanj.sudokusolver.model;

public class HypotheticLineImpl extends LineImpl implements HypotheticLine {

	public HypotheticLineImpl() {
		for (int i=0; i<9; i++) {
			line[i] = 0;
		}
	}

	public void setHypotesis(int n) {
		line[n-1] = n;
	}

	public boolean resetHypotesis(int n) {
		boolean result = (line[n-1] == n);

		line[n-1] = 0;
		
		return result;
	}
}
