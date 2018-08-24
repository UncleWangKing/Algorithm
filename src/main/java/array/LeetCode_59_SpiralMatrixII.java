package array;

import util.ZDaPangArrayUtil;

public class LeetCode_59_SpiralMatrixII {
    public static void main(String[] args) {
        ZDaPangArrayUtil.printArray2(generateMatrix(4));
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int val = 1, p = n;
        for (int i = 0; i < n / 2; ++i, p -= 2) {
            for (int col = i; col < i + p; ++col)
                res[i][col] = val++;
            for (int row = i + 1; row < i + p; ++row)
                res[row][i + p - 1] = val++;
            for (int col = i + p - 2; col >= i; --col)
                res[i + p - 1][col] = val++;
            for (int row = i + p - 2; row > i; --row)
                res[row][i] = val++;
        }
        if (n % 2 != 0) res[n / 2][n / 2] = val;//如果是奇数 不算圈数 中间只有一个值 直接复制
        return res;
    }
}
