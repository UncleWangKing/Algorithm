package array.simulation;

import java.util.Arrays;

public class LeetCode_719_FindK_thSmallestPairDistance {
    public static void main(String[] args) {
        int []list = {1,3,1};
        System.out.println(smallestDistancePair(list, 1));
    }

    /**
     * 直觉思路:直接一看 暴力解法 组合个数是O(n^2) 再加上计算时间 肯定会超时
     * 解决计算时间可以累加数组降到O(n)
     * 本质是求第k小的差
     * 那么先排序
     * 相邻2数只差为n - 1个 如果k小于等于n - 1 那么 k 一定是在某个相邻两数的差中
     * 同理 如果k 大于n-1 小于 (n - 1) + (n - 2) 也就是3数只差
     * 这样可以快速确定符合要求的差所处级别 大幅降低计算成本
     * 然而败给了 三个重复数的情况~ 显然不合理
     */
    public static int smallestDistancePair(int[] nums, int k) {
        //排序
        Arrays.sort(nums);
        //构造累加数组
        int sum[] = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] = nums[i] + sum[i - 1];
        int step = 2;//k为相距多少的数

    }
}
