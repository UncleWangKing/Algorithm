package str.simulation;

public class LeetCode_657_RobotReturnToOrigin {
    public static void main(String[] args) {
        System.out.println(judgeCircle("UDLF"));
    }

    public static boolean judgeCircle(String moves) {
        int row = 0, col = 0;
        char[] chars = moves.toCharArray();
        for (char c:chars) {
            if(c == 'L')
                row--;
            else if(c == 'R')
                row++;
            else if(c == 'U')
                col++;
            else
                col--;
        }
        return 0 == row && row == col;
    }
}
