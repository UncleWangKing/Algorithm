package array.numbersum;

import util.ZDaPangArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/10 18:10
 */
public class LeetCode_167_TwoSumII_InputArrayisSorted {
    public static void main(String[] args) {
        int list[] = {2,7,11,15};int n = 9;
        ZDaPangArrayUtil.printArray(twoSum2(list, n));
    }

    /**
     * 网上最快解
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        int left = 0;int right = numbers.length - 1;int temp = 0;
        while (left < right) {
            temp = numbers[left] + numbers[right];
            if (temp > target) {
                right--;
            } else if (temp < target) {
                left++;
            } else {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
        }
        return result;
    }
}
