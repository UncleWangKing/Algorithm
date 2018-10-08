package array.classic;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/10/8 16:25
 */
public class LeetCode_782_TransformtoChessboard {
    public static void main(String[] args) {
        int list [][] = {
                {0,1,1,0},
                {0,1,1,0},
                {1,0,0,1},
                {1,0,0,1}
        };//2
        /**
         *  0110     1010     1010
         *  0110 --> 1010 --> 0101
         *  1001     0101     1010
         *  1001     0101     0101
         */
        System.out.println(movesToChessboard(list));
    }

    /**
     * 分析：先观察一个chessBoard 如
     *  {1,0,1,0},
     *  {0,1,0,1},
     *  {1,0,1,0},
     *  {0,1,0,1}
     *
     *  看他有哪些 不随着行列交换而改变的特性 用以判断是否返回-1
     *  分析出如下两个
     *  1：只能有且仅有两种行类型，例如如果一种行类型为01010011，那么另外一种行类型只能为01010011或者10101100，该限制条件同样适合于列类型。
     *  2：每一行和每一列中，0和1的数量都是相等或差1的，棋盘是N * N
     *  如果N = 2 * K，那么每一行每一列有且仅有K个0和K个1
     *  如果N = 2 * K + 1，那么每一行每一列要么有K个1和K + 1个0，要么有K + 1个1和K个0。
     *
     *  然后根据上述性质筛选出的二维数组 再计算所需交换次数也就简单了
     */
    public static int movesToChessboard(int[][] board) {
        int n = board.length;
        /**
         * 分析1的验证部分
         * 任意以[0,0][i,j]为左上右下的矩形四个角必须满足全0全1或者0011 使用异或写法
         * 这个是上方分析1的等效写法
         */
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(1 == (board[0][0] ^ board[0][j] ^ board[i][0] ^ board[i][j]))
                    return -1;
            }
        }
        int row = 0, col = 0;
        int cntrow = 0, cntcol = 0;
        /**
         * 分析2的验证部分
         * 顺带统计了如果合格 行列需要的交换次数
         */
        for(int i = 0; i < n; i++) {
            row += board[0][i];
            col += board[i][0];
            if(board[0][i] != i % 2) cntrow++;
            if(board[i][0] != i % 2) cntcol++;
        }
        if(row < n >> 1 || row > (n + 1) >> 1) return -1;
        if(col < n >> 1 || col > (n + 1) >> 1) return -1;
        /**
         * 在分析1,2的基础上 使用的计算步骤数的方法就不再需要去模拟交换了
         * 交换列就能调整行 反之亦然
         * 再根据奇偶 分别计算出 调整行列所需步骤
         */
        int res = 0;
        if(0 == (n & 1)){//偶数
            res += Math.min(cntrow, n - cntrow);
            res += Math.min(cntcol, n - cntcol);
        }
        else{
            if(1 == (cntrow & 1))//奇数
                cntrow = n - cntrow;
            if(1 == (cntcol & 1))//奇数
                cntcol = n - cntcol;
            res = cntrow + cntcol;
        }
        return res >> 1;//除2
    }
}
