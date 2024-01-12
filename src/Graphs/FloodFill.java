package Graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class FloodFill {

	// Below arrays entail all possible 8 movements from a single cell
	private static final int[] rowMovt = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] colMovt = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static boolean isSafe(int[][] mat, int x, int y, int targetColor) {
		return ( (x>=0 && x<mat.length) && (y>=0 && y<mat[0].length) &&
			(mat[x][y] == targetColor)
		);
	}

	public static void BFS(int[][] mat, int i, int j, int replacementColor) {

		int targetColor = mat[i][j];
		if(targetColor == replacementColor) {
			return;
		}

		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i, j));

		while (!q.isEmpty()) {
			i = q.peek().x;
			j = q.peek().y;
			q.poll();

			mat[i][j] = replacementColor;

			for (int k=0; k<rowMovt.length; k++) {
				if(isSafe(mat, i+rowMovt[k], j+colMovt[k], targetColor)) {
					q.add(new Pair(i+rowMovt[k], j+colMovt[k]));
				}
			}

		}
	}
}
