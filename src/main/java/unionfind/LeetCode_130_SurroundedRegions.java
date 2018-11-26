package unionfind;

import util.ZDaPangArrayUtil;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/26 15:36
 */
public class LeetCode_130_SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
            };
        solve(board);
        ZDaPangArrayUtil.printArray2(board);
    }

    /**
     * 本质是可以'触边'的就是不被包围的
     * dfs 四条边的dfs置 & 其余全部'O' -> 'X'
     */
    public static void solve(char[][] board) {
        if(0 == board.length || 0 == board[0].length)
            return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0, m, n);
            dfs(board, i, n - 1, m, n);
        }
        for (int i = 0; i < n; i++) {
            dfs(board, 0, i, m, n);
            dfs(board, m - 1, i, m, n);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if('&' == board[i][j])
                    board[i][j] = 'O';
                else if('O' == board[i][j])
                    board[i][j] = 'X';
            }
        }
    }

    public static void dfs(char[][] board, int x, int y, int m, int n){
        if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O'){
            board[x][y] = '&';
            dfs(board, x + 1, y, m, n);
            dfs(board, x - 1, y, m, n);
            dfs(board, x, y + 1, m, n);
            dfs(board, x, y - 1, m, n);
        }
    }
}
