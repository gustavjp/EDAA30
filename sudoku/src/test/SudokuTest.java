package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sudoku.Sudoku;

public class SudokuTest {
	Sudoku s;
	String st;
	int[][] m;
	int[][] m2 = {
			{0, 0, 8, 0, 0, 9, 0, 6, 2},
			{0, 0, 0, 0, 0, 0, 0, 0, 5},
			{1, 0, 2, 5, 0, 0, 0, 0, 0},
			{0, 0, 0, 2, 1, 0, 0, 9, 0},
			{0, 5, 0, 0, 0, 0, 6, 0, 0},
			{6, 0, 0, 0, 0, 0, 0, 2, 8},
			{4, 1, 0, 6, 0, 8, 0, 0, 0},
			{8, 6, 0, 0, 3, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 4, 0, 0},
	};
	
	@Before
	public void setUp() throws Exception {
		m = new int[9][9];
		m[0][2] = 8;
		m[0][5] = 9;
		m[0][7] = 6;
		m[0][8] = 2;
		
		m[1][8] = 5;
		
		m[2][0] = 1;
		m[2][2] = 2;
		m[2][3] = 5;
		
		m[3][3] = 2;
		m[3][4] = 1;
		m[3][7] = 9;
		
		m[4][1] = 5;
		m[4][6] = 6;
		
		m[5][0] = 6;
		m[5][7] = 2;
		m[5][8] = 8;
		
		m[6][0] = 4;
		m[6][1] = 1;
		m[6][3] = 6;
		m[6][5] = 8;
		
		m[7][0] = 8;
		m[7][1] = 6;
		m[7][4] = 3;
		m[7][6] = 1;
		
		m[8][6] = 4;
	}
	
	@After
	public void tearDown() throws Exception {
		s = null;
		st = null;
		m = null;
	}
	
	@Test
	public final void testToString() {
		Sudoku sud = new Sudoku(m2);
		sud.solve();
		System.out.println(sud.toString());
		
	}
	
	@Test
	public final void getMatrix() {
		s = new Sudoku(m);
		assertEquals("Didn't get expected matrix back", s.getMatrix(), m);
	}
	
	@Test
	public final void testSolvabeSudoku() {
		s = new Sudoku(m);
		assertTrue("Solvable sudoku is solvable", s.solve());
	}
	
	@Test
	public final void testUnsolvableSudoku() {
		int[][] matrix = new int[9][9];
		matrix [0][0] = 1;
		matrix [0][1] = 2;
		matrix [0][2] = 3;
		matrix [1][0] = 4;
		matrix [1][1] = 5;
		matrix [1][2] = 6;
		matrix [2][3] = 7;
		s = new Sudoku(matrix);
		assertFalse("Can't solve unsolvable sudoku",s.solve());
	}
	
	@Test
	public final void testSolveEmptySudoku() {
		Sudoku empty = new Sudoku();
		assertTrue("Empty sudoku is always solvable", empty.solve());
	}

}
