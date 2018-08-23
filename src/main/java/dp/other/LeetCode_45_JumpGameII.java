package dp.other;

public class LeetCode_45_JumpGameII {
    public static void main(String[] args) {
        int list[] = {2,1,1,1,4};
        System.out.println(jump(list));
    }

    /**
     * 状态转换方程 dp[i] = max(nums[k] + k,dp[i-1]) k<-(dp[i-2],dp[i-1])
     * dp[i]代表第i步可达最大范围
     * 压掉一维
     */
    public static int jump(int[] nums) {
        int step = 0;
        int range = 0;
        int preRange = 0;

        while (range < nums.length - 1){
            step++;
            int temp = range;
            for (int i = preRange; i <= temp; i++) {
                range = Math.max(nums[i] + i, range);
                if(range >= nums.length - 1)//剪枝 有效帮助登顶
                    return step;
            }
            preRange = temp;
        }

        return step;
    }
}
