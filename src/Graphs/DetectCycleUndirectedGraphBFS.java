package Graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DetectCycleUndirectedGraphBFS {

        // Detect a cycle in an Undirected graph using BFS
        public static boolean detectCycleBFS(List<List<Integer>> g, int source, Set<Integer> visited, int[] parent) {
                List<Integer> q = new LinkedList<>();
                q.add(source);

                visited.add(source);

                while (!q.isEmpty()) {
                        int u = q.remove(0);

                        /*
                        u --> v
                        u = Current vertex
                        v = Adjacent vertices to u

                        for each adjacent neighbor of u
                        If v  aint visited earlier
                        Visit it
                        */
                        for(int v : g.get(u)) {

                                if(!visited.contains(v)) {
                                        q.add(v);
                                        visited.add(v);
                                        parent[v] = u;
                                }
                                /*
                                u --> v
                                (If v is already visited) AND (v is not the parent of u)
                                Then there exists a cycle
                                */
                                else if(parent[u] != v) {
                                        return true;
                                }

                        }
                }

                return false;
        }
}
