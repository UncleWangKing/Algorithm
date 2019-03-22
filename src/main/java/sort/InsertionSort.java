package sort;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/22 14:40
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] list = {3, 2, 1, 4, 5};
        insertionSortIteration(list);
        ZDaPangArrayUtil.printArray(list);
    }

    public static void insertionSortIteration(int[] list){
        for (int i = 1; i < list.length; i++){
            insertionSortCore(list, 0, i - 1, list[i]);
        }
    }

    public static void insertionSortCore(int[] list, int begin, int end, int value){
        int index = end;

        for (; index >= begin && list[index] > value; index--) {
            list[index + 1] = list[index];
        }

        list[index + 1] = value;
    }

    public static void insertionSort(int[] list){
        int rightBegin, rightEnd;

        for (rightBegin = 1; rightBegin < list.length; rightBegin++){
            int value = list[rightBegin];

            for (rightEnd = rightBegin - 1; rightEnd >= 0 && list[rightEnd] > value; rightEnd--) {
                list[rightEnd + 1] = list[rightEnd];
            }

            list[rightEnd + 1] = value;
        }
    }
}
