package dp.classic;


import java.util.*;

public class LeetCode_778_SwimInRisingWater {
    public static void main(String[] args) {
        int [][]grid = {
                {0, 1, 2, 3, 4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9, 8, 7, 6}
        };
        System.out.println(swimInWater2(grid));
    }

    /**
     * 这是网上最快的写法
     * 原因有3
     * 1.根据题目条件grid[i][j] 位于区间 [0, ..., N*N - 1] 内。 二分查找 -- 优化之前值按所有grid里的值依次找
     * 2.visited使用二维数据而不是用整数然后 / % 解析
     * 3.只用判断能否走通，每次都从0出发，所以不需要用BFS，直接DFS就可以，省去队列的维护。
     */
    public static int swimInWater(int[][] grid) {
        int N = grid.length;
        int lf = 0;
        int rt = N * N;
        while (lf < rt) {
            int mid = lf + (rt - lf) / 2;
            if (go(grid, 0, 0, new boolean[N][N], mid)) {
                rt = mid;
            }
            else {
                lf = mid + 1;
            }
        }
        return rt;
    }

    private static int[][] dir = {{-1, 0},{0, 1},{0, -1},{1, 0}};
    private static boolean go(int[][] grid, int i, int j, boolean[][] vis, int mid) {
        int N = grid.length;
        if (i == N - 1 && j == N - 1) return true;
        vis[i][j] = true;
        for (int[] d : dir) {
            int ni = i + d[0];
            int nj = j + d[1];
            /**
             * Math.max(grid[ni][nj], mid) == Math.max(grid[i][j], mid)
             * 这句是水位限制的体现
             */
            if (ni >= 0 && ni < N && nj >= 0 && nj < N && !vis[ni][nj] && Math.max(grid[ni][nj], mid) == Math.max(grid[i][j], mid)) {
                if (go(grid, ni, nj, vis, mid)) return true;
            }
        }
        return false;
    }
    /**
     * DP + DFS
     * dp[i][j]代表达到grid[i][j]所需最小水位
     * dp[i][j]可从四个方向到达
     * dp[i][j] = max(grid[i][j], min(上下左右))
     * 这题的递推过程是和DFS搜索过程结合的 第一次见 非常有意思
     */
    public static int swimInWater2(int[][] grid) {
        int [][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int n = grid.length;
        int[][] dp  = new int[n][n];
        /**
         * 便于用 Math.max(cur, grid[x][y]) >= dp[x][y] 的方式来判断visited
         */
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        helper(grid, 0, 0, grid[0][0], dp, dirs);
        return dp[n - 1][n - 1];
    }
    public static void helper(int[][] grid, int x, int y, int cur, int[][] dp, int[][]dirs) {
        int n = grid.length;
        /**
         *  Math.max(cur, grid[x][y]) >= dp[x][y] 除了判断visited
         *  还能让更小的值更新过来
         */
        if (x < 0 || x >= n || y < 0 || y >= n || Math.max(cur, grid[x][y]) >= dp[x][y]) return;
        dp[x][y] = Math.max(cur, grid[x][y]);
        for (int[] dir : dirs) {
            helper(grid, x + dir[0], y + dir[1], dp[x][y], dp, dirs);
        }
    }
}
