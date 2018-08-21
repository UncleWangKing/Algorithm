package array.reverse;

import util.ZDaPangArrayUtil;

public class LeetCode_31_NextPermutation {
    public static void main(String[] args) {
        int list[] = {1,3,2};
        nextPermutation2(list);
        ZDaPangArrayUtil.printArray(list);
    }

    /**
     * 思路一样 代码还是别人的香
     */
    public static void nextPermutation2(int[] nums) {
        int left, right;
        for(right = nums.length - 1; right > 0; right--)
            if(nums[right] > nums[right - 1]) break;

        if(right == 0){
            reverse(nums,0,nums.length - 1);
            return ;
        }
        left = right - 1;
        for (int t = nums.length-1;t > left; t--){
            if(nums[t] > nums[left]){
                swap(nums, left, t);
                break;
            }
        }
        reverse(nums, right, nums.length - 1);
    }

    /**
     * 找到两个数交换 并排序交换位置之后的数
     * 1.将数组从右到左，找第一个由高到低的"拐点"，那个"低"点，是交换数的左坐标swapLeft
     * 2.找到swapLeft右边，比swapLeft大的最小的数swapRight
     * 3.交换 然后对swapLeft + 1开始的后面部分排序 --- 其实再翻转即可，因为后面部分一定是逆序的！
     */
    public static void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            //找拐点
            if (nums[i] > nums[i - 1]) {
                int left = i - 1;
                int right = i;
                for (int j = i; j < nums.length; j++) {
                    if(nums[j] <= nums[i - 1]) {
                        right = j - 1;
                        break;
                    }
                    else if(j == nums.length - 1)
                        right = j;
                }
                swap(nums, left, right);
                //之前写的排序 后来发现 翻转就够了
                reverse(nums, left + 1, nums.length - 1);
                return;
            }
        }

        //没有找到拐点 纯逆序 翻转数组 而不是排序 这样更快
        reverse(nums, 0, nums.length - 1);
    }

    public static void reverse(int[] list, int start, int end){
        while(start < end){
            int temp = list[start];
            list[start++] = list[end];
            list[end--] = temp;
        }
    }

    public static void swap(int[] list, int i, int j){
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
