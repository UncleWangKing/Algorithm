package array;

public class LeetCode_581_ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
//        int list[] = {2,6,4,8,10,9,15};//5
//        int list[] = {1,3,2,4,5};//2
//        int list[] = {1,3,5,2,4};//4
        int list[] = {2,1,5,3,4};//5
        System.out.println(findUnsortedSubarray(list));
    }
    //逆序数组在中间 好办 双指针往中间走 然后求差值
    //逆序在两边 且有重复 如1,3,2,2,2
    public static int findUnsortedSubarray(int[] nums) {
        int left = 0, right = nums.length - 1;
        //找左右的第一个出现逆的位置
        while (left + 1 <= nums.length - 1 && nums[left] <= nums[left + 1])
            left++;
        while (right - 1 >= 0 && nums[right] >= nums[right - 1])
            right--;
        if(left >= right)//没有逆
            return 0;
        else{
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            //有逆左右往回找 必须右边大于中间总体的最大值 左边小于中间总体最小值
            while (left - 1 >= 0 && nums[left - 1] > min) left--;
            while (right + 1 <= nums.length - 1 && nums[right + 1] < max) right++;
            return right - left + 1;
        }
    }
}
