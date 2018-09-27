package array.simulation;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/9/27 17:05
 */
public class LeetCode_695_MaxAreaofIsland {
    public static void main(String[] args) {
        int list[][] = {
//                {1,1,0,1,1},
//                {1,0,0,0,0},
//                {0,0,0,0,1},
//                {1,1,0,1,1},
                {0,1},
                {1,1}
        };
        System.out.println(maxAreaOfIsland(list));
    }

    /**
     * DFS
     */
    public static int maxAreaOfIsland(int[][] grid) {
        boolean [][]visited = new boolean[grid.length][grid[0].length];
        int max = 0;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if(! visited[i][j])
                    max = Math.max(max, helper(grid, visited, i, j));

        return max;
    }

    public static int helper(int[][] grid, boolean [][]visited, int i, int j){
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length){
            if(visited[i][j]) return 0;
            visited[i][j] = true;
            if(1 == grid[i][j])
                return helper(grid, visited, i, j - 1)//上
                        + helper(grid, visited, i, j + 1)//下
                        + helper(grid, visited, i - 1, j)//左
                        + helper(grid, visited, i + 1, j) + 1;//右
            else
                return 0;
        }
        return 0;
    }
//    public static int maxAreaOfIsland(int[][] grid) {
//        int global = 0;
//        int local[][] = new int [grid.length][grid[0].length];
//
//        local[0][0] = grid[0][0];
//        //构建递推初始项 第一行
//        for (int i = 1; i < grid[0].length; i++) {
//            if(1 == grid[0][i]){
//                local[0][i] = local[0][i - 1] + 1;
//                if(0 != local[0][i - 1])local[0][i - 1]++;
//            }
//            else {
//                local[0][i] = 0;
//            }
//            global = Math.max(global, local[0][i]);
//        }
//        //构建递推初始项 第一列
//        for (int i = 1; i < grid.length; i++){
//            if(1 == grid[i][0]){
//                local[i][0] = local[i - 1][0] + 1;
//                if(0 != local[i - 1][0])local[i - 1][0]++;
//            }
//            else {
//                local[i][0] = 0;
//            }
//            global = Math.max(global, local[i][0]);
//        }
//
//        for (int i = 1; i < grid.length; i++) {
//            for (int j = 1; j < grid[0].length; j++) {
//                if(1 == grid[i][j]){
//                    local[i][j] = Math.max(local[i - 1][j], local[i][j - 1]) + 1;
//                    if(0 != local[i - 1][j])local[i - 1][j]++;
//                    if(0 != local[i][j - 1])local[i][j - 1]++;
//                }
//                else {
//                    local[i][j] = 0;
//                }
//                global = Math.max(global, local[i][j]);
//            }
//        }
//
//        return Math.max(global, local[0][0]);
//    }
}
