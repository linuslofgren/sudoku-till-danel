package sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sudoku implements SudokuSolver {

	private int[][] matrix;
	private int[][] solveMatrix;
	
	public Sudoku() {
		this.clear();
	}
	
	@Override
	public boolean solve() {
		solveMatrix = matrix;
		boolean solved = solve(0, 0);
		if(solved) {
			matrix = solveMatrix;
		}
		return solved;
		
		
	}
	
	private boolean solve(int r, int c) {
		int newC = (c+1)%solveMatrix.length;
		int newR = ((c+1)==solveMatrix.length) ? r+1: r;
		
		if(r >= solveMatrix.length) {
			System.out.println("SOLVED!");
			return true;
		}
		if(solveMatrix[r][c] != 0) {
		
			if(valid(solveMatrix)) {
				return solve(newR, newC);
			} else {
				return false;
			}
		}
		for(int i = 1; i < 10; i++) {
			solveMatrix[r][c] = i;
			if(!valid(solveMatrix)) {
				solveMatrix[r][c] = 0;
				continue;
			}
			if (solve(newR, newC)) {
				return true;
			} else {
				solveMatrix[r][c] = 0;
			}
		}
		return false;
	}

	@Override
	public void add(int row, int col, int digit) {
		if(!rangeCheck(row) || !rangeCheck(col) || !rangeCheck(digit)) {
			throw new IllegalArgumentException();
		}
		matrix[row][col] = digit;
		
	}

	@Override
	public void remove(int row, int col) {
		if(!rangeCheck(row) || !rangeCheck(col)) {
			throw new IllegalArgumentException();
		}
		matrix[row][col] = 0;
	}

	@Override
	public int get(int row, int col) {
		if(!rangeCheck(row) || !rangeCheck(col)) {
			throw new IllegalArgumentException();
		}
		return matrix[row][col];
	}
	
	private boolean rangeCheck(int i) {
		return i < 10 && i > -1;
	}

	private boolean valid(int[][] m) {
		if(m.length != 9) {
			return false;
		}
		for(int i = 0; i < m.length; i++) {
			if(!allNumbers(m[i])) {
				return false;
			}
			int[] column = new int[m[i].length];
			for(int j = 0; j < m[i].length; j++) {
				column[j] = m[j][i];
			}
			if(!allNumbers(column)) {
				return false;
			}
			
			int[] square = new int[m[i].length];
			int rowOffset = ((i-(i%3)));
			int columnOffset = 3*(i%3);
			for(int j = 0; j < 3; j++) {
				square[3*j] = m[rowOffset+j][columnOffset];
				square[3*j+1] = m[rowOffset+j][columnOffset+1];
				square[3*j+2] = m[rowOffset+j][columnOffset+2];
			}
			
			if(!allNumbers(square)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean allNumbers(int[] l) {
		Set<Integer> numbers = new HashSet<Integer>();
		for(int i = 0; i < l.length; i++) {
			if(l[i] == 0) {
				numbers.add(-i);
				continue;
			}
			if(numbers.contains(l[i]) || l[i] < 1 || l[i] > 9) {
				return false;
			}
			numbers.add(l[i]);
		}
		return numbers.size() == 9;
	}
	
	@Override
	public boolean isValid() {
		return valid(matrix);
	}

	@Override
	public void clear() {
		matrix = new int[9][9];
	}

	@Override
	public void setMatrix(int[][] m) {
//		if(!valid(m)) {
//			throw new IllegalArgumentException();
//		}
		matrix = m.clone();
		
	}

	@Override
	public int[][] getMatrix() {
		return matrix.clone();
	}

}
