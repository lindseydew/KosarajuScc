import java.util.LinkedList;

public class KosarajuSCC {
    private boolean[] marked;     // marked[v] = has vertex v been visited?
    private int[] id;             // id[v] = id of strong component containing v
    private int count;            // number of strongly-connected components


    public KosarajuSCC(Digraph G) {

        // compute reverse postorder of reverse graph
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

        // run DFS on G, using reverse postorder to guide calculation
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v : dfs.reversePost()) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

        // check that id[] gives strong components
        assert check(G);
    }

    // DFS on graph G
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    // return the number of strongly connected components
    public int count() { return count; }

    // are v and w strongly connected?
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    // id of strong component containing v
    public int id(int v) {
        return id[v];
    }

    // does the id[] array contain the strongly connected components?
    private boolean check(Digraph G) {
        TransitiveClosure tc = new TransitiveClosure(G);
        for (int v = 0; v < G.V(); v++) {
            for (int w = 0; w < G.V(); w++) {
                if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        In in = new In("/Users/lindseydew/Documents/workspace/KosajaruScc/src/graph1n10000scc9453.txt");
        try{
            LinkedList<String> inputData = in.readFile();
            Digraph G = new Digraph(10000);
            G.buildGraphFromFile(inputData, in);
            KosarajuSCC scc = new KosarajuSCC(G);

            // number of connected components
            int M = scc.count();
            System.out.println(M + " components");

            // compute list of vertices in each strong component
            Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
            for (int i = 0; i < M; i++) {
                components[i] = new Queue<Integer>();
            }
            for (int v = 0; v < G.V(); v++) {
                components[scc.id(v)].enqueue(v);
            }

            // print results
            for (int i = 0; i < M; i++) {
                int count1 = 0;
                for (int v : components[i]) {
                    count1++;
//                System.out.print(v + " ");
                }
                System.out.println("Number " + count1);
            }



        }
        catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

}