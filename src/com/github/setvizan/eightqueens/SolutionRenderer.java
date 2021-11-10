package com.github.setvizan.eightqueens;

import java.awt.Component;
import java.awt.Graphics;

public class SolutionRenderer extends Component {

	private Solution solution;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8263475183196560552L;
	
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	public Solution getSolution() {
		return solution;
	}
	
	@Override
	public void paint(Graphics g) {
		if (this.solution != null) {
			int fieldHeight = this.getHeight() / this.solution.getQueens().length;
			int posY = 0;
			for (boolean[] barr : this.solution.getQueens()) {
				int fieldWidth = this.getWidth() / barr.length;
				int posX = 0;
				for (boolean b : barr) {
					g.drawRect(posX, posY, fieldWidth, fieldHeight);
					if (b) {
						g.fillRect(posX, posY, fieldWidth, fieldHeight);
					}
					posX += fieldWidth;
				}
				posY += fieldHeight;
			}
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	
}
