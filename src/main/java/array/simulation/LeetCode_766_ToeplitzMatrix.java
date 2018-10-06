package array.simulation;

public class LeetCode_766_ToeplitzMatrix {
    public static void main(String[] args) {
        int list[][] = {
                {1,2,3,4},
                {5,1,2,3},
                {9,5,1,2}
        };
        System.out.println(isToeplitzMatrix(list));
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, p = m - 1, q = 0;
        while (p >= 0 && q < n) {
            int val = matrix[p][q], i = p, j = q;
            while (i + 1 < m && j + 1 < n)
                if (matrix[++i][++j] != val) return false;
            if(p > 0)
                --p;
            else
                ++q;
        }
        return true;
    }

    public static boolean isToeplitzMatrix2(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; ++i) {
            for (int j = 0; j < matrix[i].length - 1; ++j) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }
        return true;
    }
}
