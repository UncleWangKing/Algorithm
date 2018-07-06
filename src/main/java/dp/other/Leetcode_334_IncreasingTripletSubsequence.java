package dp.other;

public class Leetcode_334_IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int list[] = {3,5,2,6};
        System.out.println(increasingTriplet_final(list));
    }

    //dp解法 用O(n)空间 将时间从O(n3)->O(n2)
    public static boolean increasingTriplet_dp(int[] nums) {

        int dp[] = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] >= 3) return true;
                }
            }
        }
        return false;
    }
    //此处 start和middle并非是最小和次小的两个数
    //例如 {3,5,2,6} i=3也就是nums[3]=6的时候 start=2 middle=5
    //由于第三步只和middle比较 所以能保持正确
    //可以理解成 start只是更新middle的一个“缓冲”
    public static boolean increasingTriplet_final(int[] nums) {
        int start = Integer.MAX_VALUE;
        int middle = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] < start)
            {
                start = nums[i];
            }
            else if(nums[i] > start && nums[i] < middle)
            {
                middle = nums[i];
            }
            else if(nums[i] > middle)
            {
                return true;
            }
        }
        return false;
    }
}
