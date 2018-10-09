package array.sub;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/9 16:10
 */

/**
 * 启发:子数组个数等于长度的递增序列和 如果长度是5 则为 1+2+3+4+5 = 5*(5+1)/2
 * 迭代时候可以直接累加长度即可 不必最后来求n*(n+1)/2
 */
public class LeetCode_795_NumberofSubarrayswithBoundedMaximum {
    public static void main(String[] args) {
//        int list[] = {2, 1, 4, 3};int left = 2, right = 3;//[2,3]--3
//        int list[] = {2,9,2,5,6};int left = 2, right = 8;//[2,8]--7
        int list[] = {73,55,36,5,55,14,9,7,72,52};int left = 32, right = 69;//[32,69]--22
        System.out.println(numSubarrayBoundedMax3(list, left, right));
    }

    /**
     * 暴力剪枝
     */
    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (A[i] > R) continue;
            int curMax = Integer.MIN_VALUE;
            for (int j = i; j < n; ++j) {
                curMax = Math.max(curMax, A[j]);
                if (curMax > R) break;
                if (curMax >= L) ++res;
            }
        }
        return res;
    }

    /**
     * 最大值在[-∞, R]范围内的子数组的个数，减去最大值在[-∞, L-1]范围内的子数组的个数
     * 最清晰舒服的思路
     */
    public static int numSubarrayBoundedMax2(int[] A, int L, int R) {
        return count(A, R) - count(A, L - 1);
    }

    public static int count(int[] A, int bound) {
        int res = 0, cur = 0;
        for (int x : A) {
            cur = (x <= bound) ? cur + 1 : 0;
            res += cur;
        }
        return res;
    }

    /**
     * 碰到[L,R]内的数 正常迭代
     * 碰到小于L的数 加上之前迭代的数量
     * 碰到大于R的数 迭代量归零
     * MMP 自己想的时候本来已经很接近这个思路了 但是没撸出来！！！！！
     */
    public static int numSubarrayBoundedMax3(int[] A, int L, int R) {
        int res = 0, left = -1, right = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] > R) left = i;
            if (A[i] >= L) right = i;
            res += right - left;
        }
        return res;
    }

    /**
     * 解法三剪枝  --- 没啥意义 少几个小判断
     */
    public static int numSubarrayBoundedMax4(int[] A, int L, int R) {
        int res = 0, left = -1, right = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] > R) {
                left = right = i;
                continue;
            }
            if (A[i] >= L) right = i;
            res += right - left;
        }
        return res;
    }
}
