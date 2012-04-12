import org.apache.commons.collections.*;

import java.util.LinkedList;
import java.util.Stack;

public class Digraph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    /**
     * Create an empty digraph with V vertices.
     */
    public Digraph(int V) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }
    /**
     * Copy constructor.
     */
    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
    
    public void buildGraphFromFile(LinkedList<String> inputFile, In in) {
        try{
            inputFile = in.readFile();

            for(String line : inputFile) {
                String[] lineByLine = line.split("\\s+");

                int[] numbersInLine = new int[lineByLine.length];
                int j = 0;
                //populate the integer array
                for (String s : lineByLine) {
                    int num = Integer.parseInt(s);
                    numbersInLine[j] = num;
                    j++;

                }
                int vertex1 = numbersInLine[0];
                int vertex2 = numbersInLine[1];
                addEdge(vertex1 - 1, vertex2 - 1);
            }
        }
        catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Fileread error");

        }
        
        
    }

    /**
     * Return the number of vertices in the digraph.
     */
    public int V() {
        return V;
    }

    /**
     * Return the number of edges in the digraph.
     */
    public int E() {
        return E;
    }

    /**
     * Add the directed edge v-w to the digraph.
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    /**
     * Return the list of neighbors of vertex v as in Iterable.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * Return the reverse of the digraph.
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                R.addEdge(w - 1, v);
                System.out.println("w " + w + " v " + v);
            }
        }
        return R;
    }

    /**
     * Return a string representation of the digraph.
     */
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        String NEWLINE = System.getProperty("line.separator");
////        s.append(V + " " + E + NEWLINE);
//        for (int v = 0; v < V; v++) {
//            s.append(v + ": ");
//            for (int w : adj[v]) {
//                s.append(w + " ");
//            }
//            s.append(NEWLINE);
//        }
//        return s.toString();
//    }

    /**
     * Test client.
//     */
    public static void main(String[] args) {
        In in = new In("/Users/lindseydew/Documents/workspace/KosajaruScc/src/test[0,3,3,4].txt");
        Digraph G = new Digraph(10);
        try{
        G.buildGraphFromFile(in.readFile(), in);
        System.out.println(G);
        System.out.println();
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj[v])
                System.out.println(v + "->" + w);
        }
        catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

}