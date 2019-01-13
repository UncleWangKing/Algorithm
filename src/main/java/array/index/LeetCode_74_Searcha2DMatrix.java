package array.index;

public class LeetCode_74_Searcha2DMatrix {
    public static void main(String[] args) {
        int list[][] = {{1}
//                {1,   3,  5,  7},
//                {10, 11, 16, 20},
//                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(list, 1));
    }

    /**
     * 两次二分
     * 先定位行
     * 第一次碰到最后一个数大于等于target的行
     * 剪枝判断，看列的第一个数是否小于等于target，否则就是也大于target,可直接返回false
     * 继续二分查找
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return false;
        int lineLeft = 0, lineRight = matrix.length - 1;
        while (lineLeft < lineRight){
            int mid = lineLeft + (lineRight - lineLeft) / 2;
            if(target > matrix[mid][matrix[0].length - 1])
                lineLeft = mid + 1;
            else if(target < matrix[mid][matrix[0].length - 1])
                lineRight = mid;
            else
                return true;
        }
        /**
         * 确认合法
         */
        if(matrix[lineLeft][matrix[0].length - 1] >= target && matrix[lineLeft][0] <= target){
            int colLeft = 0, colRight = matrix[0].length - 1;
            while (colLeft < colRight){
                int mid = colLeft + (colRight - colLeft) / 2;
                if(target > matrix[lineLeft][mid])
                    colLeft = mid + 1;
                else if(target < matrix[lineLeft][mid])
                    colRight = mid;
                else
                    return true;
            }
            return target == matrix[lineLeft][colLeft];
        }

        return false;
    }

    /**
     * 利用坐标转换 一次二分
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if(0 == matrix.length || 0 == matrix[0].length)
            return false;
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[mid / n][mid % n] == target) return true;
            else if (matrix[mid / n][mid % n] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}
