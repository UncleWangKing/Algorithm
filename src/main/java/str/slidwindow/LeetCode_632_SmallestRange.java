package str.slidwindow;

import util.ZDaPangArrayUtil;

import java.util.*;

public class LeetCode_632_SmallestRange {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4,10,15,24,26));//[4,26]
        list.add(Arrays.asList(0,9,12,20));//[0,20]
        list.add(Arrays.asList(5,18,22,30));//[5,30]

        ZDaPangArrayUtil.printArray(smallestRange(list));//[20,24]
    }

    /**
     * 用pair记录 就变成了 76题滑动窗口了
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        List<Pair> v = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.size(); ++i)
            for (int num : nums.get(i))
                v.add(new Pair(num, i));

        Collections.sort(v);

        int left = 0, n = v.size(), k = nums.size(), cnt = 0, diff = Integer.MAX_VALUE;
        for (int right = 0; right < n; ++right) {
            if (! m.containsKey(v.get(right).second) || 0 == m.get(v.get(right).second))
                ++cnt;

            m.put(v.get(right).second, m.getOrDefault(v.get(right).second, 0) + 1);

            while (cnt == k && left <= right) {
                if (diff > v.get(right).first - v.get(left).first) {
                    diff = v.get(right).first - v.get(left).first;
                    res = new int[]{v.get(left).first, v.get(right).first};
                }

                m.put(v.get(left).second, m.get(v.get(left).second) - 1);
                if (0 == m.get(v.get(left).second)) --cnt;

                ++left;
            }
        }
        return res;
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
}
