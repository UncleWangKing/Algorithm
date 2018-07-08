package dp.dp02;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_174_DungeonGame {
    public static void main(String[] args) {
        int grid[][] = {{-2,-3,3},
                        {-5,-10,1},
                        {10,30,-5}};
        System.out.println("calculateMinimumHP_dp_loop");
        System.out.println(calculateMinimumHP_dp_loop(grid));
        System.out.println("calculateMinimumHP_dp_loop_lessMemory");
        System.out.println(calculateMinimumHP_dp_loop_lessMemory(grid));
        System.out.println("calculateMinimumHP_dp_loop_bestMemory");
        System.out.println(calculateMinimumHP_dp_loop_bestMemory(grid));
    }

    /**
     * 状态转换方程
     * dp[i][j] = max(1, min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]) (dp[i][j]代表到达该位置且不会死亡(hp>=1)所需最低hp)
     * 自顶向下(top-down)
     */
    //典型的自顶向下
    public static int calculateMinimumHP_dp_loop(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int [][]dp = new int[m+1][n+1];
        //初始化为Integer.MAX_VALUE
        Arrays.fill(dp[m], Integer.MAX_VALUE);
        /**
         * 上一句的存在 i < m 而不是 i <= m
         */
        for (int i = 0; i < m; i++) {
            dp[i][n] = Integer.MAX_VALUE;
        }

        /**
         * 给第一个递推项dp[m-1][n-1]使用
         */
        dp[m][n-1] = 1; dp[m-1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }

    public static int calculateMinimumHP_dp_loop_lessMemory(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int []dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        /**
         * 是n-1而不是n 为了让每轮的最“右”的“有效数据”可以排除“来自右方的可能”
         */
        dp[n-1] = 1;

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[j] = Math.max(1, Math.min(dp[j], dp[j+1]) - dungeon[i][j]);
            }
        }

        return dp[0];
    }

    public static int calculateMinimumHP_dp_loop_bestMemory(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        if(m > n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            /**
             * 是n-1而不是n 为了让每轮的最“右”的“有效数据”可以排除“来自右方的可能”
             */
            dp[n - 1] = 1;

            for (int i = m - 1; i >= 0; --i) {
                for (int j = n - 1; j >= 0; --j) {
                    dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[i][j]);
                }
            }

            return dp[0];
        }else {
            int[] dp = new int[m + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            /**
             * 是m-1而不是m 为了让每轮的最“下”的“有效数据”可以排除“来自下方的可能”
             */
            dp[m - 1] = 1;

            for (int i = n - 1; i >= 0; --i) {
                for (int j = m - 1; j >= 0; --j) {
                    dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[j][i]);
                }
            }

            return dp[0];
        }
    }
}
