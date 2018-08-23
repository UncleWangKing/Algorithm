package dp.other;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 11:01
 */
public class LeetCode_55_JumpGame {
    public static void main(String[] args) {
        int list[] = {2,3,1,1,4};
        System.out.println(canJump_greedy(list));
    }

    /**
     * 状态转换方程 dp[i] = max(dp[i-1], nums[i-1]) - 1
     * dp[i]代表到达i点后还能往后触及多少范围
     */
    public static boolean canJump_dp(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i-1]) - 1;
            if (dp[i] < 0) return false;//跳不到后面了 剪枝
        }
        return dp[dp.length-1] >= 0;
    }
    /**
     * 状态转换方程 dp[i] = max(dp[i-1], nums[i-1]) - 1
     * dp[i]仅和dp[i-1]相关 么有重叠子问题 就是贪心
     */
    public static boolean canJump_greedy(int[] nums) {
        int n = nums.length, reach = 0;
        for (int i = 0; i < n; ++i) {
            if (i > reach || reach >= n - 1) break;
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= n - 1;
    }
}
