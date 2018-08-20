package array;

import util.ZDaPangArrayUtil;

public class LeetCode_27_RemoveElement {
    public static void main(String[] args) {
        int list[] = {3,2,2,3};
        System.out.println(removeElement(list, 3));
        ZDaPangArrayUtil.printArray(list);
    }

    /**
     * 双指针 你看看人家！！！
     */
    public static int removeElement2(int[] nums, int val) {
        if(1 > nums.length)
            return 0;
        int result = 0, left = 0, right = nums.length - 1;

        while(left <= right){
            if(val != nums[left]){
                left++;
                result++;
            }else{
                nums[left] = nums[right];
                right--;
            }
        }
        return result;
    }

    /**
     * 双指针 丑陋的自己的写法
     */
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
