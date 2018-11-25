package unionfind;

public class LeetCode_200_NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
        if (grid == null || grid.length == 0 || grid[0].length == 0)  {
            return 0;
        }
        UnionFindTwo uf = new UnionFindTwo(grid);
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    for (int[] d : distance) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                            int id1 = i*cols + j;
                            int id2 = x*cols + y;
                            uf.union(id1, id2);
                        }
                    }
                }
            }
        }
        return uf.count;
    }

    public static void dfs(char[][] matrix,int i,int j,int m,int n)
    {
        if(i >= 0 && i < m && j >= 0 && j < n && matrix[i][j] == '1'){
            matrix[i][j]='0';
            dfs(matrix,i + 1, j, m, n);
            dfs(matrix,i - 1, j, m, n);
            dfs(matrix, i,j + 1, m, n);
            dfs(matrix, i,j - 1, m, n);
        }
    }

    public static int numIslands2(char[][] grid) {
        if(0 == grid.length || 0 == grid[0].length)
            return 0;
        int m = grid.length;
        int n = grid[0].length,cnt = 0;

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    cnt++;
                }
        }
        return cnt;
    }
}
