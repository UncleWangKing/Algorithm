package array;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_119_PascalsTriangleII {
    public static void main(String[] args) {
        System.out.println(generate(3));
    }

    public static List<Integer> generate(int rowIndex) {
        List<Integer> ret = new LinkedList<>();
        long nk = 1;
        for (int i = 0; i <= rowIndex; i++) {
            ret.add((int)nk);
            nk = nk * (rowIndex - i) / (i + 1);
        }
        return ret;
    }
}
