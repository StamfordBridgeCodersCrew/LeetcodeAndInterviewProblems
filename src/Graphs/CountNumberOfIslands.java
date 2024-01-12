package Graphs;

import java.util.ArrayDeque;
import java.util.Queue;

// https://www.techiedelight.com/count-the-number-of-islands/
class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class CountNumberOfIslands {
	private static final int[] rowMovt = {-1, -1, -1, 0, 1, 0, 1, 1};
	private static final int[] colMovt = {-1, 1, 0, -1, -1, 1, 0, 1};

	/*
	Is it safe to go to (x,y) i.e mat[x,y] from the current posn ?
		(x,y) must lie within the boundaries of mat
		mat[x,y] must be a land(1), It must not be water(0)
		(x,y) must not be visited earlier

	* */
	public static boolean isSafe(int[][] mat, int x, int y, boolean[][] visited) {
		return ( (x>=0 && x<mat.length) && (y>=0 && y<mat[0].length) &&
			mat[x][y]==1 && !visited[x][y]
		);
	}

	/* Utility function that performs BFS
		mat --> Matrix upon which the BFS is to be performed
		i,j --> Source node = Source cell
		visited --> To keep track of the visited nodes
	*/
	public static void BFS(int[][] mat, int i, int j, boolean[][] visited) {
		// 1.1 Create an empty queue
		// 1.2 Push the source node into q (Mark it as visited)
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i,j));
		visited[i][j] = true;

		// 2. While the q is not empty
		while(!q.isEmpty()) {
			// 2.1 Dequeue the front node from the q
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			// 2.2 Probe for the valid adjacent neighbors of Node (x,y)
			// If they aint visited earlier --> Visit them (Enqueue them, Mark them as visited)
			for(int k=0; k<rowMovt.length; k++) {
				if(isSafe(mat, x+rowMovt[k], y+colMovt[k], visited)) {
					q.add(new Pair(x+rowMovt[k], y+colMovt[k]));
					visited[x+rowMovt[k]][y+colMovt[k]] = true;
				}
			}

		}
	}

	/* Utility function that performs BFS
		mat --> Matrix upon which the BFS is to be performed
		i,j --> Source node = Source cell
		visited --> To keep track of the visited nodes
	*/
	public static void DFS(int[][] mat, int i, int j, boolean[][] visited) {

		// 1. Mark the current node as visited
		visited[i][j] = true;

		// 2. Visit all the valid neighbors of (x,y) in a Depth First manner
		for(int k=0; k<rowMovt.length; k++) {
			if(isSafe(mat, i+rowMovt[k], j+colMovt[k], visited)) {
				DFS(mat, i+rowMovt[k], j+colMovt[k], visited);
			}
		}
	}

	public static int countIslands(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		
		// visited = For keeping track of all the visited nodes(cells)
		boolean[][] visited = new boolean[m][n];
		
		int num_islands = 0;
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[0].length; j++) {
				/* 
				Start BFS from each unprocessed node
					* Increase island_count by 1
					* No of BFS calls = No of islands = No of connected components of the Graph
				*/
				if(mat[i][j]==1 && !visited[i][j]) {
					//BFS(mat, i, j, visited);
					DFS(mat, i, j, visited);
					num_islands++;
				}
			}
		}
		
		return num_islands;
	}

	public static void main(String[] args) {
		int[][] mat=
			{
				{ 1, 0, 1, 0, 0, 0, 1, 1, 1, 1 },
				{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
				{ 1, 1, 1, 1, 0, 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 },
				{ 0, 1, 0, 1, 0, 0, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 1, 1, 1, 0 },
				{ 1, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
				{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 }
			};

		System.out.print("The total number of islands is " + countIslands(mat));
	}
}
