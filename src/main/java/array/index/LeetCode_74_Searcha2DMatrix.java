package array.index;

public class LeetCode_74_Searcha2DMatrix {
    public static void main(String[] args) {
        int list[][] = {{1},{3}
//                {1,   3,  5,  7},
//                {10, 11, 16, 20},
//                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(list, 0));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return false;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][matrix[0].length-1] >= target)
                for (int j = 0; j < matrix[0].length; j++) {
                    if(matrix[i][j] == target)
                        return true;
                    else if(matrix[i][j] > target)
                        return false;
                }
        }
        return false;
    }
}
