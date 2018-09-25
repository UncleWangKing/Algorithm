package array.simulation;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/25 13:41
 */
public class LeetCode_667_BeautifulArrangementII {
    public static void main(String[] args) {
        ZDaPangArrayUtil.printArray(constructArray(5,3));
    }

    /**
     * 1, 2, 3, 4, 5, 6, 7, 8
     当我们这样有序排列的话，相邻两数的差的绝对值为1。我们想差的绝对值最大能为多少，
     应该是把1和8放到一起，为7。那么为了尽可能的产生不同的差的绝对值，我们在8后面需要放一个小数字，
     比如2，这样会产生差的绝对值6，同理，后面再跟一个大数，比如7，产生差的绝对值5，以此类推，我们得到下列数组：
     1, 8, 2, 7, 3, 6, 4, 5
     其差的绝对值为：7，6，5，4，3，2，1
     共有7种，所以我们知道k最大为n-1，所以这样的排列一定会存在。
     我们的策略是，先按照这种最小最大数相邻的方法排列，没排一个，k自减1，
     当k减到1的时候，后面的排列方法只要按照生序的方法排列，就不会产生不同的差的绝对值，
     这种算法的时间复杂度是O(n)，属于比较高效的那种。
     我们使用两个指针，初始时分别指向1和n，然后分别从i和j取数加入结果res，每取一个数字k自减1，直到k减到1的时候，开始按升序取后面的数字
     */
    public static int[] constructArray(int n, int k) {
        int[] result = new int[n];
        int left = 1;
        int right = n;
        int index = 0;
        for (left = 1, right = n; left <= right; ) {
            if (k > 1) {
                result[index++] = (k % 2 == 1 ? left++ : right--);
                k--;
            } else {
                result[index++] = left++;
            }
        }
        return result;
    }
}
