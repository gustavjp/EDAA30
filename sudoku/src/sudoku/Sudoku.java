package sudoku;

public class Sudoku {

	private int[][] matrix;

	/**
	 * Creates an empty Sudoku object.
	 */
	public Sudoku() {
		this(new int[9][9]);
	}

	/**
	 * Creates a Sudoku object with specified initial matrix.
	 * 
	 * @param m the matrix
	 */
	public Sudoku(int[][] m) {
		matrix = m;
	}
	
	public void setMatrix(int[][] m) {
		matrix = m;
	}

	/**
	 * Returns the matrix.
	 * 
	 * @return the matrix
	 */
	public int[][] getMatrix() {
		return matrix;
	}

	/**
	 * Returns the sudoku as a string.
	 * 
	 * @return the string
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				s += matrix[i][j];
				s += " ";
			}
			s += '\n';
		}
		return s;
	}

	/**
	 * Solves the sudoku puzzle and returns true if sudoku was solved.
	 * 
	 * @return true if the sudoku was solved, otherwise false
	 */
	public boolean solve() {
		return solve(0, 0);
	}

	/**
	 * Recursively solves the sudoku.
	 * 
	 * @param row the starting row
	 * @param col the starting column
	 * @return true is the sudoku was solved, otherwise false
	 */
	private boolean solve(int row, int col) {
		if (row == 9) {
			return true;
		}
		if (matrix[row][col] != 0) {
			/*
			if (solve(col == 8 ? (row + 1) : row, (col + 1) % 9)) {
				return true;
			}
			*/
			//
			if (col == 8) {
				if (solve(row + 1, (col + 1) % 9)) {
					return true;
				}
			} else {
				if (solve(row, (col + 1) % 9)) {
					return true;
				}
			}
			//
		} else {
			for (int i = 0; i < 9; i++) {
				if (isValid(row, col, i + 1)) {
					matrix[row][col] = (i + 1);
					/*
					if (solve(col == 8 ? (row + 1) : row, (col + 1) % 9)) {
						return true;
					} else {
						matrix[row][col] = 0;
					}
					*/
					//
					if (col == 8) {
						if (solve(row + 1, 0)) {
							return true;
						} else {
							matrix[row][col] = 0;
						}
					} else {
						if (solve(row, col + 1)) {
							return true;
						} else {
							matrix[row][col] = 0;
						}
					}
					//
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the number n is a valid candidate for its row and column and
	 * returns true if it's valid, otherwise false.
	 * 
	 * @param n   the number to check validity for
	 * @param row the row the number is checked for
	 * @param col the column the number is checked for
	 * @return true if the number is a valid candidate, otherwise false
	 */
	public boolean isValid(int row, int col, int n) {
		// check if 0 < n < 10
		if (n < 0 || n > 10) {
			return false;
		}

		// check row and column
		for (int i = 0; i < 9; i++) {
			if ((matrix[row][i] == n && col != i) || (matrix[i][col] == n && row != i)) {
				return false;
			}
		}

		// check subgrid
		int crow = (row / 3) * 3;
		int ccol = (col / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if ((crow + i != row && ccol + k != col) && matrix[crow + i][ccol + k] == n) {
					return false;
				}
			}
		}
		return true;
	}
}
