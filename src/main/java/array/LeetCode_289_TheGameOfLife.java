package array;

import util.ZDaPangArrayUtil;

//状态0： 死细胞转为死细胞
//状态1： 活细胞转为活细胞
//状态2： 活细胞转为死细胞
//状态3： 死细胞转为活细胞

public class LeetCode_289_TheGameOfLife {
    public static void main(String[] args) {
        int[][] board = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        gameOfLife(board);
        ZDaPangArrayUtil.print(board);
    }

    public static void gameOfLife(int[][] board) {
        int rowLength = board.length;
        int columnLength = board[0].length;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                int sum = getNumber(board, i, j);
                if(2 == sum)
                    continue;
                else if(3 == sum)
                    board[i][j]=board[i][j] == 0 ? 3 : 1;
                else
                    board[i][j]=board[i][j]== 1 ? 2 : 0;
            }
        }

        for(int i = 0;i < rowLength; i++) {
            for(int j = 0;j < columnLength; j++)
                board[i][j]%=2;
        }
    }

    public static int getNumber(int[][] board, int x, int y){
        int rowLength = board.length;
        int columnLength = board[0].length;
        int sum=0;
        for(int i = x - 1; i < x + 2; ++i) {
            for(int j = y - 1; j < y + 2; ++j) {
                if(i == x && j == y)
                    continue;
                if(i >= 0 && i < rowLength && j >=0 && j < columnLength && (board[i][j] == 1 || board[i][j] == 2))
                    ++sum;
            }
        }
        return sum;
    }
}
