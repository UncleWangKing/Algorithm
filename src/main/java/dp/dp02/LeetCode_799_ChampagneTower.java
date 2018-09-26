package dp.dp02;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/17 10:40
 */
public class LeetCode_799_ChampagneTower {
    public static void main(String[] args) {
        System.out.println(champagneTower(0, 1, 0));
    }

    /**
     * 思路:以杯子上的流经量为切入点  dp
     * 方程:dp[i][j] = (dp[i-1][j] - 1) / 2.0 + (dp[i-1][j - 1] - 1) / 2.0  -- 看成一个棋盘 每个杯子水量 来自上方和左上方杯子
     */
    public static double champagneTower(int poured, int query_row, int query_glass) {
        double dp[][] = new double[query_row + 1][query_glass + 1];
        dp[0][0] = poured;
        for (int i = 1; i <= query_row; i++) {
            for (int j = 0; j <= query_glass; j++) {
                //第一列特殊处理  防止下表越界 -- -1
                if(0 == j)
                    dp[i][j] = (dp[i - 1][j] - 1) / 2.0;
                else
                    dp[i][j] = Math.max(0, (dp[i - 1][j] - 1) / 2.0) + Math.max(0, (dp[i - 1][j - 1] - 1) / 2.0);
            }
        }

        return Math.max(Math.min(dp[query_row][query_glass], 1), 0);
    }

    /**
     * 压缩空间
     * 此刻内循环必须倒着推 因为需要上方和左上方的杯子的水量 正着推 左上方会被覆盖成当前列的值
     */
    public static double champagneTower_betterMemory(int poured, int query_row, int query_glass) {
        double dp[] = new double[query_glass + 1];
        dp[0] = poured;
        for (int i = 1; i <= query_row; i++) {
            for (int j = query_glass; j >= 0 ; j--) {
                //第一列特殊处理  防止下表越界 -- -1
                if(0 == j)
                    dp[j] = (dp[j] - 1) / 2.0;
                else
                    dp[j] = Math.max(0, (dp[j] - 1) / 2.0) + Math.max(0, (dp[j - 1] - 1) / 2.0);
            }
        }

        return Math.max(Math.min(dp[query_glass], 1), 0);
    }
}
