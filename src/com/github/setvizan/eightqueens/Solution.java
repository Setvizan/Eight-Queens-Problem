package com.github.setvizan.eightqueens;

public class Solution {

	private final boolean[][] queens;
	
	public Solution(boolean[][] queens) {
		this.queens = queens;
	}

	public boolean[][] getQueens() {
		return queens;
	}
	
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
