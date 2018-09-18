package array.simulation;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/18 17:54
 */
public class LeetCode_628_MaximumProductofThreeNumbers {
    public static void main(String[] args) {
        int list[] = {1,2,3,4};
        System.out.println(maximumProduct(list));
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        if(nums[nums.length - 1] <= 0 || nums[0] > 0)//全是非正数 就要尝试绝对值最小方案 或者 全是正数 就是绝对值最大方案
            return nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        else //有正有负
            return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3],nums[nums.length - 1] * nums[0] * nums[1]);
    }
    //三个最大数 两个最小数
    public static int maximumProduct2(int[] nums) {
        int max0 = Integer.MIN_VALUE, max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min0 = Integer.MAX_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int val : nums) {
            if (val > max2) {
                if (val > max0) {
                    max2 = max1;
                    max1 = max0;
                    max0 = val;
                } else if (val > max1) {
                    max2 = max1;
                    max1 = val;
                } else {
                    max2 = val;
                }
            }
            if (val < min2) {
                if (val < min0) {
                    min2 = min1;
                    min1 = min0;
                    min0 = val;
                } else if (val < min1) {
                    min2 = min1;
                    min1 = val;
                } else {
                    min2 = val;
                }
            }
        }
        return Integer.max(max0 * max1 * max2, min0 * min1 * max0);
    }
}
