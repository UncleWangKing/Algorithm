package binarysearch;

import util.ZDaPangArrayUtil;

public class LeetCode_34_FindFirstAndLastPositionofElementinSortedArray {
    public static void main(String[] args) {
//        int list[] = {5,7,7,8,8,10};int target = 8;//[3,4]
        int list[] = {1};int target = 1;//
        ZDaPangArrayUtil.printArray(searchRange(list, target));
    }

    /**
     * 二分分别找值开始结束位置
     * 锻炼二分的写法
     */
    public static int[] searchRange(int[] nums, int target) {
        if(0 == nums.length) return new int[]{-1 ,-1};
        int first = findFirst(nums, 0, nums.length - 1, target);
        if (-1 != first) {
            int last = findLast(nums, 0, nums.length, target);
            return new int[]{first, last};
        }
        return new int[]{-1 ,-1};
    }

    public static int findFirst(int[] nums, int begin, int end, int target){
        while (begin < end){
            int mid = begin + (end - begin) / 2;
            if(nums[mid] >= target)
                end = mid;
            else
                begin = mid + 1;
        }
        return nums[begin] == target ? begin : -1;
    }

    public static int findLast(int[] nums, int begin, int end, int target){
        while (begin < end){
            int mid = begin + (end - begin) / 2;
            if(nums[mid] <= target)
                begin = mid + 1;
            else
                end = mid;
        }
        return nums[begin - 1] == target ? begin - 1 : -1;
    }
}
