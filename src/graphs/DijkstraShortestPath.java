
// Dijkstra’s shortest path algorithm
// Given a graph and a source vertex in graph, find shortest paths from source to all vertices in the given graph.

import java.util.*;
import java.lang.*;
import java.io.*;
 
class ShortestPath
{
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V=9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    void printSolution(int dist[], int n)
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i+" tt "+dist[i]);
    }
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        // print the constructed distance array
        printSolution(dist, V);
    }
 
    // Driver method
    public static void main (String[] args)
    {
        /* Let us create the example graph discussed above */
       int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                  {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                  {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                  {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                  {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                  {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                  {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                  {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                  {0, 0, 2, 0, 0, 0, 6, 7, 0}
                                 };
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0);
    }
}
// Time Complexity of the implementation is O(V^2)
// If the input graph is represented using adjacency list, it can be reduced to O(E log V) with the help of binary heap
// Dijkstra’s algorithm doesn’t work for graphs with negative weight edges. 
// For graphs with negative weight edges, Bellman–Ford algorithm can be used

// -------------------------------------------------------------------------------------------------------------------
// Java Program for Dijkstra’s Algorithm with Path Printing
import java.util.Scanner; //Scanner Function to take in the Input Values
 
public class Dijkstra
{
    static Scanner scan; // scan is a Scanner Object
 
    public static void main(String[] args)
    {
        int[] preD = new int[5];
        int min = 999, nextNode = 0; // min holds the minimum value, nextNode holds the value for the next node.
        scan = new Scanner(System.in);
        int[] distance = new int[5]; // the distance matrix
        int[][] matrix = new int[5][5]; // the actual matrix
        int[] visited = new int[5]; // the visited array
 
        System.out.println("Enter the cost matrix");
 
        for (int i = 0; i < distance.length; i++)
        {
            visited[i] = 0; //initialize visited array to zeros
            preD[i] = 0;
 
            for (int j = 0; j < distance.length; j++)
            {
                matrix[i][j] = scan.nextInt(); //fill the matrix
                if (matrix[i][j]==0)
                    matrix[i][j] = 999; // make the zeros as 999
            }
        }
 
        distance = matrix[0]; //initialize the distance array
        visited[0] = 1; //set the source node as visited
        distance[0] = 0; //set the distance from source to source to zero which is the starting point
 
        for (int counter = 0; counter < 5; counter++)
        {
            min = 999;
            for (int i = 0; i < 5; i++)
            {
                if (min > distance[i] && visited[i]!=1)
                {
                    min = distance[i];
                    nextNode = i;
                }
            }
 
            visited[nextNode] = 1;
            for (int i = 0; i < 5; i++)
            {
                if (visited[i]!=1)
                {
                    if (min+matrix[nextNode][i] < distance[i])
                    {
                        distance[i] = min+matrix[nextNode][i];
                        preD[i] = nextNode;
                    }
 
                }
 
            }
 
        }
 
        for(int i = 0; i < 5; i++)
            System.out.print("|" + distance[i]);
 
        System.out.println("|");
 
        int j;
        for (int i = 0; i < 5; i++)
        {
            if (i!=0)
            {
 
                System.out.print("Path = " + i);
                j = i;
                do
                {
                    j = preD[j];
                    System.out.print(" <- " + j);
                }
                while(j != 0);
            }
            System.out.println();
        }
    }
}

// 




