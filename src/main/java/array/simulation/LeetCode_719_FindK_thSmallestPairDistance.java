package array.simulation;

import java.util.Arrays;

public class LeetCode_719_FindK_thSmallestPairDistance {
    public static void main(String[] args) {
        int []list = {1,3,3};
        System.out.println(smallestDistancePair2(list, 1));
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
     * 网上思路:桶排序优化暴力
     */
    public static int smallestDistancePair(int[] nums, int k) {
        int n = nums.length, N = 1000000;
        int [] cnt = new int[N];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                ++cnt[Math.abs(nums[i] - nums[j])];
            }
        }
        for (int i = 0; i < N; ++i) {
            if (cnt[i] >= k) return i;
            k -= cnt[i];
        }
        return -1;
    }

    /**
     * 二分:二分条件不再是简单的大小 而是一个"函数"
     * 核心思想是二分确定一个中间数，然后找到所有小于等于这个中间数的距离个数，用其跟k比较来确定折半的方向。
     * 一句话:想明白怎么在排序好的带重复数的数组里 求差值小于k的所有数对的 个数 以及左右如何选择
     * 这是该题二分的重要依据
     */
    public static int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = left + (right - left) / 2, cnt = 0, start = 0;
            for (int i = 0; i < n; ++i) {
                while (start < n && nums[i] - nums[start] > mid) ++start;
                cnt += i - start;
            }
            if (cnt < k) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}
