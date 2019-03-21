package binarysearch;

import util.ZDaPangArrayUtil;

import java.util.*;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2019/3/21 15:48
 */
public class LeetCode_436_FindRightInterval {
    public static void main(String[] args) {
        Interval interval1 = new Interval(3, 4);
        Interval interval2 = new Interval(2, 3);
        Interval interval3 = new Interval(1, 2);
        Interval[] intervals = {interval1, interval2, interval3};
        ZDaPangArrayUtil.printArray(findRightInterval(intervals));
    }

    public static int[] findRightInterval(Interval[] intervals) {
        int [] result = new int[intervals.length];
        List<Integer> v = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap();
        for (int i = 0; i < intervals.length; ++i) {
            m.put(intervals[i].start, i);
            v.add(intervals[i].start);
        }
        Collections.sort(v);
        int index = 0;
        for (Interval a : intervals) {
            int begin = 0;
            int end = v.size();
            while (begin < end){
                int mid = begin + (end - begin) / 2;
                if(v.get(mid) >= a.end)
                    end = mid;
                else
                    begin = mid + 1;
            }
            result[index++] = (begin < v.size()) ? m.get(v.get(begin)) : -1;
        }

        return result;
    }

    private static class Interval {
        public int start;
        public int end;
        Interval() {
            start = 0; end = 0;
        }
        Interval(int s, int e) {
            start = s; end = e;
        }
    }
}
