package sort;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/22 14:25
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] list = {3, 2, 1, 4, 5};
        selectionSort(list);
        ZDaPangArrayUtil.printArray(list);
    }

    public static void selectionSortIteration(int[] list){
        for (int i = list.length - 1; i >= 0 ; i--) {
            selectionSortCore(list, 0, i);
        }
    }

    public static void selectionSortCore(int[] list, int begin, int end){
        int maxIndex = begin;
        for (int i = begin; i <= end; i++) {
            if(list[maxIndex] < list[i]){
                maxIndex = i;
            }
        }
        int temp = list[maxIndex];
        list[maxIndex] = list[end];
        list[end] = temp;
    }

    public static void selectionSort(int[] list){
        for (int i = list.length - 1; i >= 0 ; i--) {
            int maxIndex = 0;
            for (int j = 0; j <= i; j++) {
                if(list[maxIndex] < list[j]){
                    maxIndex = j;
                }
            }
            int temp = list[maxIndex];
            list[maxIndex] = list[i];
            list[i] = temp;
        }
    }
}
