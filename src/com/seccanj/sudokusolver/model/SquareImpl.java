package com.seccanj.sudokusolver.model;

public class SquareImpl extends LineImpl implements Square {

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
	
	public int getValueHypotesis(int i, int j) {
		return hypothesis[i*3 + j];
	}

	public void setHypotesis(int i, int j, int value) {
		hypothesis[i*3 + j] = value;
	}

}
