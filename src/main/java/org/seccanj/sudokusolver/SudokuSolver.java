package org.seccanj.sudokusolver;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class SudokuSolver {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            kSession.insert(new Board());
            
            /*
    		*******************************
    		* Easy
    		*******************************

    		int[][] numbers = new int[][] {
    			{5, 3, 0, 0, 7, 0, 0, 0, 0},
    			{6, 0, 0, 1, 9, 5, 0, 0, 0},
    			{0, 9, 8, 0, 0, 0, 0, 6, 0},
    			{8, 0, 0, 0, 6, 0, 0, 0, 3},
    			{4, 0, 0, 8, 0, 3, 0, 0, 1},
    			{7, 0, 0, 0, 2, 0, 0, 0, 6},
    			{0, 6, 0, 0, 0, 0, 2, 8, 0},
    			{0, 0, 0, 4, 1, 9, 0, 0, 5},
    			{0, 0, 0, 0, 8, 0, 0, 7, 9}				
    		};
    		
    		int[][] numbers = new int[][] {
    			{0, 0, 2, 1, 0, 0, 0, 0, 6},
    			{7, 0, 5, 0, 2, 0, 0, 1, 0},
    			{0, 3, 1, 0, 0, 6, 2, 0, 0},
    			{4, 0, 0, 5, 0, 0, 1, 9, 0},
    			{5, 0, 9, 0, 0, 3, 0, 0, 7},
    			{2, 8, 0, 7, 1, 9, 0, 0, 0},
    			{0, 2, 0, 0, 5, 1, 0, 0, 9},
    			{6, 5, 0, 0, 0, 0, 7, 3, 1},
    			{0, 9, 8, 3, 0, 0, 0, 4, 0}				
    		};

    		*******************************
    		* Medium
    		*******************************

    		int[][] numbers = new int[][] {
    			{0, 9, 5, 0, 0, 8, 0, 0, 6},
    			{0, 0, 0, 0, 7, 3, 9, 0, 0},
    			{0, 0, 1, 9, 0, 0, 0, 3, 8},
    			{0, 0, 0, 3, 6, 0, 0, 1, 0},
    			{0, 6, 0, 0, 0, 0, 0, 8, 2},
    			{5, 0, 7, 0, 0, 2, 0, 0, 0},
    			{4, 0, 0, 0, 5, 0, 0, 0, 7},
    			{0, 0, 0, 2, 9, 0, 0, 5, 0},
    			{8, 5, 0, 0, 0, 0, 1, 9, 0}				
    		};

    		*******************************
    		* Hard
    		*******************************

    		int[][] numbers = new int[][] {
    			{0, 0, 3, 7, 4, 0, 0, 0, 1},
    			{0, 0, 8, 0, 0, 5, 2, 0, 4},
    			{7, 2, 0, 0, 1, 0, 5, 0, 0},
    			{0, 0, 0, 0, 0, 1, 0, 9, 0},
    			{0, 0, 5, 9, 0, 0, 0, 0, 0},
    			{0, 0, 0, 0, 0, 7, 0, 1, 8},
    			{0, 0, 0, 1, 0, 0, 9, 0, 0},
    			{6, 0, 0, 0, 0, 0, 0, 8, 7},
    			{9, 8, 0, 0, 0, 6, 0, 0, 0}				
    		};

    		int[][] numbers = new int[][] {
    			{9, 0, 0, 2, 0, 0, 0, 8, 0},
    			{8, 0, 0, 9, 0, 0, 6, 0, 0},
    			{0, 1, 3, 0, 0, 4, 0, 0, 0},
    			{0, 0, 0, 0, 0, 0, 7, 0, 9},
    			{0, 8, 0, 0, 0, 0, 0, 5, 0},
    			{2, 0, 5, 0, 0, 0, 0, 0, 0},
    			{0, 0, 0, 6, 0, 0, 1, 2, 0},
    			{0, 0, 1, 0, 0, 7, 0, 0, 3},
    			{0, 4, 0, 0, 0, 3, 0, 0, 5}				
    		};

    		int[][] numbers = new int[][] {
    			{3, 1, 8, 0, 0, 5, 4, 0, 6},
    			{0, 0, 0, 6, 0, 3, 8, 1, 0},
    			{0, 0, 6, 0, 8, 0, 5, 0, 3},
    			{8, 6, 4, 9, 5, 2, 1, 3, 7},
    			{1, 2, 3, 4, 7, 6, 9, 5, 8},
    			{7, 9, 5, 3, 1, 8, 2, 6, 4},
    			{0, 3, 0, 5, 0, 0, 7, 8, 0},
    			{0, 0, 0, 0, 0, 7, 3, 0, 5},
    			{0, 0, 0, 0, 3, 9, 6, 4, 1}				
    		};

    		// Hidden pairs (1, 3) on row 3
    		int[][] numbers = new int[][] {
    			{8, 0, 1, 0, 0, 6, 0, 9, 4},
    			{3, 0, 0, 0, 0, 9, 0, 8, 0},
    			{9, 7, 0, 0, 8, 0, 5, 0, 0},
    			{5, 4, 7, 0, 6, 2, 0, 3, 0},
    			{6, 3, 2, 0, 0, 0, 0, 5, 0},
    			{1, 9, 8, 3, 7, 5, 2, 4, 6},
    			{0, 8, 3, 6, 2, 0, 9, 1, 5},
    			{0, 6, 5, 1, 9, 8, 0, 0, 0},
    			{2, 1, 9, 5, 0, 0, 0, 0, 8}				
    		};
    		*/
    		
    		/**
    		 * Medium
    		 */
    		/*
    		int[][] numbers = new int[][] {
    			{0, 9, 5, 0, 0, 8, 0, 0, 6},
    			{0, 0, 0, 0, 7, 3, 9, 0, 0},
    			{0, 0, 1, 9, 0, 0, 0, 3, 8},
    			{0, 0, 0, 3, 6, 0, 0, 1, 0},
    			{0, 6, 0, 0, 0, 0, 0, 8, 2},
    			{5, 0, 7, 0, 0, 2, 0, 0, 0},
    			{4, 0, 0, 0, 5, 0, 0, 0, 7},
    			{0, 0, 0, 2, 9, 0, 0, 5, 0},
    			{8, 5, 0, 0, 0, 0, 1, 9, 0}				
    		};
    		*/
            
    		int[][] numbers = new int[][] {
    			{0, 9, 5, 0, 0, 8, 0, 0, 6},
    			{0, 0, 0, 0, 7, 3, 9, 0, 0},
    			{0, 0, 1, 9, 0, 0, 0, 3, 8},
    			{0, 0, 0, 3, 6, 0, 0, 1, 0},
    			{0, 6, 0, 0, 0, 0, 0, 8, 2},
    			{5, 0, 7, 0, 0, 2, 0, 0, 0},
    			{4, 0, 0, 0, 5, 0, 0, 0, 7},
    			{0, 0, 0, 2, 9, 0, 0, 5, 0},
    			{8, 5, 0, 0, 0, 0, 1, 9, 0}				
    		};
            
            for (int row = 0; row < 9; row++) {
            	for (int column = 0; column < 9; column++) {
           			kSession.insert(new Position(row, column, numbers[row][column]));
            	}
            }
            
            kSession.startProcess("sudoku");
            kSession.fireAllRules();
      
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Number {
    	public int number;
    	
    	public Number(int number) {
    		this.number = number;
    	}
    }
    
    public static class Board {
    	private List<Number> numbers = new ArrayList<>();
    	
    	public Board() {
    		for (int i=1; i<=9; i++) {
    			numbers.add(new Number(i));
    		}
    	}

		public List<Number> getNumbers() {
			return numbers;
		}

    }
    
    public static class Hypothesis {
		private int row;
    	private int column;
    	private int square;
    	private int number;
    	
		public Hypothesis(int row, int column, int number) {
			this.row = row;
			this.column = column;
			this.number = number;
			
			this.square = ((int)(row / 3)) * 3 + ((int)(column / 3));
		}
    	
		/**
		 * @return the row
		 */
		public int getRow() {
			return row;
		}
		/**
		 * @param row the row to set
		 */
		public void setRow(int row) {
			this.row = row;
		}
		/**
		 * @return the column
		 */
		public int getColumn() {
			return column;
		}
		/**
		 * @param column the column to set
		 */
		public void setColumn(int column) {
			this.column = column;
		}
		/**
		 * @return the square
		 */
		public int getSquare() {
			return square;
		}
		/**
		 * @param square the square to set
		 */
		public void setSquare(int square) {
			this.square = square;
		}
		/**
		 * @return the number
		 */
		public int getNumber() {
			return number;
		}
		/**
		 * @param number the number to set
		 */
		public void setNumber(int number) {
			this.number = number;
		}
    	
    	
    }

    public static class Position {
		private int row;
    	private int column;
    	private int square;
    	private int number;
    	
		public Position(int row, int column, int number) {
			this.row = row;
			this.column = column;
			this.number = number;
			
			this.square = ((int)(row / 3)) * 3 + ((int)(column / 3));
		}
    	
		public int getOrder() {
			return row * 9 + column;
		}

		/**
		 * @return the row
		 */
		public int getRow() {
			return row;
		}
		/**
		 * @param row the row to set
		 */
		public void setRow(int row) {
			this.row = row;
		}
		/**
		 * @return the column
		 */
		public int getColumn() {
			return column;
		}
		/**
		 * @param column the column to set
		 */
		public void setColumn(int column) {
			this.column = column;
		}
		/**
		 * @return the square
		 */
		public int getSquare() {
			return square;
		}
		/**
		 * @param square the square to set
		 */
		public void setSquare(int square) {
			this.square = square;
		}
		/**
		 * @return the number
		 */
		public int getNumber() {
			return number;
		}
		/**
		 * @param number the number to set
		 */
		public void setNumber(int number) {
			this.number = number;
		}
    	
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			if (row == 0 && column == 0) {
				sb.append("=======================================================\n");
				sb.append("‖     |     |     ‖     |     |     ‖     |     |     ‖\n");
			}
			
			if (column == 0) {
				sb.append("‖");
			}
			
			sb.append("  "+(number != 0 ? number : " ")+"  ");

			if (column % 3 == 2) {
				sb.append("‖");
			} else {
				sb.append("|");
			}

			if (column == 8) {
				sb.append("\n");
				sb.append("‖     |     |     ‖     |     |     ‖     |     |     ‖\n");

				if (row % 3 == 2) {
					sb.append("=======================================================\n");
					if (row != 8) {
						sb.append("‖     |     |     ‖     |     |     ‖     |     |     ‖\n");
					}
				}
			}
			
			return sb.toString();
		}
    	
    }

}
