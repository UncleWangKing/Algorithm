package array.index;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_54_SpiralMatrix {

    public static void main(String[] args) {
        int list[][] = {{1,2,3}
                        ,{4,5,6}
                        ,{7,8,9}
            };

        System.out.println(spiralOrder(list));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (0 == matrix.length) return res;
        int m = matrix.length, n = matrix[0].length;
        int c = m > n ? (n + 1) / 2 : (m + 1) / 2;//圈数
        int p = m, q = n;//圈的高宽
        for (int i = 0; i < c; ++i, p -= 2, q -= 2) {
            for (int col = i; col < i + q; ++col)
                res.add(matrix[i][col]);
            for (int row = i + 1; row < i + p; ++row)
                res.add(matrix[row][i + q - 1]);
            if (p == 1 || q == 1) break;//高或者宽为1 表示不用走下面部分
            for (int col = i + q - 2; col >= i; --col)
                res.add(matrix[i + p - 1][col]);
            for (int row = i + p - 2; row > i; --row)
                res.add(matrix[row][i]);
        }
        return res;
    }
}
