import java.util.*;

// DFS
// Time Complexity: m * n

class dfs_solution {
    final int WATER = 0, LAND = 1;  // land and water toString
    int m, n;   // the size of the grid
    int baseRow, baseCol;   // base row for shape comparision comp.
    int[][] grid;   // a 2-d array to store the grid shape
    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Set<String> shapes = new HashSet<>();
        this.grid = grid;

        // find each islands using dfs
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(grid[row][col] == WATER) continue;
                baseRow = row;
                baseCol = col;
                StringBuilder sb = new StringBuilder();
                dfs(row, col, sb);
                shapes.add(sb.toString());
            }
        }
        return shapes.size();
    }

    private void dfs(int row, int col, StringBuilder sb) {
        // check out fo bound
        if(row < 0 || col < 0 || row == m || col == n) return;

        // check if water
        if(grid[row][col] == WATER) return;

        // append current position into sb
        sb.append(row - baseRow);
        sb.append(',');
        sb.append(col - baseCol);
        sb.append('|');

        // sink the island to mark it visted
        grid[row][col] = WATER;

        // search top
        dfs(row - 1, col, sb);

        // search right
        dfs(row, col + 1, sb);

        // search left
        dfs(row, col - 1, sb);

        // search down
        dfs(row + 1, col, sb);
    }
}

// BFS 

class bfs_solution {
    final int WATER = 0, LAND = 1;
    int m, n;
    int baseRow, baseCol;
    int[][] grid;
    final int[][] DIRECTIONS = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Set<String> shapes = new HashSet<>();
        this.grid = grid;
    

    // find each islands
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(grid[row][col] == WATER) continue;
                baseRow = row;
                baseCol = col;
                StringBuilder sb = new StringBuilder();
                bfs(row, col, sb);
                shapes.add(sb.toString());
            }
        }

        // return unique islands
        return shapes.size();
    }
    private void bfs(int row, int col, StringBuilder sb) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        grid[row][col] = WATER;

        // perform bfs
        while(!queue.isEmpty()) {
            // get first position
            int[] curPos = queue.poll();
            sb.append(curPos[0] - baseRow);
            sb.append(curPos[1]- baseCol);
            // add all its neighbors (all 4 directions)
            for (int[] direction : DIRECTIONS) {
                int curRow = curPos[0] + direction[0];
                int curCol = curPos[1] + direction[1];
                if(validPosition(curRow, curCol)){
                    queue.add(new int[]{curRow, curCol});
                    grid[curRow][curCol] = WATER;
                }
            }
        }
    }
    private boolean validPosition(int row, int col) {
        if(row < 0 || col < 0 || row == m || col == n) return false;
        if(grid[row][col] == WATER) return false;
        return true;
    }
}