package array;

import util.ZDaPangArrayUtil;

public class LeetCode_31_NextPermutation {
    public static void main(String[] args) {
        int list[] = {1,3,2};
        nextPermutation(list);
        ZDaPangArrayUtil.printArray(list);
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
                int swapLeft = i - 1;
                int swapRight = i;
                for (int j = i; j < nums.length; j++) {
                    if(nums[j] <= nums[i - 1]) {
                        swapRight = j - 1;
                        break;
                    }
                    else if(j == nums.length - 1)
                        swapRight = j;
                }
                swap(nums, swapLeft, swapRight);
                reverse(nums, swapLeft + 1, nums.length);
                return;
            }
        }

        //没有找到拐点 纯逆序 翻转数组 而不是排序 这样更快
        reverse(nums, 0, nums.length);
    }

    public static void reverse(int[] list, int begin, int end){
        int length = (end - begin) / 2;
        for(int i = begin; i < begin + length ; i++){
            swap(list, i, begin + end - 1 - i);
        }
    }

    public static void swap(int[] list, int i, int j){
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
