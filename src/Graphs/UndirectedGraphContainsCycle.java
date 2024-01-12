package Graphs;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
Node =
	u = Represents the node
	parent = Represents the parent of u
*/
class Node {
	int u;
	int parent;

	public Node(int u, int parent) {
		this.u = u;
		this.parent = parent;
	}
}

public class UndirectedGraphContainsCycle {
	public static boolean BFS(List<List<Integer>> g, int src, Set<Integer> visited) {
		// 1.1 Create an empty Q
		// 1.2 Push the source node into the Q, Mark it as visited
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(src, -1));
		visited.add(src);

		// 2. While q is not empty, Do the following
		while(!q.isEmpty()) {

			// 2.1 Dequeue the front node from the q
			Node node = q.poll();

            /*
                u = node.u = Current vertex
                v = Adjacent vertices to u

                Probe for all the adjacent neighbors of u (i.e v)

            */
			for(int v : g.get(node.u)) {
				/*
				If they(v) aint visited earlier, Visit them(Enqueue them, Mark hem as visited)
				Additionally mark their parent
				*/
				if(!visited.contains(node.u)) {
					q.add(new Node(v, node.u));
					visited.add(v);
				}
				/*
				If v is not a parent of u
				u->v(Cross Edge) discovered, Contributes to a Cycle
				*/
				else if (v != node.parent){
					return true;
				}
			}
		}
		// No cross edges were found in the Graph.
		return false;
	}


}
