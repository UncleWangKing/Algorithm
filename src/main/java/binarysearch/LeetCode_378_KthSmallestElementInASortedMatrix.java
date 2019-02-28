package binarysearch;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/2/28 14:02
 */
public class LeetCode_378_KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                            { 1,  5,  9},
                            {10, 11, 13},
                            {12, 13, 15}
        };
        int k = 8;
        System.out.println(kthSmallest(matrix, k));
    }

    /**
     * 在最小最大值之间取二分值 search_less_equal判断这个值在matrix里的大小
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0], right = matrix[matrix.length - 1][matrix.length - 1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = search_less_equal(matrix, mid);
            if (cnt < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    /**
     * 左下角开始找 上面的一定更小 右边的一定更大
     * 如果matrix[i][j]比target小或者相等，那么比target小或者相等的“至少有”i + 1个(下标+1) --- i是这一整列的都比target小 因为i是这列的最下面一个 上面的都更小
     * 否则i--去上一行
     * 得到的就是target是第几小
     */
    public static int search_less_equal(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0, res = 0;
        while (i >= 0 && j < matrix.length) {
            if (matrix[i][j] <= target) {
                res += i + 1;
                ++j;
            } else {
                --i;
            }
        }
        return res;
    }
}
