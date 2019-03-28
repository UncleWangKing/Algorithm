package binarysearch.classic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/28 14:01
 */

import java.util.Random;

/**
 * 常规思路 初始化后求和然后算出比例 然后random随机
 * 二分思路 从小到大排序，然后生成w[i]范围内随机数，二分查找出第一个大于等于这个数的数
 * 由于要求返回的是下标 所以还不能排序 否则又得存下标和值得对应
 * 所以用累加数组
 */
public class LeetCode_528_RandomPickWithWeight {
    public static void main(String[] args) {
        int []w = {1,2,3};
        Solution(w);
        System.out.println(pickIndex());
        System.out.println(pickIndex());
        System.out.println(pickIndex());
        System.out.println(pickIndex());
    }
    private static int[] sum;
    private static Random rand;
    public static void Solution(int[] w) {
        rand = new Random();
        sum = w;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
    }

    public static int pickIndex() {
        int target = rand.nextInt(sum[sum.length - 1]);
        int begin = 0, end = sum.length - 1;
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (sum[mid] <= target)
                begin = mid + 1;
            else
                end = mid;
        }
        return begin;
    }
}
