package str.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * java 数组扩容不便 与使用了index 本质是二分查找第一个不小于比新来的数 替换
     */
    public static int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

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
