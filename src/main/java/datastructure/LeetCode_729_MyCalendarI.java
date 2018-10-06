package datastructure;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_729_MyCalendarI {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));// returns true
        System.out.println(myCalendar.book(15, 25));// returns false
        System.out.println(myCalendar.book(20, 30));// returns true
    }

    /**
     * 空间小 还是查询快 是一个要权衡的地方 这里选择查询快
     */
    public static class MyCalendar {
        private Map<Integer,Integer> map;

        public MyCalendar() {
            map = new HashMap<>();
        }

        public boolean book(int start, int end) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet())
                if(! (start >= entry.getValue() || end <= entry.getKey()))
                    return false;

            map.put(start, end);
            return true;
        }
    }
}
