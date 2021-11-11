package com.github.setvizan.eightqueens;

/**
 * This is just a wrapper class to store solutions in
 */
public class Solution {

	private final boolean[][] queens;
	
	/**
	 * 
	 * @param queens [N][N] Array true if there's a queen, false otherwise
	 */
	public Solution(boolean[][] queens) {
		this.queens = queens;
	}

	public boolean[][] getQueens() {
		return queens;
	}
	
	/**
	 * @return A string which visualizes a NxN chess board and marks all queen-positions
	 * String looks like this:
	 * [ ][Q][ ][ ]
	 * [ ][ ][ ][Q]
	 * [Q][ ][ ][ ]
	 * [ ][ ][Q][ ]
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < queens.length; i++) {
			for (int k = 0; k < queens[i].length; k++) {
				sb.append("[").append(queens[i][k] ? "Q" : " ").append("]");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
