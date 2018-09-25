package array;

import java.util.Arrays;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/25 11:20
 */
public class LeetCode_611_ValidTriangleNumber {
    public static void main(String[] args) {
        int list[] = {2,2,3,4};
        System.out.println(triangleNumber(list));
    }

    /**
     * 两条短边相加 > 长边 可确定能组成三角形
     * 排序来帮助选择短边  二分查找长边
     */
    //O(lgn*n^2)
    public static int triangleNumber(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int sum = nums[i] + nums[j], left = j + 1, right = n;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (nums[mid] < sum) left = mid + 1;
                    else right = mid;
                }
                res += right - 1 - j;
            }
        }
        return res;
    }

    /**
     *
     */
    //O(n^2)
    public static int triangleNumber2(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = n - 1; i >= 2; --i) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {//right i是确定的 left到right内都可组成三角 就是合理的left个数
                    res += right - left;
                    --right;
                } else {
                    ++left;
                }
            }
        }
        return res;
    }
}
