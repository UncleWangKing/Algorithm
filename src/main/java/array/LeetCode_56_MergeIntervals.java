package array;

import java.util.*;

public class LeetCode_56_MergeIntervals {

     public static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
         public String toString(){
             return "[" + start + ","+end + "]";
         }
     }

    public static void main(String[] args) {
//        [[2,3],[4,5],[6,7],[8,9],[1,10]]
        List<Interval> list = new LinkedList<>();
        list.add(new Interval(2,3));
        list.add(new Interval(4,5));
        list.add(new Interval(6,7));
        list.add(new Interval(8,9));
        list.add(new Interval(1,10));
        System.out.println(merge(list));
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new LinkedList<>();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < intervals.size(); i++) {
            Interval temp = new Interval(intervals.get(i).start, intervals.get(i).end);
            while(i + 1 < intervals.size() && temp.end >= intervals.get(i + 1).start) {
                temp.end = Math.max(temp.end, intervals.get(i + 1).end);
                i++;
            }
            res.add(temp);
        }
        return res;
    }
}
