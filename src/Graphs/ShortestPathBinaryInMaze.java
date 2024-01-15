package Graphs;

import java.util.ArrayDeque;
import java.util.Queue;

class Node1 {
	int x, y;
	int dis;

	public Node1(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node1(int x, int y, int dis) {
		this.x = x;
		this.y = y;
		this.dis = dis;
	}
}
/*
* Shortest path in a maze = Shortest path from src-->dest in a Matrix(Containing only 0s and 1s)
* Can be solved using BFS, DFS
* */
public class ShortestPathBinaryInMaze {
	public static final int[] rowMovt = { -1, 0, 0, 1 };
	public static final int[] colMovt = { 0, -1, 1, 0 };
	/*
	Is it safe to go to (x,y) i.e mat[x,y] from the current posn ?
		(x,y) must lie within the boundaries of mat
		mat[x,y] must be 1, It must not be 0
		(x,y) must not be visited earlier

	*/
	public static boolean isSafe(int[][] mat, int x, int y, boolean[][] visited) {
		return ( (x>=0 && x<mat.length) && (y>=0 && y<mat[0].length) && mat[x][y]==1 &&
			!visited[x][y]
		);
	}

	/*
	* Assume Matrix ~ Graphs in these scenarios.
	* Pair = Node = Represents each Matrix cell (i,j)
	*
	* Finding shortest path in a Matrix
	* BFS technique finds out the shortest path only if the Matrix is a Binary one.
	*
	* https://www.techiedelight.com/lee-algorithm-shortest-path-in-a-maze/
	* */
	public static int BFS(int[][] mat, int src_i, int src_j, boolean[][] visited, int dest_i, int dest_j) {

		Queue<Node1> q = new ArrayDeque<>();
		q.add(new Node1(src_i, src_j, 0));
		visited[src_i][src_j] = true;

		int min_dist = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int dist = q.peek().dis;
			q.poll();

			if(x==dest_i && y==dest_j) {
				min_dist = dist;
				break;
			}

			for(int k=0; k<rowMovt.length; k++) {
				if(isSafe(mat, x+rowMovt[k], y+colMovt[k], visited)) {
					q.add(new Node1(x+rowMovt[k], y+colMovt[k], dist+1));
					visited[x+rowMovt[k]][y+colMovt[k]] = true;
				}
			}
		}

		return (min_dist != Integer.MAX_VALUE) ? min_dist : -1;
	}

	/*
	*
	* https://www.techiedelight.com/find-shortest-path-in-maze/
	* */


	public static void main(String[] args) {
		int[][] mat =
			{
				{ 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
				{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
				{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
				{ 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
			};

		int m = mat.length;
		int n = mat[0].length;

		boolean[][] visited = new boolean[m][n];

		// src_i, src_j = Source coordinates
		// dest_i, dest_j = Destination coordinates
		int src_i=0, src_j=0;
		int dest_i=7, dest_j=5;

		// Base case :- Invalid inputs
		if(mat==null || mat.length==0 || mat[src_i][src_j]==0 || mat[dest_i][dest_j]==0) {
			System.out.println("Invalid input");
		}

		int min_dist = BFS(mat, src_i, src_j, visited, dest_i, dest_j);
		if (min_dist != -1) {
			System.out.printf("Length of the smallest path from src->dest = %d",min_dist);
		} else {
			System.out.println("Destination is unreachable from source");
		}
	}
}
