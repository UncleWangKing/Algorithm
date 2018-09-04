package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_118_PascalsTriangle {
    public static void main(String[] args) {
        System.out.println(generate(10));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if(0 == numRows)
            return list;
        List<Integer> lastList = Arrays.asList(1);
        list.add(lastList);
        if(1 == numRows)
            return list;
        for (int i = 2; i <= numRows; i++) {
            List<Integer> tempList = new ArrayList<>();
            list.add(tempList);
            for (int j = 0; j < i; j++) {
                if(0 == j || i -1 == j)
                    tempList.add(1);
                else
                    tempList.add(lastList.get(j)+ lastList.get(j-1));
            }
            lastList = tempList;
        }
        return list;
    }
}
