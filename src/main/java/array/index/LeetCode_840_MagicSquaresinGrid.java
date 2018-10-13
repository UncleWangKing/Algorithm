package array.index;

public class LeetCode_840_MagicSquaresinGrid {
    public static void main(String[] args) {
        int list [][]= {
            {4,3,8,4},
            {9,5,1,9},
            {2,7,6,2}
        };
        System.out.println(numMagicSquaresInside(list));
    }

    public static int numMagicSquaresInside(int[][] grid){
        //排除不够3X3的数组矩阵
        if (grid.length < 3)
            return 0;

        int sum = 0;
        //找九宫格中间的数字
        for (int i = 1;i < grid.length - 1;i++){
            for (int j = 1;j < grid.length - 1;){
                //上述 条件1&&条件2
                if (grid[i][j] == 5 && isLawfulNum(grid, j, i)){
                    sum ++;
                    //如果当前九宫格满足条件意味着：
                    //向右移动一位的九宫格中间的数字不是5
                    //自己脑补一下把....
                    j += 2;
                }else
                    j++;
            }
        }
        return sum;
    }
    //条件2
    private static boolean isLawfulNum(int[][] grid,int x,int y){
        int num1 = grid[y - 1][x - 1];
        int num2 = grid[y - 1][x];
        int num3 = grid[y][x - 1];
        int num4 = grid[y - 1][x + 1];
        int num5 = grid[y][x + 1];
        int num6 = grid[y + 1][x - 1];
        int num7 = grid[y + 1][x];
        int num8 = grid[y + 1][x + 1];
        //俩数相加必须等于10  还不能超过范围1—9，同时还不能是5，因此除5取余数来排除0，5，10
        if (num1 % 5 != 0 && num2 % 5 != 0 && num3 % 5 != 0 && num4 % 5 != 0)
            if (10 - num1 == num8 && 10 - num2 == num7 && 10 - num3 == num5 && 10 - num4 == num6)
                return true;
        return false;
    }

    private static boolean helper(int[][] grid, int x,int y){
        if(grid[x+1][y+1]!=5) return false;

        int[] valid=new int[16];

        for(int i=x;i<=x+2;i++)
            for(int j=y;j<=y+2;j++)
                valid[grid[i][j]]++;

        for (int v = 1; v <= 9; v++)
            if (valid[v] != 1) return false;

        if((grid[x][y]+grid[x][y+1]+grid[x][y+2])!=15)         return false;
        if((grid[x][y]+grid[x+1][y+1]+grid[x+2][y+2])!=15)     return false;
        if((grid[x][y]+grid[x+1][y]+grid[x+2][y])!=15)         return false;
        if((grid[x+2][y]+grid[x+2][y+1]+grid[x+2][y+2])!=15)   return false;
        if((grid[x][y+2]+grid[x+1][y+2]+grid[x+2][y+2])!=15)   return false;
        if((grid[x][y+2]+grid[x+1][y+1]+grid[x+2][y])!=15)     return false;
        return true;
    }
}
