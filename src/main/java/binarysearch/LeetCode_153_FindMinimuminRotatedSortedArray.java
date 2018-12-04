package binarysearch;

public class LeetCode_153_FindMinimuminRotatedSortedArray {
    public static void main(String[] args) {
//        int list[] = {4,5,6,7,0,1,2};
//        int list[] = {2,1};
        int list[] = {3,1,2};
        System.out.println(findMin(list));
    }

    /**
     * 类似 33 题 只是33题是拿数进来这 这是在数组身上找
     */
    public static int findMin(int[] nums) {
        int begin = 0, end = nums.length - 1;
        while(begin < end) {
            int mid = (begin + end) / 2;
            if(nums[begin] < nums[mid]) {//左单调 -- 题目不存在重复 不考虑等于情况
                if(nums[mid] < nums[end])
                    return nums[begin];
                else begin = mid;
            }
            else {//nums[begin] > nums[mid]
                if(nums[mid] > nums[end])
                    return nums[end];
                end = mid;
            }
        }

        return nums[begin];
    }
    /**
     * grandyang 大佬写法
     */
    public static int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > nums[right]) {
            while (left != (right - 1)) {
                int mid = (left + right) / 2;
                if (nums[left] < nums[mid]) left = mid;
                else right = mid;
            }
            return Math.min(nums[left], nums[right]);
        }
        return nums[0];
    }
}
