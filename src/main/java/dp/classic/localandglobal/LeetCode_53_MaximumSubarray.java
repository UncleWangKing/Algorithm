package dp.classic.localandglobal;

public class LeetCode_53_MaximumSubarray {
    public static void main(String[] args) {
        int[] list = {1,2,3,1};
        System.out.println(maxSubArray(list));
    }

    public static int maxSubArray(int[] list) {
        int[] dp = new int[list.length];
        int max = list[0];
        dp[0] = list[0];
        for(int i = 1; i < list.length; i++){
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + list[i] : list[i];
            max = Math.max(dp[i],max);
        }
        return max;
    }
    //maxSubArray1 简化写法 滚动数组
    public static int maxSubArray2(int[] list) {
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
    //local and global
    public static int maxSubArray3(int[] nums){
        int n = nums.length;
        int[] local = new int[2];
        local[0] = nums[0];
        int global = nums[0];
        for(int i = 1; i < n; i++){
            local[i % 2] = nums[i] + (local[(i - 1) % 2] > 0 ? local[(i - 1) % 2] : 0);
            global = Math.max(global, local[i % 2]);
        }
        return global;
    }
    //分治
    public static int maxSubArray4(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    public static int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);
        int rmax = helper(nums, mid + 1, right);
        int mmax = nums[mid], t = mmax;
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        return Math.max(mmax, Math.max(lmax, rmax));
    }
}
