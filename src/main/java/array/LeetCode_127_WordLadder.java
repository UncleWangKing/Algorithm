package array;

import java.util.Arrays;
import java.util.List;

public class LeetCode_127_WordLadder {
    public static void main(String[] args) {
        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList = Arrays.asList("hot","dog","dot");

        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int res = 0;
        for (int i = 0; i < wordList.size();) {
            String tempWord = "";
            while (i < wordList.size() && changAble(beginWord, wordList.get(i))) {
                tempWord = wordList.get(i);
                if(endWord.equals(wordList.get(i)))
                    return ++res;
                i++;
            }
            if(tempWord.equals("")){
                if(i < wordList.size() - 1){
                    beginWord = wordList.get(i);
                    i++;
                }
                else
                    return 0;
            }
            else
                beginWord = tempWord;

            res++;
        }
        return 0;
    }

    public static boolean changAble(String left, String right){
        char[] l = left.toCharArray();
        char[] r = right.toCharArray();
        int count = 0;
        for (int i = 0; i < l.length; i++)
            if(l[i] != r[i])
                count++;
        return 1 == count;
    }
}