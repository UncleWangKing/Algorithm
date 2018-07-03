package dp02;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_63_UniquePathsII {
    public static void main(String[] args) {
        int grid[][] = {{0,0,0},
                        {0,1,0},
                        {0,0,0}};
        System.out.println(uniquePathsWithObstacles_lessMemory(grid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (0 == obstacleGrid.length || 0 == obstacleGrid[0].length || obstacleGrid[0][0] == 1) return 0;

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

    public static int uniquePathsWithObstacles_lessMemory(int[][] obstacleGrid) {
        if (0 == obstacleGrid.length || 0 == obstacleGrid[0].length || obstacleGrid[0][0] == 1) return 0;

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
}
