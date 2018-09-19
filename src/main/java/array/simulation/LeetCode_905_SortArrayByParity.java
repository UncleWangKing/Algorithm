package array.simulation;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/19 17:52
 */
public class LeetCode_905_SortArrayByParity {
    public static void main(String[] args) {
        int list[] = {0,2};
        ZDaPangArrayUtil.printArray(sortArrayByParity(list));
    }

    public static int[] sortArrayByParity(int[] A) {
        int left = 0, right = A.length - 1;

        while (left < right){
            while (0 == (1 & A[left]) && left < right) left++;
            while (1 == (1 & A[right]) && left < right) right--;
            swap(A, left, right);
        }

        return A;
    }

    public static void swap(int nums[], int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
