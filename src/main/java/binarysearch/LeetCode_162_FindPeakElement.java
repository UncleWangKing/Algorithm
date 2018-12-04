package binarysearch;

public class LeetCode_162_FindPeakElement {
    public static void main(String[] args) {
        int list[] = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement(list));
    }

    /**
     * 迷迷糊糊
     */
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    /**
     * 并非二分 但是思路值得一写 遇到下降就说明是峰值
     */
    public static int findPeakElement2(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) return i - 1;
        }
        return nums.length - 1;
    }
}
