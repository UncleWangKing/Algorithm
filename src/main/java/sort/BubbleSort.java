package sort;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/22 14:04
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] list = {3, 2, 1, 4, 5};
        bubbleSortIteration(list);
        ZDaPangArrayUtil.printArray(list);
    }

    public static void bubbleSortIteration(int[] list){
        for (int i = list.length; i >= 0 ; i--) {
            bubbleSortCore(list, 0, i);
        }
    }

    public static void bubbleSortCore(int[] list, int begin, int end){
        for (int i = begin; i < end - 1; i++) {
            if(list[i] > list[i + 1]){
                int temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
            }
        }
    }

    public static void bubbleSort(int[] list){
        for (int i = list.length; i >= 0 ; i--) {
            for (int j = 0; j < i - 1; j++) {
                if(list[j] > list[j + 1]){
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }
}
