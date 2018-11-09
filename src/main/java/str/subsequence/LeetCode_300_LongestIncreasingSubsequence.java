package str.subsequence;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/3 10:11
 */
public class LeetCode_300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
//        int []list = {3,1,5,8};//3
        int []list = {4,10,4,3,8,9};//3
        System.out.println(lengthOfLIS2(list));
    }

    /**
     * 有了走楼梯的基础来看，这题可以dp。
     * 方程：另dp[i] 为以i为结尾的LIS长度。则任意的j[0,i)
     * if(nums[i] > nums[j])
     * dp[i] = max(dp[i], dp[j] + 1);
     * else
     * dp[i] = 1;
     * 意思是，每新增一个位置为i的数进来，依次和之前的dp[j]比大小，更大，那么等于dp[j] + 1,
     * 由于可能多个最大，所以取最大的存起来。
     * 需要注意的是，因为dp[i]的定义是必须以i结尾，而i结尾的子序列未必是全局的LIS，
     * 所以要有个变量存放出现过得最大值。
     * 是的，这是走楼梯系列不曾出现的，最终结果并不在dp的最后，而在过程之中。
     * PS:为何这样定义，因为dp找方程的本质就是状态划分，
     * 而以i为结尾的LIS这样的划分，是一个完备的划分，
     * 它包含了所有可能的LIS。
     */

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

    /**
     * java 数组扩容不便 与使用了index 本质是二分查找第一个不小于比新来的数 替换
     */
    public static int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        int dp[] = new int[len];
        int index = 0;
        for(int i = 0;i < len;i++){
            if(i == 0){
                dp[index++] = nums[i];
            }else if(nums[i] > dp[index - 1]){
                dp[index++] = nums[i];
            }else{
                int left = 0, right = index - 1;
                while (left < right){
                    int mid = left + (right - left) / 2;
                    if(dp[mid] <= nums[i])
                        left = mid + 1;
                    else
                        right = mid;
                }
                dp[left] = nums[i];
            }
        }
        return index;
    }

    public static int lengthOfLIS3(int[] nums){
        int len = nums.length;
        int dp[] = new int[len];
        int index = 0;
        for(int i = 0;i < len;i++){
            if(i == 0){
                dp[index++] = nums[i];
            }else if(nums[i] > dp[index - 1]){
                dp[index++] = nums[i];
            }else{
                for(int j = 0;j < index;j++){
                    if(dp[j] >= nums[i]){
                        dp[j] = nums[i];
                        break;
                    }
                }
            }
        }
        return index;
    }
}
