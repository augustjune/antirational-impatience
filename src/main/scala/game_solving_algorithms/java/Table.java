package game_solving_algorithms.java;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private boolean[][] grid;
	private int size;

	public Table(int size) {
		this.size = size;
		grid = new boolean[size][size];
	}

	public int color(int row, int column) {
		int reward = calculateReward(row, column);
		if (isValid(row) && isValid(column)) {
			grid[row][column] = true;
			return reward;
		}
		return 0;
	}

	public int calculateReward(int row, int col) {
		if (!isValid(row) || !isValid(col)) return 0;
		if (grid[row][col]) return 0;

		int points = 0;
		if (gridsToFillRow(row) == 1)
			points += size;
		if (gridsToFillColumn(col) == 1)
			points += size;

		if ((row + col) % ((size - 1) * 2) != 0)
			if (gridsToFillUpDiagonal(row, col) == 1)
				points += size - Math.abs(size - 1 - row - col);

		if (Math.abs(row - col) != size - 1)
			if (gridsToFillDownDiagonal(row, col) == 1)
				points += size - Math.abs(row - col);


		return points;
	}

	private int gridsToFillRow(int row) {
		int count = 0;
		for (int i = 0; i < size; i++)
			if (!grid[row][i])
				count++;

		return count;
	}

	private int gridsToFillUpDiagonal(int row, int col) {
		int count = 0;
		int sum = row + col;
		if (sum < size) {    //is in up-left triangle
			for (int c = 0; c <= sum; c++)
				if (!grid[sum - c][c])
					count++;
		} else {        // is in down-right triangle
			for (int r = size - 1; r > sum - size; r--)
				if (!grid[r][sum - r])
					count++;
		}
		return count;
	}

	private int gridsToFillDownDiagonal(int row, int col) {
		int count = 0;
		int diff = Math.abs(row - col);
		if (col > row) {    // is in up-right triangle
			for (int r = 0; r < size - diff; r++)
				if (!grid[r][r + diff])
					count++;
		} else {        // is in down-left triangle
			for (int c = 0; c < size - diff; c++)
				if (!grid[c + diff][c])
					count++;
		}

		return count;
	}

	private int gridsToFillColumn(int column) {
		int count = 0;
		for (int i = 0; i < size; i++)
			if (!grid[i][column])
				count++;

		return count;
	}

	private boolean isValid(int index) {
		return 0 <= index && index < size;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				System.out.printf("%s  ", grid[i][j] ? "X" : "_");
			System.out.println();
		}
		System.out.println("==========================");
	}

	public List<Pair<Integer, Integer>> freeGrids() {
		List<Pair<Integer, Integer>> result = new ArrayList<>();
		for (int i=0; i<size; i++)
			for (int j=0; j<size; j++)
				if (!grid[i][j])
					result.add(new Pair<>(i,j));
		return result;
	}

	public boolean isEnded() {
		for (int i=0; i<size; i++)
			for (int j=0; j<size; j++)
				if (!grid[i][j])
					return false;
		return true;
	}
}
