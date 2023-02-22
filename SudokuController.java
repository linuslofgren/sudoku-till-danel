package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class SudokuController {
	
	SudokuSolver sudoku = new Sudoku();

	public SudokuController() {
	    SwingUtilities.invokeLater(() -> createWindow("Sudoku", 100, 300));
	}
	private void createWindow(String title, int width, int height) {
	    JFrame frame = new JFrame(title);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container pane = frame.getContentPane();
	    
	    JPanel panel = new JPanel();
	    JPanel panelBottom = new JPanel();
	    panel.setLayout(new GridLayout(9,9));    
	    
	    JFormattedTextField[] textFields = new JFormattedTextField[81];
	    
	    for(int i = 0; i < 81; i++) {
	    	NumberFormat f = NumberFormat.getInstance();
	    	f.setMaximumIntegerDigits(1);
	    	f.setMaximumFractionDigits(0);
	    	f.setParseIntegerOnly(true);
	    	textFields[i] = new JFormattedTextField(f);
	    	boolean b = (i%9) < 3 || (i%9) > 5;
	    	if((i > 26 && i < 54) ? !b : b) {
	    		textFields[i].setBackground( Color.orange );
	    	}
	    	panel.add(textFields[i]);
	    }
		
		JButton btn = new JButton("Lös min sudoku tack");
		JButton btnClr = new JButton("Clear");
		
		ActionListener clear = (event) -> {
			sudoku.clear();
			for(int i = 0; i < 81; i++) {
				textFields[i].setText("");
			}
		};
		btnClr.addActionListener(clear);
		
		
		ActionListener find = (event) -> {
			for(int i = 0; i < 81; i++) {
				int v = 0;
				try {
					v = Integer.parseInt(textFields[i].getText());
				} catch( java.lang.NumberFormatException e) {
					
				}
				
				int c = i%9;
				int r = (i-c)/9;
				sudoku.add(r, c, v);
			}
			boolean solved = sudoku.solve();
			if(!solved) {
				JOptionPane.showMessageDialog(frame, "No solution exists for this sudoku");
			}
			int[][] matrix = sudoku.getMatrix();
			for(int i = 0; i < 81; i++) {
				int c = i%9;
				int r = (i-c)/9;
				String str = ""+matrix[r][c];
				if(str.equals("0")) {
					str = "";
				}
				textFields[i].setText(str);
			}
		};
		btn.addActionListener(find);
		panelBottom.add(btn);
		panelBottom.add(btnClr);
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panelBottom, BorderLayout.SOUTH);
		
		frame.setSize(300,300);
		frame.setVisible(true);
}
}
