package dp.dp01;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_54_MaximunSubarray {
    public static void main(String[] args) {
        int []list = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray_dp(list));
    }

    public static int maxSubArray_dp(int[] list) {
        int[] dp = new int[list.length];
        int max = list[0];
        dp[0] = list[0];
        for(int i = 1; i < list.length; i++){
            dp[i] = dp[i-1] > 0 ? dp[i-1] + list[i] : list[i];
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static int maxSubArray(int[] list) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            sum += list[i];
            if(sum > max)
                max = sum;
            if(0 > sum)
                sum = 0;
        }

        return max;
    }
}
