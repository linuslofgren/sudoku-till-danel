package sudoku;

public interface SudokuSolver {
	/**
	 * Solves the sudoku
	 * 
	 * @return True if the sudoku could be solved
	 * 
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @throws IllegalArgumentException if row or col is outside the range
	 *                                  [0..9]
	 */
	void remove(int row, int col);

	/**
	 * Gets digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @throws IllegalArgumentException if row or col is outside the range
	 *                                  [0..9]
	 * @return digit in the box row, col.
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * @return True if all digits follow the sudoku rules
	 */
	boolean isValid();

	/**
	 * Clears the matrix. The digit 0 represents an empty box.
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Returns the sudoku matrix. The digit 0 represents an empty box.
	 * @return copy of the sudoku matrix
	 */
	int[][] getMatrix();
}