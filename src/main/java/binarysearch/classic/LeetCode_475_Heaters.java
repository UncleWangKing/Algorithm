package binarysearch.classic;

import java.util.Arrays;

public class LeetCode_475_Heaters {
    public static void main(String[] args) {
//        int houses[] = {1,5};int heaters[] = {3};//2
//        int houses[] = {1,2,3,4};int heaters[] = {1,4};//1
        int houses[] = {1,5};int heaters[] = {10};//1
        System.out.println(findRadius(houses, heaters));
    }

    /**
     * 每个house去找离自己距离最近距离的heater
     * 最小距离等于所有上述距离中最大值
     * 如何找离子自己距离最近的heater
     * 找第一个大于自身的heater的位置index 然后和 index - 1位置比较
     */
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0;
        for (int i:houses) {
            int left = 0, right = heaters.length - 1;
            /**
             * 找第一个大于自身的heater位置
             */
            while (left < right){
                int mid = left + (right - left) / 2;
                if(i >= heaters[mid])
                    left = mid + 1;
                else
                    right = mid;
            }
            if(left > 0)
                max = Math.max(max, Math.min(Math.abs(heaters[left] - i), Math.abs(i - heaters[left - 1])));
            else
                max = Math.max(max, Math.abs(i - heaters[left]));
        }

        return max;
    }

    /**
     * 没有二分居然更快？？？？
     */
    public static int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0;
        int result = 0;
        for(int house : houses){
            while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2)
                i++;
            result = Math.max(result,Math.abs(heaters[i] - house));
        }
        return result;
    }
}
