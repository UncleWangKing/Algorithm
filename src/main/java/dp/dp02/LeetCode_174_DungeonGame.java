package dp.dp02;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_174_DungeonGame {
    public static void main(String[] args) {
        int grid[][] = {{-2,-3,3},
                        {-5,-10,1},
                        {10,30,-5}};
        System.out.println(calculateMinimumHP_lessMemory(grid));
    }

    /**
     * 状态转换方程
     * dp[i][j] = max(1, min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j])
     *
     */
    //典型的自顶向下
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int [][]dp = new int[m+1][n+1];
        //初始化为Integer.MAX_VALUE
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[m][n - 1] = 1; dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
    public static int calculateMinimumHP_lessMemory(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int []dp = new int[n+1];
        //初始化为Integer.MAX_VALUE
        for (int i = 0; i <= n; i++) {
                dp[i] = Integer.MAX_VALUE;
        }
        dp[n - 1] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0];
    }
}
