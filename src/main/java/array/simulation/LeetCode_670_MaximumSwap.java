package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/25 13:21
 */
public class LeetCode_670_MaximumSwap {
    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
    }

    /**
     * 走两遍
     * 第一遍逆向遍历 求出每个值右方最大值"下标"
     * 第二遍正向遍历 如果该位置右方最大值不等于自身 交换
     */

    public static int maximumSwap(int num) {
        String str = String.valueOf(num);
        char[] list = str.toCharArray();
        int[] temp = new int[list.length];
        temp[list.length - 1] = (list.length - 1);
        for (int i = list.length - 2; i >= 0; i--) {
            temp[i] = list[i] > list[temp[i + 1]] ? i : temp[i + 1];
        }
        for (int i = 0; i < list.length; i++) {
            if(list[i] < list[temp[i]]){
                char swap = list[i];
                list[i] = list[temp[i]];
                list[temp[i]] = swap;
                break;
            }
        }

        return Integer.valueOf(String.valueOf(list));
    }
}
