package array.simulation;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/19 18:21
 */
public class LeetCode_867_TransposeMatrix {
    public static void main(String[] args) {
        int list[][] = {
                {1,2},
                {4,5},
                {7,8}
        };
        ZDaPangArrayUtil.printArray2(transpose(list));
    }

    public static int[][] transpose(int[][] A) {
        int[][] list = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                list[j][i] = A[i][j];
        return list;
    }
}
