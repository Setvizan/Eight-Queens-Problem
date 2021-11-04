package com.github.setvizan.eightqueens;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Main {
    public static void main(String[] args) {
    	JFrame nDialog = new JFrame();
    	nDialog.setLayout(null);
    	nDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	nDialog.setMinimumSize(new Dimension(250, 115));
    	
    	nDialog.setTitle("Input size");
    	JTextField input = new JTextField();
    	JButton button = new JButton("Run");
    	
    	input.setBounds(5, 5, 225, 30);
    	
    	button.setBounds(5, 40, 225, 30);
    	button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				if (!text.isEmpty()) {
					try {
						int n = Integer.parseInt(text);
						showSolutions(NQueens.solve(n));
						
					} catch (NumberFormatException e2) {}
				}
			}
		});
    	nDialog.add(input);
    	nDialog.add(button);
    	nDialog.pack();
    	nDialog.setLocationRelativeTo(null);
    	nDialog.setVisible(true);
    }
    
    public static void showSolutions(Solution[] solutions) {
    	JFrame explorer = new JFrame();
    	explorer.setTitle("Solutions");
    	explorer.setLayout(new GridLayout(1, 2));
    	explorer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	explorer.setMinimumSize(new Dimension(500, 500));
    
    	JTable table = new JTable(new DefaultTableModel(new Object[] {"Solution"}, 0) {
    		/**
			 * 
			 */
			private static final long serialVersionUID = 290658070096283549L;

			@Override
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	});
    	table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    	
    	for (int i = 0; i<solutions.length; i++) {
    		((DefaultTableModel) table.getModel()).addRow(new String[] {String.valueOf(i+1)});
    	}
    	JScrollPane scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
    	
    	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getSource() instanceof DefaultListSelectionModel) {
					DefaultListSelectionModel source = (DefaultListSelectionModel) e.getSource();
					int[] selectedIndices = source.getSelectedIndices();
					if (selectedIndices.length > 0) {
						Solution chosenSolution = solutions[selectedIndices[0]];
						System.out.println("\n".repeat(50) + "Solution No. " + (selectedIndices[0]+1) + "\n" + chosenSolution.toString());
					}
				}
			}
		});
    	
    	explorer.add(scrollPane);
    	
    	explorer.pack();
    	explorer.setLocationRelativeTo(null);
    	explorer.setVisible(true);
    }
}
