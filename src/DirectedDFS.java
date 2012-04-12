import org.apache.commons.collections.*;

public class DirectedDFS {
    private boolean[] marked;  // marked[v] = true if v is reachable
    // from source (or sources)

    // single-source reachability
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // multiple-source reachability
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources)
            dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    // is there a directed path from the source (or sources) to v?
    public boolean marked(int v) {
        return marked[v];
    }

    // test client
//    public static void main(String[] args) {
//
//        // read in digraph from command-line argument
//        In in = new In("/Users/lindseydew/Documents/workspace/KosajaruScc/src/test.txt");
//        Digraph G = new Digraph(in, 9);
//
//        // read in sources from command-line arguments
//        Bag<Integer> sources = new Bag<Integer>();
//        for (int i = 1; i < args.length; i++) {
//            int s = Integer.parseInt(args[i]);
//            sources.add(s);
//        }
//
//        // multiple-source reachability
//        DirectedDFS dfs = new DirectedDFS(G, sources);
//
//        // print out vertices reachable from sources
//        for (int v = 0; v < G.V(); v++) {
//            if (dfs.marked(v)) System.out.print(v + " ");
//        }
//        System.out.println();
//    }

}