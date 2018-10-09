package array.simulation;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_830_PositionsofLargeGroups {
    public static void main(String[] args) {
        System.out.println(largeGroupPositions("abbxxxxzyy"));
    }

    public static List<List<Integer>> largeGroupPositions(String S) {
        char[] charList = S.toCharArray();
        List<List<Integer>> list = new ArrayList<>();
        int i = 0;
        while (i < charList.length) {
            int j = i + 1;
            while (j < charList.length && charList[j - 1] == charList[j])
                j++;
            if(j - i >= 3){
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j - 1);
                list.add(temp);
            }
            i = j;
        }

        return list;
    }
}
