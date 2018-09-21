package array.binarysearch;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/21 14:35
 */
public class LeetCode_189_RotateArray {
    public static void main(String[] args) {
        int list[] = {-1,-100,3,99};
        rotate(list, 2);
        ZDaPangArrayUtil.printArray(list);
    }

    public static void rotate(int[] list, int step) {
        step %= list.length;
        rollOver(list, 0, list.length - 1);
        rollOver(list, 0, step - 1);
        rollOver(list, step , list.length - 1);
    }
    //翻转数组[left,right]部分
    public static void rollOver(int[] list, int start, int end){
        while(start < end){
            int temp = list[start];
            list[start++] = list[end];
            list[end--] = temp;
        }
    }
}
