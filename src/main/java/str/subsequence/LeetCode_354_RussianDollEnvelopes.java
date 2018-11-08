package str.subsequence;

import java.util.*;

public class LeetCode_354_RussianDollEnvelopes {
    public static void main(String[] args) {
//        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};//3
        int[][] envelopes = {{1,3},{3,5},{6,7},{6,8},{8,4},{9,5}};//3
//        int[][] envelopes = {{1,1},{1,1},{1,1}};//3
        System.out.println(maxEnvelopes(envelopes));
    }
    //变种的Lis 宽高都要更大 那么排序先 -- 宽高固定 认真读题~
    public static int maxEnvelopes(int[][] envelopes) {
        List<Pair> list = new ArrayList<>();
        for (int[] envelope : envelopes)
            list.add(new Pair(envelope[0], envelope[1]));
        Collections.sort(list);
        int dp[] = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (list.get(i).first > list.get(j).second && list.get(i).second > list.get(j).second) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private static class Pair implements Comparable<Pair>{
        public int first;
        public int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return this.first - o.first;
        }
    }

    /**
     * 码是人家香 为何别人这么快~
     * 1.原地排序
     * 2.宽度都排好序了，只用看高度，用300题的二分优化方式查找
     */
    public static int maxEnvelopes2(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] args1, int[] args2) {
                if (args1[0] == args2[0])
                    return args2[1] - args1[1];
                else
                    return args1[0] - args2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int count = 0;
        for (int[] envelop : envelopes) {
            int i = 0, j = count;
            while (i < j) {
                int m = (i + j) / 2;
                if (dp[m] < envelop[1]) i = m + 1;
                else j = m;
            }
            dp[i] = envelop[1];
            if (i == count) ++count;
        }
        return count;
    }
}
