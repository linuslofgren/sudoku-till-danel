package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {

	SudokuSolver sudoku;
	
	int[][] EMPTY = {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
		};
	
	int[][] SOLVABLE = {
			{1,0,7,4,5,2,0,3,0},
			{0,0,0,7,8,0,0,5,0},
			{0,6,8,3,0,9,4,7,2},
			{0,7,0,0,9,0,0,6,0},
			{8,5,1,6,0,0,3,9,0},
			{0,2,0,5,4,0,0,0,7},
			{6,8,0,0,0,4,0,0,3},
			{0,0,4,0,0,1,0,0,9},
			{2,0,0,0,0,0,7,0,0}
		};
	
	int[][] SOLVED = {
			{5,3,4,6,7,8,9,1,2},
			{6,7,2,1,9,5,3,4,8},
			{1,9,8,3,4,2,5,6,7},
			{8,5,9,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{9,6,1,5,3,7,2,8,4},
			{2,8,7,4,1,9,6,3,5},
			{3,4,5,2,8,6,1,7,9}
	};
	
	int[][] UNSOLVABLE = {
			{5,3,4,6,7,8,9,1,2},
			{6,7,2,1,9,5,3,4,8},
			{1,9,8,3,4,2,5,6,7},
			{8,5,9,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{9,6,1,5,3,7,2,0,9},
			{2,8,7,4,1,9,6,3,5},
			{3,4,5,2,8,6,1,7,9}
	};
	
	@BeforeEach
	void setUp() throws Exception {
		sudoku = new Sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsValid() {
		sudoku.setMatrix(SOLVABLE);
		assertTrue(sudoku.isValid());
	}
	
	@Test
	void testCanSolve() {
		sudoku.setMatrix(SOLVABLE);
		assertTrue(sudoku.solve());
	}
	
	@Test
	void testCanDetectNonSolve() {
		sudoku.setMatrix(UNSOLVABLE);
		assertFalse(sudoku.solve());
	}
	
	@Test
	void testSetMatrix() {
		sudoku.setMatrix(UNSOLVABLE);
		assertArrayEquals(sudoku.getMatrix(), UNSOLVABLE);
	}
	
	@Test
	void testAdd() {
		sudoku.setMatrix(UNSOLVABLE);
		sudoku.add(4,4,9);
		assertEquals(sudoku.get(4,4), 9);
	}
	
	@Test
	void testRemove() {
		sudoku.setMatrix(UNSOLVABLE);
		sudoku.remove(4,4);
		assertEquals(sudoku.get(4,4), 0);
	}
	
	@Test
	void testSolveEmpty() {
		sudoku.setMatrix(EMPTY);
		assertTrue(sudoku.solve());
	}
	
	@Test
	void testCelar() {
		sudoku.setMatrix(SOLVABLE);
		sudoku.clear();
		assertArrayEquals(sudoku.getMatrix(), EMPTY);
	}

}
