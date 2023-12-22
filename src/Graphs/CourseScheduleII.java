package Graphs;

import java.util.*;

public class CourseScheduleII {

    /*
    Constructing Adj List reprsn of a Directed Graph from the following info -
        edge_info
        num_of_vertices = n
    * */
    public List<List<Integer>> constructAdjList(int[][] edge_info, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edge_info) {
            int from = edge[0];
            int to = edge[1];

            // We are constructin Adj List of a Directed Graph
            // graph[from].push_back(to);
            graph.get(from).add(to);
        }

        return graph;
    }
    /*
    graph = Adj List representation of the graph
    */
    public static List<Integer> computeIndegree(List<List<Integer>> graph) {
        int n = graph.size();
        List<Integer> indegree = new ArrayList<>(Collections.nCopies(n, 0));

        for(int u=0; u<n; u++) {
            for(int v : graph.get(u)) {
                // indegree[v] = indegree[v] + 1;
                indegree.set(v, indegree.get(v)+1);
            }
        }

        return indegree;
    }
    /*
    This problem is equivalent to generating topo orders of G


    If the graph aint a DAG, No topological ordering exists
    Therefore it will be impossible to take all courses.
    We would return an empty list in that case

    graph = Adj List representation of the graph
    We will be utilizing Mr Oliver kahn's algorithm

    Bite sized concept
            Q = Holds all vertices whose indegree = 0

    Every time , you pop an item from the Queue
    You include it in topo_order
    */

    /*
    Essentially, We will be doing BFS
    Except that the Q will store nodes (Whose indegree = 0)
    * */
    public static List<Integer> generateTopoOrders(List<List<Integer>> graph) {
        int n = graph.size();

        // Compute the indegree of each of the vertices
        List<Integer> indegree = computeIndegree(graph);

        Queue<Integer> q = new LinkedList<>();

        // List to store 1 Topo ordering of the Graph
        List<Integer> topo_order = new ArrayList<>();

        // Insert the source vertex of the DAG into the Q
        for(int i=0; i<n; i++) {
            if(indegree.get(i) == 0)
                q.offer(i);
        }

        while (!q.isEmpty()) {

            // Remove the front element of the Q
            int u = q.poll();

            // Enter this front element into answer
            topo_order.add(u);

            for(int v : graph.get(u)) {
                /*
                Since we are removing and all its associated edges

                Decrease the indegree of all v's(immediate neighbors of v by 1)
                indegree[v] = indegree[v] - 1;
                * */
                indegree.set(v, indegree.get(v));

                // If indegree[v] = 0, Push it into the Q
                if(indegree.get(v) == 0) {
                    q.offer(v);
                }
            }
        }

        // If graph aint a DAG
        // Return an empty Array List
        if(topo_order.size() != n) {
            return new ArrayList<>();
        }

        // Else return one of the Topological orderings
        return topo_order;
    }

    public void findOrder(int numCourses, int[][] prerequisites) {

    }
    public static void main(String[] args) {

    }
}
