package dp02;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_64_MinimumPathSum {
    public static void main(String[] args) {
//        [4,6,4,7,7,4,8,2,1],[1,9,6,9,8,2,9,7,2],[5,5,7,5,8,7,9,1,4],[0,7,9,9,1,5,3,9,4]
        int grid[][] = {{7,4,3},
                        {1,8,3},
                        {1,8,3}};
        System.out.println("minPathSum_dp_loop");
        System.out.println(minPathSum_dp_loop(grid));
        System.out.println("minPathSum_dp_loop_lessMemory");
        System.out.println(minPathSum_dp_loop_lessMemory(grid));
        System.out.println("minPathSum_dp_loop_bestMemory");
        System.out.println(minPathSum_dp_loop_bestMemory(grid));
        System.out.println("minPathSum_dp_otherWay_loop");
        System.out.println(minPathSum_dp_otherWay_loop(grid));
        System.out.println("minPathSum_dp_otherWay_loop_lessMemory");
        System.out.println(minPathSum_dp_otherWay_loop_lessMemory(grid));
        System.out.println("minPathSum_dp_otherWay_loop_bestMemory");
        System.out.println(minPathSum_dp_otherWay_loop_bestMemory(grid));
    }
    /**
     * 状态转换方程:
     * dp[i][j] = min(dp[i-1][j] + grid[i-1][j], dp[i][j-1] + grid[i][j-1]) (dp[i][j]代表“到达”第i行j列所需最小花费)
     */
    public static int minPathSum_dp_loop(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int dp[][] = new int[m][n];

        for (int i = 1; i < n; ++i) dp[0][i] = dp[0][i-1] + grid[0][i-1];
        for (int i = 1; i < m; ++i) dp[i][0] = dp[i-1][0] + grid[i-1][0];

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i-1][j] + grid[i-1][j], dp[i][j-1] + grid[i][j-1]);
            }
        }
        return dp[m-1][n-1] + grid[m-1][n-1];
    }

    /**
     * 状态转换方程:
     * dp[i][j] = min(dp[i-1][j] + grid[i-1][j], dp[i][j-1] + grid[i][j-1]) (dp[i][j]代表“到达”第i行j列所需最小花费)
     * 优化内存
     */
    public static int minPathSum_dp_loop_lessMemory(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int dp[] = new int[n];

        for (int i = 1; i < n; ++i) dp[i] = dp[i-1] + grid[0][i-1];
        for (int i = 1; i < m; ++i) {
            /**
             * 单独解决dp[0]累加更新问题
             */
            dp[0] += grid[i-1][0];
            for (int j = 1; j < n; ++j) {
                dp[j] = Math.min(dp[j] + grid[i-1][j], dp[j-1] + grid[i][j-1]);
            }
        }
        return dp[n-1] + grid[m-1][n-1];
    }

    /**
     * 状态转换方程:
     * dp[i][j] = min(dp[i-1][j] + grid[i-1][j], dp[i][j-1] + grid[i][j-1]) (dp[i][j]代表“到达”第i行j列所需最小花费)
     * 根据行列大小来选择遍历顺序 可更加节约内存
     *
     * 若m > n 横向遍历
     * 若m < n 纵向遍历
     */
    public static int minPathSum_dp_loop_bestMemory(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(m > n) {
            int dp[] = new int[n];

            for (int i = 1; i < n; ++i) dp[i] = dp[i-1] + grid[0][i-1];
            for (int i = 1; i < m; ++i) {
                /**
                 * 单独解决dp[0]累加更新问题
                 */
                dp[0] += grid[i - 1][0];
                for (int j = 1; j < n; ++j) {
                    dp[j] = Math.min(dp[j] + grid[i-1][j], dp[j-1] + grid[i][j-1]);
                }
            }
            return dp[n-1] + grid[m-1][n-1];
        }else{
            int dp[] = new int[m];

            for (int i = 1; i < m; ++i) dp[i] = dp[i-1] + grid[i-1][0];
            for (int i = 1; i < n; ++i) {
                /**
                 * 单独解决dp[0]累加更新问题
                 */
                dp[0] += grid[0][i-1];
                for (int j = 1; j < m; ++j) {
                    dp[j] = Math.min(dp[j] + grid[j][i-1], dp[j-1] + grid[j-1][i]);
                }
            }
            return dp[m-1] + grid[m-1][n-1];
        }
    }

    /**
     * 状态转换方程:
     * dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1]) (dp[i][j]代表“经过”第i行j列所需最小花费)
     */
    public static int minPathSum_dp_otherWay_loop(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int dp[][] = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; ++i) dp[i][0] = grid[i][0] + dp[i-1][0];
        for (int i = 1; i < n; ++i) dp[0][i] = grid[0][i] + dp[0][i-1];

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 状态转换方程:
     * dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1]) (dp[i][j]代表“经过”第i行j列所需最小花费)
     * 优化内存
     */
    public static int minPathSum_dp_otherWay_loop_lessMemory(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int dp[] = new int[n];

        dp[0] = grid[0][0];
        for (int i = 1; i < n; ++i) dp[i] = grid[0][i] + dp[i-1];

        for (int i = 1; i < m; ++i) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; ++j) {
                dp[j] = grid[i][j] + Math.min(dp[j-1], dp[j]);
            }
        }
        return dp[n-1];
    }

    /**
     * 状态转换方程:
     * dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1]) (dp[i][j]代表“经过”第i行j列所需最小花费)
     * 根据行列大小来选择遍历顺序 可更加节约内存
     *
     * 若m > n 横向遍历
     * 若m < n 纵向遍历
     */
    public static int minPathSum_dp_otherWay_loop_bestMemory(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(m > n) {
            int dp[] = new int[n];

            dp[0] = grid[0][0];
            for (int i = 1; i < n; ++i) dp[i] = grid[0][i] + dp[i - 1];

            for (int i = 1; i < m; ++i) {
                dp[0] += grid[i][0];
                for (int j = 1; j < n; ++j) {
                    dp[j] = grid[i][j] + Math.min(dp[j - 1], dp[j]);
                }
            }
            return dp[n - 1];
        }else{
            int dp[] = new int[m];

            dp[0] = grid[0][0];
            for (int i = 1; i < m; ++i) dp[i] = grid[i][0] + dp[i - 1];

            for (int i = 1; i < n; ++i) {
                dp[0] += grid[0][i];
                for (int j = 1; j < m; ++j) {
                    dp[j] = grid[j][i] + Math.min(dp[j - 1], dp[j]);
                }
            }
            return dp[m - 1];
        }
    }
}
