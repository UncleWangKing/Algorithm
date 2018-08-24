package array;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_57_InsertInterval {
    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        public String toString(){
            return "[" + start + ","+end + "]";
        }
    }

    public static void main(String[] args) {
//        [1,2],[3,5],[6,7],[8,10],[12,16]
        List<Interval> list = new LinkedList<>();
        list.add(new Interval(1,2));
        list.add(new Interval(3,5));
        list.add(new Interval(6,7));
        list.add(new Interval(8,10));
        list.add(new Interval(12,16));
        System.out.println(insert(list, new Interval(4, 8)));
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        intervals.add(newInterval);
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
