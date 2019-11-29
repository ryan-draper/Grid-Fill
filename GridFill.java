package gridfill;
import java.util.*;

public class GridFill {

    public static Integer[][] mat = new Integer[15][15]; // assume mat.length == mat[i].length
    
    public static void main(String[] args) {
        for(int r = 0; r < mat.length; r++) {
            for(int c = 0; c < mat[0].length; c++) {
                mat[r][c] = (int)(Math.random() + 0.5);
            }
        }
        for(int r = 0; r < mat.length; r++) {
            for(int c = 0; c < mat[0].length; c++) {
                System.out.print(" " + mat[r][c]);
            }
            System.out.println();
        }
        
        Graph g = new Graph(mat.length * mat.length);
        for(int r = 0; r < mat.length; r++) {
            for(int c = 0; c < mat[0].length; c++) {
                if(r == 0 && c == 0) {
                    // TOP LEFT CORNER
                    if(mat[r][c] == mat[mat.length / mat.length][mat.length % mat.length])
                        g.addEdge(0, mat.length);
                    if(mat[r][c] == mat[1 / mat.length][1 % mat.length])
                        g.addEdge(0, 1);
                } else if(r == 0 && c == mat.length - 1) {
                    // TOP RIGHT CORNER
                    if(mat[r][c] == mat[(mat.length - 2) / mat.length][(mat.length - 2) % mat.length])
                        g.addEdge(mat.length - 1, mat.length - 2);
                    if(mat[r][c] == mat[(2*mat.length - 1) / mat.length][(2*mat.length - 1) % mat.length])
                        g.addEdge(mat.length - 1, 2*mat.length - 1);
                } else if(r == mat.length - 1 && c == 0) {
                    // BOTTOM LEFT CORNER
                    if(mat[r][c] == mat[((mat.length - 1)*(mat.length) + 1) / mat.length][((mat.length - 1)*(mat.length) + 1) % mat.length])
                        g.addEdge((mat.length - 1)*(mat.length), (mat.length - 1)*(mat.length) + 1);
                    if(mat[r][c] == mat[((mat.length - 1)*(mat.length - 1) - 1) / mat.length][((mat.length - 1)*(mat.length - 1) - 1) % mat.length])
                        g.addEdge((mat.length - 1)*(mat.length), (mat.length - 1)*(mat.length - 1) - 1);
                } else if(r == mat.length - 1 && c == mat.length - 1) {
                    // BOTTOM RIGHT CORNER
                    if(mat[r][c] == mat[((mat.length)*(mat.length) - 2) / mat.length][((mat.length)*(mat.length) - 2) % mat.length])
                        g.addEdge((mat.length)*(mat.length) - 1, (mat.length)*(mat.length) - 2);
                    if(mat[r][c] == mat[((mat.length)*(mat.length - 1) - 1) / mat.length][((mat.length)*(mat.length - 1) - 1) % mat.length])
                        g.addEdge((mat.length)*(mat.length) - 1, (mat.length)*(mat.length - 1) - 1);
                } else if(r == 0) {
                    // TOP EDGE
                    if(mat[r][c] == mat[((r + 1)*c + mat.length) / mat.length][((r + 1)*c + mat.length) % mat.length])
                        g.addEdge(((r + 1)*c), ((r + 1)*c + mat.length));
                    if(mat[r][c] == mat[(((r + 1)*c) + 1) / mat.length][(((r + 1)*c) + 1) % mat.length])
                        g.addEdge(((r + 1)*c), ((r + 1)*c) + 1);
                    if(mat[r][c] == mat[((r + 1)*c - 1) / mat.length][((r + 1)*c - 1) % mat.length])
                        g.addEdge(((r + 1)*c), ((r + 1)*c) - 1);
                } else if(r == mat.length - 1) {
                    // BOTTOM EDGE
                    if(mat[r][c] == mat[((r)*mat.length + c - mat.length) / mat.length][((r)*mat.length + c - mat.length) % mat.length])
                        g.addEdge(((r)*mat.length + c), ((r)*mat.length + c - mat.length));
                    if(mat[r][c] == mat[((r)*mat.length + c - 1) / mat.length][((r)*mat.length + c - 1) % mat.length])
                        g.addEdge(((r)*mat.length + c), ((r)*mat.length + c - 1));
                    if(mat[r][c] == mat[((r)*mat.length + c + 1) / mat.length][((r)*mat.length + c + 1) % mat.length])
                        g.addEdge(((r)*mat.length + c), ((r)*mat.length + c + 1));
                } else if(c == 0) {
                    // LEFT EDGE
                    if(mat[r][c] == mat[((r)*mat.length + 1) / mat.length][((r)*mat.length + 1) % mat.length])
                        g.addEdge((r)*mat.length, (r)*mat.length + 1);
                    if(mat[r][c] == mat[((r)*mat.length + mat.length) / mat.length][((r)*mat.length + mat.length) % mat.length])
                        g.addEdge((r)*mat.length, (r)*mat.length + mat.length);
                    if(mat[r][c] == mat[((r)*mat.length - mat.length) / mat.length][((r)*mat.length - mat.length) % mat.length])
                        g.addEdge((r)*mat.length, (r)*mat.length - mat.length);
                } else if(c == mat.length - 1) {
                    // RIGHT EDGE
                    if(mat[r][c] == mat[((r+1)*mat.length - 2) / mat.length][((r+1)*mat.length - 2) % mat.length])
                        g.addEdge((r+1)*mat.length - 1, (r+1)*mat.length - 2);
                    if(mat[r][c] == mat[((r+1)*mat.length - 1 + mat.length) / mat.length][((r+1)*mat.length - 1 + mat.length) % mat.length])
                        g.addEdge((r+1)*mat.length - 1, (r+1)*mat.length - 1 + mat.length);
                    if(mat[r][c] == mat[((r+1)*mat.length - 1 - mat.length) / mat.length][((r+1)*mat.length - 1 - mat.length) % mat.length])
                        g.addEdge((r+1)*mat.length - 1, (r+1)*mat.length - 1 - mat.length);
                } else {
                    // MIDDLE
                    int sum = r*mat.length + c;
                    if(mat[r][c] == mat[(sum + 1) / mat.length][(sum + 1) % mat.length])
                        g.addEdge(sum, sum + 1);
                    if(mat[r][c] == mat[(sum - 1) / mat.length][(sum - 1) % mat.length])
                        g.addEdge(sum, sum - 1);
                    if(mat[r][c] == mat[(sum - mat.length) / mat.length][(sum - mat.length) % mat.length])
                        g.addEdge(sum, sum - mat.length);
                    if(mat[r][c] == mat[(sum + mat.length) / mat.length][(sum + mat.length) % mat.length])
                        g.addEdge(sum, sum + mat.length);
                }
            }
        }
        
        Scanner s = new Scanner(System.in);
        System.out.println("\nEnter the row and column (where r = {0,1,2...} and c = {0,1,2...}) "
                + "of the cell you want to fill, each on their own line:");
        
        int row = Integer.parseInt(s.nextLine());
        int col = Integer.parseInt(s.nextLine());
        int sum = row*mat.length + col;
        
        DepthFirstSearch dfs = new DepthFirstSearch(g, sum);
        
        for(int r = 0; r < mat.length; r++) {
            for(int c = 0; c < mat[0].length; c++) {
                System.out.print(" " + mat[r][c]);
            }
            System.out.println();
        }
    }
}

/*
SAMPLE OUTPUTS:

 1 0 1 0 1 0 1 1 1 1 0 0 0 0 0
 1 1 0 0 1 0 0 0 1 0 1 0 1 0 1
 0 1 1 1 0 1 1 0 1 0 0 1 0 0 1
 1 1 1 1 0 0 0 1 0 1 1 1 0 0 0
 0 0 0 1 1 0 0 1 0 1 0 0 0 1 0
 1 0 0 0 0 0 0 0 0 0 1 1 0 1 1
 1 1 1 0 0 1 0 1 0 1 0 1 0 1 0
 1 1 1 0 1 0 1 1 1 0 0 1 0 0 1
 0 1 0 1 0 0 1 1 0 1 0 1 1 1 1
 0 1 0 1 1 1 0 1 0 1 1 1 1 1 0
 0 0 1 1 1 1 0 0 0 0 1 1 1 0 1
 0 1 1 1 0 1 0 1 1 0 0 0 0 1 1
 0 1 0 0 1 0 1 0 1 1 0 0 1 1 0
 0 0 0 1 1 1 0 1 0 0 1 1 0 0 0
 0 0 1 0 1 1 1 0 1 0 0 0 0 1 0

Enter the row and column (where r = {0,1,2...} and c = {0,1,2...}) of the cell you want to fill, each on their own line:
7
7
 1 0 1 0 1 0 1 1 1 1 0 0 0 0 0
 1 1 0 0 1 0 0 0 1 0 1 0 1 0 1
 0 1 1 1 0 1 1 0 1 0 0 1 0 0 1
 1 1 1 1 0 0 0 1 0 1 1 1 0 0 0
 0 0 0 1 1 0 0 1 0 1 0 0 0 1 0
 1 0 0 0 0 0 0 0 0 0 1 1 0 1 1
 1 1 1 0 0 1 0 0 0 1 0 1 0 1 0
 1 1 1 0 1 0 0 0 0 0 0 1 0 0 1
 0 1 0 1 0 0 0 0 0 1 0 1 1 1 1
 0 1 0 1 1 1 0 0 0 1 1 1 1 1 0
 0 0 1 1 1 1 0 0 0 0 1 1 1 0 1
 0 1 1 1 0 1 0 1 1 0 0 0 0 1 1
 0 1 0 0 1 0 1 0 1 1 0 0 1 1 0
 0 0 0 1 1 1 0 1 0 0 1 1 0 0 0
 0 0 1 0 1 1 1 0 1 0 0 0 0 1 0

--------------------------------------------------------------------------------

 1 0 1 0 1 0 0 1 0 1 0 1 1 1 1
 0 0 0 1 0 1 0 1 0 1 1 0 1 0 0
 1 1 1 0 0 0 0 1 0 1 1 1 1 0 1
 0 1 1 0 0 1 1 1 1 1 0 1 1 0 1
 1 0 1 0 1 1 1 0 1 1 0 1 1 1 1
 0 1 1 0 0 1 0 1 1 0 1 1 1 0 0
 0 0 1 1 0 1 0 1 1 1 0 0 1 0 0
 0 0 0 0 0 0 0 1 1 0 0 0 0 1 1
 1 0 1 0 0 0 1 1 1 0 1 1 1 1 0
 0 1 1 1 0 1 0 0 0 1 0 0 0 1 0
 1 0 1 0 1 1 1 1 1 1 1 0 0 0 1
 0 0 1 1 1 0 1 0 0 1 1 1 0 1 1
 1 1 0 1 0 0 1 0 0 1 1 0 1 0 0
 1 0 1 1 1 0 1 1 1 1 1 0 0 0 1
 1 1 1 0 0 0 1 1 0 1 0 1 0 0 1

Enter the row and column (where r = {0,1,2...} and c = {0,1,2...}) of the cell you want to fill, each on their own line:
1
1
 1 1 1 0 1 0 0 1 0 1 0 1 1 1 1
 1 1 1 1 0 1 0 1 0 1 1 0 1 0 0
 1 1 1 0 0 0 0 1 0 1 1 1 1 0 1
 0 1 1 0 0 1 1 1 1 1 0 1 1 0 1
 1 0 1 0 1 1 1 0 1 1 0 1 1 1 1
 0 1 1 0 0 1 0 1 1 0 1 1 1 0 0
 0 0 1 1 0 1 0 1 1 1 0 0 1 0 0
 0 0 0 0 0 0 0 1 1 0 0 0 0 1 1
 1 0 1 0 0 0 1 1 1 0 1 1 1 1 0
 0 1 1 1 0 1 0 0 0 1 0 0 0 1 0
 1 0 1 0 1 1 1 1 1 1 1 0 0 0 1
 0 0 1 1 1 0 1 0 0 1 1 1 0 1 1
 1 1 0 1 0 0 1 0 0 1 1 0 1 0 0
 1 0 1 1 1 0 1 1 1 1 1 0 0 0 1
 1 1 1 0 0 0 1 1 0 1 0 1 0 0 1

--------------------------------------------------------------------------------

 0 1 0 0 0 0 1 1 0 0 1 1 1 1 1
 1 1 0 0 0 0 1 1 0 0 0 1 0 0 0
 0 1 0 0 1 1 1 0 0 1 1 0 0 1 1
 1 1 1 0 0 0 1 0 1 0 0 1 1 1 0
 0 1 0 1 1 1 0 0 0 0 1 0 1 1 0
 1 1 1 1 1 1 1 1 0 0 0 1 0 1 1
 0 0 1 0 0 0 0 0 1 1 1 0 1 0 0
 1 1 0 1 1 1 1 0 0 0 0 1 0 1 0
 1 1 0 0 0 1 0 0 1 1 1 0 0 0 0
 0 1 1 1 1 1 0 0 1 1 0 0 1 1 0
 1 1 1 0 0 0 1 0 1 0 1 0 0 0 0
 1 1 1 1 1 0 0 1 1 1 1 1 0 1 0
 0 1 0 1 0 1 0 0 1 1 0 1 1 0 1
 0 1 1 1 0 0 0 1 1 0 0 0 1 0 0
 0 0 1 1 1 1 0 0 0 1 0 0 0 1 0

Enter the row and column (where r = {0,1,2...} and c = {0,1,2...}) of the cell you want to fill, each on their own line:
5
0
 0 0 0 0 0 0 1 1 0 0 1 1 1 1 1
 0 0 0 0 0 0 1 1 0 0 0 1 0 0 0
 0 0 0 0 1 1 1 0 0 1 1 0 0 1 1
 0 0 0 0 0 0 1 0 1 0 0 1 1 1 0
 0 0 0 0 0 0 0 0 0 0 1 0 1 1 0
 0 0 0 0 0 0 0 0 0 0 0 1 0 1 1
 0 0 0 0 0 0 0 0 1 1 1 0 1 0 0
 1 1 0 1 1 1 1 0 0 0 0 1 0 1 0
 1 1 0 0 0 1 0 0 1 1 1 0 0 0 0
 0 1 1 1 1 1 0 0 1 1 0 0 1 1 0
 1 1 1 0 0 0 1 0 1 0 1 0 0 0 0
 1 1 1 1 1 0 0 1 1 1 1 1 0 1 0
 0 1 0 1 0 1 0 0 1 1 0 1 1 0 1
 0 1 1 1 0 0 0 1 1 0 0 0 1 0 0
 0 0 1 1 1 1 0 0 0 1 0 0 0 1 0

*/