package array.simulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_914_XofaKindInaDeckofCards {
    public static void main(String[] args) {
//        int list[] = {1,2,3,4,4,3,2,1};
//        int list[] = {1};
//        int list[] = {1,1,2,2,2,2};
//        int list[] = {1,1,1,2,2,2,3,3};
//        int list[] = {1,1,1,1,2,2,2,2,2,2};
//        int list[] = {1,2,3,4,4,3,2,1};
        int list[] = {0,0,0,1,1,1,1,1,1,2,2,2};
        System.out.println(hasGroupsSizeX(list));
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        int[] deckList = new int[10002];

        for(int d : deck)
            deckList[d]++;

        boolean result = false;
        for(int i = 2;i <= 10000;i++){
            int j;
            for(j = 0; j < 10000; j++)
                if(0 != deckList[j] % i)
                    break;
            if(10000 == j){
                result = true;
                break;
            }
        }
        return result;
    }
}
