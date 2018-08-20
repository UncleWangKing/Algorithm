package array;

import util.ZDaPangArrayUtil;

public class LeetCode_27_RemoveElement {
    public static void main(String[] args) {
        int list[] = {3,2,2,3};
        System.out.println(removeElement(list, 3));
        ZDaPangArrayUtil.printArray(list);
    }

    public static int removeElement(int[] nums, int val) {
        if(1 > nums.length)
            return 0;
        if(1 == nums.length)
            return val == nums[0] ? 0 : 1;
        int left = 0;
        int right = nums.length - 1;
        int result = 0;
        while(left < right){
            if(nums[left] == val){
                while(left < right){
                    if(nums[right] != val){
                        result++;
                        nums[left] = nums[right--];
                        break;
                    }else
                    right--;
                }
            }else
                result++;
            left++;
            if(left == right){
                if(nums[left] != val)
                    result++;
            }
        }

        return result;
    }
}
