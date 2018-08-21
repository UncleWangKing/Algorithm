package array.reverse;

import util.ZDaPangArrayUtil;

public class LeetCode_189_RotateArray {
    public static void main(String[] args) {
        int list[] = {1,2,3,4,5,6,7};
        rotate(list, 3);
        ZDaPangArrayUtil.printArray(list);
    }
    //两次翻转 第一次全部 第二次前step个
    public static void rotate(int[] list, int step) {
        step %= list.length;
        reverse(list, 0, list.length - 1);
        reverse(list, 0, step - 1);
        reverse(list, step , list.length - 1);
    }
    //翻转数组[left,right]部分
    public static void reverse(int[] list, int start, int end){
        while(start < end){
            int temp = list[start];
            list[start++] = list[end];
            list[end--] = temp;
        }
    }
}
