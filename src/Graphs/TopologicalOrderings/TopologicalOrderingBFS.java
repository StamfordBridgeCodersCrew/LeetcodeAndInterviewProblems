package Graphs.TopologicalOrderings;

import java.util.*;

// Edge = Represents the edge of a Graph G
class Edge {
	int src, dest;

	public Edge(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}
}

/*
* Graph = Represents a Graph
* 	n = No of vertices of the Graph
* 	edges = Edge information of the Graph
*
* Others
* 	adjList, indegree = Constructed using information (n, edges)
* */
class Graph {

	// n, edges = Used by the constructor
	int n;
	List<Edge> edges = null;

	// adjList, indegree = Constructed using the information (n, edges)
	List<List<Integer>> adjList = null;
	List<Integer> indegree = null;

	Graph(int n, List<Edge> edges) {
		this.n = n;
		this.edges = edges;

		// Initialize adjList
		adjList = new ArrayList<>();
		for(int i=0; i<n; i++) {
			adjList.add(new ArrayList<>());
		}

		// Initialize indegree
		indegree = new ArrayList<>(Collections.nCopies(n, 0));

		for(Edge edge : edges) {
			int src = edge.src;
			int dest = edge.dest;

			// adjList[src].push_back(dest)
			adjList.get(src).add(dest);

			// indegree[dest] = indegree[dest] + 1 ==> indegree[dest]++
			indegree.set(dest, indegree.get(dest)+1);
		}
	}
}
public class TopologicalOrderingBFS {

	// https://www.techiedelight.com/kahn-topological-sort-algorithm/
	public static List<Integer> topologicalOrderingBFS(Graph g) {
		// List l <-- Stores all topologically sorted vertices
		List<Integer> L = new ArrayList<>();

		// Queue q <-- Set of all nodes whose indegree = 0
		ArrayDeque<Integer> q = new ArrayDeque<>();

		// 1 Insert all vertices(whose indegree=0) into q
		List<Integer> indegree = g.indegree;
		int n = g.n;
		for(int i=0; i<n; i++) {
			if(indegree.get(i) == 0) {
				q.add(i);
			}
		}

		// 2. While the q is not empty, Do the following
		while (!q.isEmpty()) {
			// 2.1 Dequeue the front node from q
			int u = q.peek();
			q.poll();

			// 2.2 Insert u at the rear of L
			L.add(u);

			/*
			3. For all adjacent neighbors(v) of u
				3.1 Remove edge u-->v (Decrease indegree[v] by 1)
				3.2 If now, v has no incoming edges(indegree[v] == 0), Insert it into q

			*/
			for(int v : g.adjList.get(u)) {
				// 3.1 Remove edge u-->v (Decrease indegree[v] by 1)
				// indegree[v] = indegree[v] - 1;
				indegree.set(v, indegree.get(v)-1);

				// 3.2 If now, v has no incoming edges(indegree[v] == 0), Insert it into L
				if(indegree.get(v) == 0) {
					q.add(v);
				}
			}
		}

		// At this point, If graph has atleast 1 edge --> It has a cycle
		for(int i=0; i<n; i++) {
			if(indegree.get(i) != 0) {
				return null;
			}
		}

		// Else return L
		// L contains the topological ordering of the given Graph
		return L;
	}

	public static void main(String[] args) {
		// List of graph edges as per the above diagram
		List<Edge> edges = Arrays.asList(
			new Edge(0, 6), new Edge(1, 2), new Edge(1, 4),
			new Edge(1, 6), new Edge(3, 0), new Edge(3, 4),
			new Edge(5, 1), new Edge(7, 0), new Edge(7, 1)
		);

		// No of nodes in the Graph
		int n = 8;

		// Build a Graph from the above information (n, edges)
		Graph g = new Graph(n, edges);

		// answer = For storing the topological ordering of Graphs
		List<Integer> answer = topologicalOrderingBFS(g);

		if(answer != null) {
			System.out.println("Topological ordering of the Graph = "+answer);
		}
		else {
			System.out.println("Graph aint a DAG (It has atleast 1 cycle)");
		}
	}
}
