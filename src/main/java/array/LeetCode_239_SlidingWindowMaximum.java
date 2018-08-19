package array;

import util.ZDaPangArrayUtil;

import java.util.Stack;

/**
 * 思路:
 * 用栈保存每个窗口中的数。
 * 入栈: 后进的会顶掉前面更小的， 却不会顶掉前面更大的。
 * 栈底就是最大值。
 * 出栈: 遇到出栈值等于栈底值才出栈
 */
public class LeetCode_239_SlidingWindowMaximum {
    public static void main(String[] args) {
        int list[] = {7, 2, 4};
        ZDaPangArrayUtil.printArray(maxSlidingWindow(list, 2));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0)
            return nums;
        Stack<Integer> vec = new Stack<>();
        int result[] = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++)
            putIn(vec, nums[i]);

        for (int i = k,j = 0; i < nums.length; i++,j++) {
            result[j] = vec.firstElement();
            getOut(vec, nums[j]);
            putIn(vec, nums[i]);
        }
        result[result.length-1] = vec.firstElement();

        return result;
    }

    public static void putIn(Stack<Integer> vec, int value){
        while (! vec.empty()) {
            if(vec.lastElement() < value)
                vec.pop();
            else
                break;
        }
        vec.push(value);
    }

    public static void getOut(Stack<Integer> vec, int value){
        if(vec.firstElement() == value)
            vec.removeElementAt(0);
    }
}