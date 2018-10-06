package datastructure;

import java.util.*;

public class LeetCode_731_MyCalendarII {
    public static void main(String[] args) {
        MyCalendarTwo myCalendar = new MyCalendarTwo();
        System.out.println(myCalendar.book(10, 20));// returns true
        System.out.println(myCalendar.book(50, 60));// returns true
        System.out.println(myCalendar.book(10, 40));// returns true
        System.out.println(myCalendar.book(5, 15));// returns false
        System.out.println(myCalendar.book(5, 10));// returns true
        System.out.println(myCalendar.book(25, 55));// returns true
    }

    /**
     * hashmap不能有重复
     */
    public static class MyCalendarTwo {
        private List<Pair> list;
        private List<Pair> list_2;
        private Map<Integer, Integer> map;
        private class Pair{
            public int key;
            public int val;
            public Pair(int key, int val){
                this.key = key;
                this.val = val;
            }
        }

        public MyCalendarTwo() {
            list = new LinkedList<>();
            list_2 = new LinkedList<>();
            map = new TreeMap<>();
        }

        /**
         * 保留了两种判断区间的方法 用以学习
         */
        public boolean book2(int start, int end) {
            for (Pair pair : list_2)
                if(! (start >= pair.val || end <= pair.key))
                    return false;
            for (Pair pair : list)
                if(Math.max(pair.key, start) < Math.min(pair.val, end))
                    list_2.add(new Pair(Math.max(pair.key, start), Math.min(pair.val, end)));

            list.add(new Pair(start, end));
            return true;
        }

        /**
         * 利用treeMap 有序
         * 有序遍历 遇到出现过的start 就 + 1 遇到 end -1
         * 大于等于3 说明区间内有三件事
         */
        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int cnt = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                cnt += entry.getValue();
                if (cnt == 3) {
                    map.put(start, map.getOrDefault(start, 0) - 1);
                    map.put(end, map.getOrDefault(end, 0) + 1);
                    return false;
                }
            }
            return true;
        }
    }
}
