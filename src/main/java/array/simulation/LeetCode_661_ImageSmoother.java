package array.simulation;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/25 12:36
 */
public class LeetCode_661_ImageSmoother {
    public static void main(String[] args) {
        int list[][] = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };

        ZDaPangArrayUtil.printArray2(imageSmoother(list));
    }

    public static int[][] imageSmoother(int[][] M) {
        int res[][] = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                int temp = 0;
                int count = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j+1; y++) {
                        if(x >=0 && x < M.length && y >= 0 && y < M[0].length) {
                            temp += M[x][y];
                            count++;
                        }
                    }
                }
                res[i][j] = temp / count;
            }
        }

        return res;
    }
}
