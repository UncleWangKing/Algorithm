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
        }

        public boolean book(int start, int end) {
            int count = 0;
            ArrayList<Pair> tempList = new ArrayList<>();
            for (Pair pair : list) {
                /**
                 * 不能简单交叉 因为可能三个交叉但并未超2个
                 * 比如 [1,3][2,4][3,5] [2,4]和左右交叉 但总体并未有超2的安排
                 * 所以要记录产生交叉的pair是否已经交叉 如果已经交叉 则false
                 * 否则true
                 */
                if((start >= pair.key && start < pair.val
                    ||
                    (end > pair.key && end < pair.val))
                    ||
                    (start < pair.key && end >= pair.val)){
                    for (Pair tempPair:tempList) {
                        if((tempPair.key >= pair.key && tempPair.key < pair.val
                                ||
                                (tempPair.val > pair.key && tempPair.val < pair.val))
                                ||
                                (tempPair.key < pair.key && tempPair.val >= pair.val))
                            return false;
                    }
                    tempList.add(pair);
                }

            }

            list.add(new Pair(start, end));
            return true;
        }
    }
}
