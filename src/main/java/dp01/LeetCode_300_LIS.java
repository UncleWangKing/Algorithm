package dp01;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_300_LIS {
    public static void main(String[] args) {
        int []list = {3,1,5,8};
        System.out.println(lengthOfLIS(list));
    }

    public static int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
