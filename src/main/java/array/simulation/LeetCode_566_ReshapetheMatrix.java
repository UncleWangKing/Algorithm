package array.simulation;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/19 18:26
 */
public class LeetCode_566_ReshapetheMatrix {
    public static void main(String[] args) {
        int list[][] = {
                {1,2},
                {3,4}
        };
        ZDaPangArrayUtil.printArray2(matrixReshape(list, 1, 4));
    }

    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        if(0 == nums.length || 0 == nums[0].length || r * c != nums[0].length * nums.length)
            return nums;

        int[][] list = new int[r][c];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                list[index / c][index % c] = nums[i][j];
                index++;
            }
        }
        return list;
    }
}
