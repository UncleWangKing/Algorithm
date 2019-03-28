package sort;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/28 18:17
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] list = {4,1,7,6,9,2,8,0,3,5};
        quickSortIteration(list,0, list.length - 1);
        ZDaPangArrayUtil.printArray(list);
    }

    public static int quickSortCore(int[] list, int begin, int end){
        /**
         * 基准值下标暂存
         */
        int temp = begin;
        while(begin < end) {
            /**
             * 这两个while的顺序依赖于基准值的取法
             * temp取的begin就是现在这样
             * 如果temp取的end两个while要交换顺序
             */
            while(begin < end && list[end] >= list[temp]) {
                --end;
            }
            while(begin < end && list[begin] <= list[temp]) {
                ++begin;
            }
            swap(list, begin, end);
        }
        swap(list, temp, begin);
        return begin;
    }

    public static void quickSortIteration(int[] list, int begin, int end){
        if(begin >= end)
            return;
        int mid = quickSortCore(list, begin, end);
        quickSortIteration(list, begin, mid - 1);
        quickSortIteration(list, mid + 1, end);
    }

    private static void swap(int[] list, int left, int right){
        int temp = list[left];
        list[left] = list[right];
        list[right] = temp;
    }
}
