package binarysearch;

public class LeetCode_704_BinarySearch {
    public static void main(String[] args) {
        int [] nums = {-1,0,3,5,9,12}; int target = 9;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0, rifht = nums.length - 1;
        while (left != rifht){
            int mid = left + (rifht - left) / 2;
            if(target > nums[mid])
                left = mid + 1;
            else if(target < nums[mid])
                rifht = mid;
            else
                return mid;
        }
        return nums[left] == target ? left : -1;
    }
}
