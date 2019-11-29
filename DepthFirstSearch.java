package gridfill;

public class DepthFirstSearch {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s, true);
    }

    private void dfs(Graph G, int v, boolean firstTime) {
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                if (GridFill.mat[w / GridFill.mat.length][w % GridFill.mat.length] == 0) {
                    GridFill.mat[w / GridFill.mat.length][w % GridFill.mat.length] = 1;
                } else {
                    GridFill.mat[w / GridFill.mat.length][w % GridFill.mat.length] = 0;
                }
                dfs(G, w);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                if (GridFill.mat[w / GridFill.mat.length][w % GridFill.mat.length] == 0) {
                    GridFill.mat[w / GridFill.mat.length][w % GridFill.mat.length] = 1;
                } else {
                    GridFill.mat[w / GridFill.mat.length][w % GridFill.mat.length] = 0;
                }
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
