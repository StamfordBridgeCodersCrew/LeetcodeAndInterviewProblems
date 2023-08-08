package Graphs;

import java.util.*;

public class NumberOfProvinces {
        /*
        Perform BFS on Graph G starting from source vertex = source
        visited = To maintain a record of vertices already visited

        g = Adjacency list representation of the graph
        visited = HashSet
        */
        public static void bfs1(List<List<Integer>> g, int source, Set<Integer> visited ) {

                // Insert the source vertex in the Q
                List<Integer> q = new LinkedList<>();
                q.add(source);

                // Update HashSet visited
                visited.add(source);

                while(!q.isEmpty()) {
                        /*
                        Take out the element from the front of the Q
                        int u = q.front()
                        q.pop();

                        * */
                        int u = q.remove(0);

                        /*
                        u = Current vertex
                        v = Adjacent vertices to u

                        for each adjacent neighbor of u
                        If v  aint visited earlier
                        Visit it
                        */
                        for(int v : g.get(u)) {
                                if(!visited.contains(v)) {
                                        // Add to the Q
                                        q.add(v);

                                        // Update visited
                                        visited.add(v);
                                }
                        }
                }
        }

        /*
        Perform DFS traversal on Graph G starting from vertex = u
        visited = To maintain a record of vertices already visited

        g = Adjacency list representation of the graph
        visited = HashSet
        */
        public static void dfs1(List<List<Integer>> g, int u, Set<Integer> visited) {
                visited.add(u);

                for(int v : g.get(u)) {
                        if(!visited.contains(v)) {
                                // Update visited
                                visited.add(v);

                                // Perform a DFS starting at vertex v
                                dfs1(g, v, visited);
                        }
                }
        }

        // Calculate the number of connected components
        // isconnected = Adjacency matrix representation of the graph

        // NOTE :- We are dealing with a Undirected graph
        public static int numberOfConnectedComponents(List<List<Integer>> isconnected) {
                // n = m, obvo
                int n = isconnected.size();
                int m = isconnected.get(0).size();

                // isconnected = Adjacency Matrix representation of the Graph
                // g = Adjacency List representation of the Graph
                List<List<Integer>> g = new ArrayList<>();
                for(int i=0; i<n; i++) {
                        g.add(new ArrayList<>());

                        for(int j=0; j<n; j++) {
                                if( (i!=j) && (isconnected.get(i).get(j) == 1) ) {
                                        /*
                                        g[i].push_back(j)
                                        g[j].push_back(i)
                                        */
                                        g.get(i).add(j);
                                        g.get(j).add(i);
                                }
                        }
                }

                // Count the number of connected components in the graph using BFS

                // Count the number of connected components in the Graph using DFS
                Set<Integer> visited = new HashSet<>();
                int num_of_connected_components = 0;
                for(int i=0; i<n; i++) {
                        if(!visited.contains(i)) {
                                //bfs1(g, i, visited);
                                dfs1(g, i, visited);
                                num_of_connected_components += 1;
                        }
                }

                return num_of_connected_components;
        }

        public static void main(String[] args) {

        }
}
