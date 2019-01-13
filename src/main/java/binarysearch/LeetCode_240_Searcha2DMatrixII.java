package binarysearch;

public class LeetCode_240_Searcha2DMatrixII {
    public static void main(String[] args) {
        int list[][] = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(searchMatrix(list, 5));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return false;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] <= target){
                int left = 0, right = matrix[0].length - 1;
                while (left < right){
                    int mid = left + (right - left) / 2;
                    if(target > matrix[i][mid])
                        left = mid + 1;
                    else if(target < matrix[i][mid])
                        right = mid;
                    else
                        return true;
                }
                if(target == matrix[i][left]) return true;
            }
        }

        return false;
    }

    /**
     * 模拟搜索做法
     * 从左下或者右上开始搜
     * 每次横向和纵向的搜索都可以替换成二分查找 只是没必要弄那么复杂
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return false;
        int x = matrix.length - 1, y = 0;
        while (true) {
            if (matrix[x][y] > target) --x;
            else if (matrix[x][y] < target) ++y;
            else return true;
            if (x < 0 || y >= matrix[0].length) break;
        }
        return false;
    }
}
