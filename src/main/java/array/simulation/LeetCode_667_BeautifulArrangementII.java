package array.simulation;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/25 13:41
 */
public class LeetCode_667_BeautifulArrangementII {
    public static void main(String[] args) {
        ZDaPangArrayUtil.printArray(constructArray(5,3));
    }

    /**
     * 直接和倒数第k-1个数交换即可 2,1,3,4,5,6,7,8,9
     */
    public static int[] constructArray(int n, int k) {
        int []res = new int[n];

        for (int i = 0; i < n; i++)
            res[i] = i + 1;

        swap(res, n - k, n - 1);

        return res;
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
