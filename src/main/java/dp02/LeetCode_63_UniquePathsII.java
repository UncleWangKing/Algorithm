package dp02;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_63_UniquePathsII {
    public static void main(String[] args) {
        int grid[][] = {{1, 0}};
        System.out.println("uniquePathsWithObstacles_dp_loop");
        System.out.println(uniquePathsWithObstacles_dp_loop(grid));
        System.out.println("uniquePathsWithObstacles_dp_loop_lessTime");
        System.out.println(uniquePathsWithObstacles_dp_loop_lessTime(grid));
        System.out.println("uniquePathsWithObstacles_dp_loop_lessMemory");
        System.out.println(uniquePathsWithObstacles_dp_loop_lessMemory(grid));
        System.out.println("uniquePathsWithObstacles_dp_loop_bestMemory");
        System.out.println(uniquePathsWithObstacles_dp_loop_bestMemory(grid));
    }

    /**
     * 状态转换方程:
     * if(1 == obstacleGrid[i][j])
     *  dp[i][j] = 0;
     * else
     *  dp[i][j] = dp[i-1][j] + dp[i][j-1];
     */
    public static int uniquePathsWithObstacles_dp_loop(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int [][]dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(1 == obstacleGrid[i][j])
                    dp[i][j] = 0;
                else if(0 == i && 0 == j)
                    dp[i][j] = 1;
                else if(0 == i && 0 < j)
                    dp[i][j] = dp[i][j-1];
                else if(0 < i && 0 == j)
                    dp[i][j] = dp[i-1][j];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(1 == obstacleGrid[i][j])
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static int uniquePathsWithObstacles_dp_loop_lessTime(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int [][]dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(1 == obstacleGrid[i][j])
                    dp[i][j] = 0;
                else if(0 == i && 0 == j)
                    dp[i][j] = 1;
                else if(0 == i && 0 < j)
                    dp[i][j] = dp[i][j-1];
                else if(0 < i && 0 == j)
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static int uniquePathsWithObstacles_dp_loop_lessMemory(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int []dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (obstacleGrid[i][j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static int uniquePathsWithObstacles_dp_loop_bestMemory(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(m > n) {
            int[] dp = new int[n];
            dp[0] = 1;

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (obstacleGrid[i][j] == 1) dp[j] = 0;
                    else if (j > 0) dp[j] += dp[j - 1];
                }
            }
            return dp[n - 1];
        }else{
            int[] dp = new int[m];
            dp[0] = 1;

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (obstacleGrid[j][i] == 1) dp[j] = 0;
                    else if (j > 0) dp[j] += dp[j - 1];
                }
            }
            return dp[m - 1];
        }
    }
}
