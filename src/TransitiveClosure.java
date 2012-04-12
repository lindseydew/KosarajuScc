public class TransitiveClosure {
    private DirectedDFS[] tc;  // tc[v] = reachable from v

    public TransitiveClosure(Digraph G) {
        tc = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++)
            tc[v] = new DirectedDFS(G, v);
    }

    public boolean reachable(int v, int w) {
        return tc[v].marked(w);
    }

    // test client
//    public static void main(String[] args) {
//        In in = new In("/Users/lindseydew/Documents/workspace/KosajaruScc/src/test.txt");
//        Digraph G = new Digraph(in, 9);
//
//        TransitiveClosure tc = new TransitiveClosure(G);
//
//        // print header
//        System.out.print("     ");
//        for (int v = 0; v < G.V(); v++)
//            System.out.printf("%3d", v);
//        System.out.println();
//        System.out.println("--------------------------------------------");
//
//        // print transitive closure
//        for (int v = 0; v < G.V(); v++) {
//            System.out.printf("%3d: ", v);
//            for (int w = 0; w < G.V(); w++) {
//                if (tc.reachable(v, w)) System.out.printf("  T");
//                else                    System.out.printf("   ");
//            }
//            System.out.println();
//        }
//    }

}