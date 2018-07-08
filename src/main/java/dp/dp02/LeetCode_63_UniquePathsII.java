package dp.dp02;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_63_UniquePathsII {
    public static void main(String[] args) {
        int grid[][] = {{0, 0, 1},
                        {0, 1, 0},
                        {0, 0, 0},
                        {0, 0, 0}};
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
     *  dp[i][j]代表有多少种方法可到达第i行j列
     *
     *  递推初始项
     *  可按照本来应该dp[i][0] = dp[0][j] = 1的假设来从左到右从上到下赋值
     *  只是如果中途遇到障碍(1 == obstacleGrid[i][j])则剩余部分为0
     *  举例
     *  若obstacleGrid为
     * {{0, 1, 0},
     *  {0, 1, 0},
     *  {0, 0, 0},
     *  {0, 0, 0}}
     * 则为
     * {{1, 0, 0},
     *  {1, 0, 0},
     *  {1, 0, 0}，
     *  {1, 0, 0}}
     *  过程是第一行从左到右赋值1时候，在第二个位置遇到了障碍，
     *  于是所有达到右边的点都不通了，所有右侧全为0，第一列的时候同理。
     */
    public static int uniquePathsWithObstacles_dp_loop(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int [][]dp = new int[m][n];
        if(1 == obstacleGrid[0][0])
            return 0;
        dp[0][0] = 1;
        for (int i = 1; i < m; ++i)
            dp[i][0] = 1 == obstacleGrid[i][0] ? 0 : dp[i-1][0];

        for (int i = 1; i < n; ++i)
            dp[0][i] = 1 == obstacleGrid[0][i] ? 0 : dp[0][i-1];

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
