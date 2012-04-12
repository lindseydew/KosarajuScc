//import java.util.Queue;
import java.util.Stack;

public class DepthFirstOrder {
    private boolean[] marked;          // marked[v] = has v been marked in dfs?
    private int[] pre;                 // pre[v]    = preorder  number of v
    private int[] post;                // post[v]   = postorder number of v
    private Queue<Integer> preorder;   // vertices in preorder
    private Queue<Integer> postorder;  // vertices in postorder
    private int preCounter;            // counter or preorder numbering
    private int postCounter;           // counter for postorder numbering

    // depth-first search preorder and postorder in a digraph
    public DepthFirstOrder(Digraph G) {
        pre    = new int[G.V()];
        post   = new int[G.V()];
        postorder = new Queue<Integer>();
        preorder  = new Queue<Integer>();
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    // depth-first search preorder and postorder in an edge-weighted digraph
//    public DepthFirstOrder(EdgeWeightedDigraph G) {
//        pre    = new int[G.V()];
//        post   = new int[G.V()];
//        postorder = new Queue<Integer>();
//        preorder  = new Queue<Integer>();
//        marked    = new boolean[G.V()];
//        for (int v = 0; v < G.V(); v++)
//            if (!marked[v]) dfs(G, v);
//    }

    // run DFS in digraph G from vertex v and compute preorder/postorder
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preorder.enqueue(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    // run DFS in edge-weighted digraph G from vertex v and compute preorder/postorder
//    private void dfs(EdgeWeightedDigraph G, int v) {
//        marked[v] = true;
//        pre[v] = preCounter++;
//        preorder.enqueue(v);
//        for (DirectedEdge e : G.adj(v)) {
//            int w = e.to();
//            if (!marked[w]) {
//                dfs(G, w);
//            }
//        }
//        postorder.enqueue(v);
//        post[v] = postCounter++;
//    }

    public int pre(int v) {
        return pre[v];
    }

    public int post(int v) {
        return post[v];
    }

    // return vertices in postorder as an Iterable
    public Iterable<Integer> post() {
        return postorder;
    }

    // return vertices in postorder as an Iterable
    public Iterable<Integer> pre() {
        return preorder;
    }

    // return vertices in reverse postorder as an Iterable
    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : postorder)
            reverse.push(v);
        return reverse;
    }


    // certify that digraph is either acyclic or has a directed cycle
    private boolean check(Digraph G) {

        // check that post(v) is consistent with post()
        int r = 0;
        for (int v : post()) {
            if (post(v) != r) {
                System.out.println("post(v) and post() inconsistent");
                return false;
            }
            r++;
        }

        // check that pre(v) is consistent with pre()
        r = 0;
        for (int v : pre()) {
            if (pre(v) != r) {
                System.out.println("pre(v) and pre() inconsistent");
                return false;
            }
            r++;
        }


        return true;
    }

//    public static void main(String[] args) {
//        In in = new In("/Users/lindseydew/Documents/workspace/KosajaruScc/src/test.txt");
//        Digraph G = new Digraph(in, 9);
//
//        DepthFirstOrder dfs = new DepthFirstOrder(G);
//        System.out.println("   v  pre post");
//        System.out.println("--------------");
//        for (int v = 0; v < G.V(); v++) {
//            System.out.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
//        }
//
//        System.out.print("Preorder:  ");
//        for (int v : dfs.pre()) {
//            System.out.print(v + " ");
//        }
//        System.out.println();
//
//        System.out.print("Postorder: ");
//        for (int v : dfs.post()) {
//            System.out.print(v + " ");
//        }
//        System.out.println();
//
//        System.out.print("Reverse postorder: ");
//        for (int v : dfs.reversePost()) {
//            System.out.print(v + " ");
//        }
//        System.out.println();
//
//
//    }

}