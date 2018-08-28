package array;

import util.ZDaPangArrayUtil;

public class LeetCode_26_RemoveDuplicatesfromSortedArray {
    public static void main(String[] args) {
        int []list = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(list));
        ZDaPangArrayUtil.printArray(list);
    }
    public static int removeDuplicates(int[] list) {
        int left = 0;
        int right = 1;

        while (right < list.length)
            if (list[right++] != list[left])
                list[++left] = list[right-1];

        return left + 1;
    }
}
