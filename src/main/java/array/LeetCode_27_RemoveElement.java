package array;

import util.ZDaPangArrayUtil;

public class LeetCode_27_RemoveElement {
    public static void main(String[] args) {
        int list[] = {3,2,2,3};
        System.out.println(removeElement(list, 3));
        ZDaPangArrayUtil.printArray(list);
    }

    /**
     * 双指针就是最优思路了 如何写优雅 这是抄的100%写法
     */
    public static int removeElement(int[] nums, int val) {
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
}
