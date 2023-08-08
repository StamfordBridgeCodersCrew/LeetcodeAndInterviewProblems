package Graphs;

import java.util.List;
import java.util.Set;

public class NumberOfOperationsToMakeNetworkConnected {

        public static void dfs1(List<List<Integer>> g, int u, Set<Integer> visited) {

                for(int v : g.get(u)) {

                        // If v aint visited earlier
                        // Visit v
                        if(!visited.contains(v)) {
                                // Update visited
                                visited.add(v);

                                // Start a DFS from vertex = v
                                dfs1(g, v, visited);
                        }
                }
        }

        public static void main(String[] args) {

        }
}
