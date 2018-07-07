package dp.dp02;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 13:50
 */
public class LeetCode_741_CherryPickup {
    public static void main(String[] args) {
        int grid[][] = {{0, 1, -1},
                        {1, 0, -1},
                        {1, 1,  1}};

        System.out.println("cherryPickup_dp_loop");
        System.out.println(cherryPickup_dp_loop((grid)));
    }

    /**
     * 分析:第一次可能想到分两步进行，第一轮dp后，将走过的路径置0，再走一次
     * 这是一种”贪心”的做法 然而是错误的 会卡在这个例字上
     * 1 1 1 0 0
     * 0 0 1 0 1
     * 1 0 1 0 0
     * 0 0 1 1 1
     * 明显两轮可以收完，但是上面的做法会漏掉左右两边中间部分的两个的其中一个。
     *
     * 再次分析
     * 1.不难看出正向一次再反向一次 等同于 正向两次。
     * 2.可以当成有两个人同时在正向走。且速度一样，希望两人总体的收益最大。
     *   如果它们同时走到一个格子上，那它们只能拿一次。可以简单理解一下为什么这个问题，和刚才的问题等价：
     *   设速度都是1，则第t个时刻，设第一个人走到(x1, y1)，第二个人走到(x2, y2)，
     *   那么一定有x1 + y1 = t，x2 + y2 = t，
     *   假如x1 != x2，那么这一次行程中，第一个人永远不会走到(x2, y2)，同理第二人永远不会走到(x1, y1)。
     *   因此，拿重的问题只会在它们同时走到一个格子的时候遇到，因此我们判断他们每个时刻是否会到达同一个格子就可以去重了。
     *
     *   把这个思想转换成dp的状态，则可以表示为dp(t, x1, x2)，
     *   也就是第t时刻第一个人走到(x1, t - x1)，
     *   第二个人走到(x2, t - x2)时两人的最大收益。
     * 状态转换方程
     * dp(t, x1, x2) = grid(x1, t - x1) +
     *                 (x1 == x2 ? 0 : grid(x2, t - x2)) +
     *                 Math.max(dp(t-1, x1, x2), dp(t - 1, x1, x2 - 1), dp(t - 1, x1 - 1, x2), dp(t - 1, x1 - 1, x2 - 1))
     *  Math.max中四个量，分别表示两人分别从各自的上方或者左方到达当前位置。
     *  PS:注意x1和x2是分别两人的要么都是横向 要么都是纵向的移动位置 上方Math.max中四个量的组合请仔细观察。
     *  递推初始项
     *  dp[0][0] = grid[0][0]
     *  直接滚动数组压掉t
     */
    public static int cherryPickup_dp_loop(int[][] grid) {
        /**
         * 虽然题目保证 m == n 但我们写更通用一些
         */
        int m = grid.length, n = grid[0].length;
        int maxStep = m + n - 1;
        int dp[][] = new int [m][n];
        dp[0][0] = grid[0][0];
        for (int k = 1; k < maxStep; ++k) {
            for (int i = m - 1; i >= 0; --i) {
                for (int p = n - 1; p >= 0; --p) {
                    int j = k - i, q = k - p;
                    /**
                     * 超出边界 或者 有障碍的点
                     */
                    if (j < 0 || j >= m || q < 0 || q >= n || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1;
                        continue;
                    }
                    /**
                     * 状态转换方程的等效写法
                     * 前三个if负责 “四取一个最大” 加上了一些条件 去掉了一些多余判断
                     * 最后一个if负责 判断是否重复并加上对应grid的值
                     */
                    if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
                    if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
                    if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);
                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
                }
            }
        }
        return Math.max(dp[n - 1][n - 1], 0);
    }
}
