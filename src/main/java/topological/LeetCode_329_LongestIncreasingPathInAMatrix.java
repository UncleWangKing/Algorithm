package topological;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode_329_LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
//        int [][] matrix = {
//                {9,9,4},
//                {6,6,8},
//                {2,1,1}
//        };//4
        int [][] matrix = {
                {7,8,9},
                {9,7,6},
                {7,2,3}
        };//6
        System.out.println(longestIncreasingPath2(matrix));
    }

    /**
     * 直接DFS TLE
     * DP
     */
    public static int longestIncreasingPath(int[][] matrix) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return 0;
        int [][]dir = {{1,0},{-1,0},{0,1},{0,-1}};
        int [][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfsHigher(matrix, dp, dir, i, j));
            }
        }
        return max;
    }

    public static int dfsHigher(int[][] matrix, int [][] dp, int[][] dir, int i, int j){
        if(0 != dp[i][j])
            return dp[i][j];
        int max = 1;
        for (int[] d:dir) {
            int x = i + d[0];
            int y = j + d[1];
            if((x < matrix.length && x >= 0 && y < matrix[0].length && y >= 0) && matrix[x][y] > matrix[i][j])
                max = Math.max(max, dfsHigher(matrix, dp, dir, x, y) + 1);
        }
        dp[i][j] = max;
        return dp[i][j];
    }

    /**
     * BFS DP
     */
    public static int longestIncreasingPath2(int[][] matrix) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return 0;
        int res = 1;
        int [][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        int [][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j ) {
                if (dp[i][j] > 0) continue;
                Queue<Pair> q = new ArrayDeque<>();
                q.add(new Pair(i, j));
                int cnt = 1;
                while (! q.isEmpty()) {
                    ++cnt;
                    int len = q.size();
                    for (int k = 0; k < len; ++k) {
                        Pair t = q.poll();
                        for (int[] dir : dirs) {
                            int x = t.first + dir[0], y = t.second + dir[1];
                            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] <= matrix[t.first][t.second] || cnt <= dp[x][y]) continue;
                            dp[x][y] = cnt;
                            res = Math.max(res, cnt);
                            q.add(new Pair(x, y));
                        }
                    }
                }
            }
        }
        return res;
    }

    private static class Pair{
        public int first;
        public int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
}
