package dp.dp01;


/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_303_RangeSumQuery {
    public static void main(String[] args) {
        int []list = {-2, 0, 3, -5, 2, -1};
        LeetCode_303_RangeSumQuery leetCode_303_rangeSumQuery = new LeetCode_303_RangeSumQuery(list);
        System.out.println(leetCode_303_rangeSumQuery.sumRange(0,2));
        System.out.println(leetCode_303_rangeSumQuery.sumRange(2,5));
        System.out.println(leetCode_303_rangeSumQuery.sumRange(0,5));
    }
    private int [] dp;

    public LeetCode_303_RangeSumQuery(int[] nums) {
        dp = nums;
        for (int i = 1; i < dp.length; i++) {
            dp[i] += dp[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return 0 == i ? dp[j] : dp[j] - dp[i-1];
    }
}
