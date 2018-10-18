package datastructure;

import java.util.*;

public class LeetCode_715_RangeModule {
    /**
     * addRange(10, 20): null
     * removeRange(14, 16): null
     * queryRange(10, 14): true （区间 [10, 14) 中的每个数都正在被跟踪）
     * queryRange(13, 15): false （未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
     * queryRange(16, 17): true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
     */
    public static void main(String[] args) {
        RangeModule2 rangeModule = new RangeModule2();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        System.out.println(rangeModule.queryRange(10, 14));
        System.out.println(rangeModule.queryRange(13, 15));
        System.out.println(rangeModule.queryRange(16, 17));
    }

    public static class RangeModule {
        private class Interval {
            public int left;
            public int right;
            public Interval(int left, int right){
                this.left = left;
                this.right = right;
            }
        }

        private List<Interval> list;

        public RangeModule() {
            list = new LinkedList<>();
        }

        /**
         * 插入保证有序 并合并重复区间
         */
        public void addRange(int left, int right) {
            List<Interval> res = new LinkedList<>();
            int n = list.size(), cur = 0;
            for (int i = 0; i < n; ++i) {
                if (list.get(i).right < left) {//前面的加进去
                    res.add(list.get(i));
                    ++cur;
                } else if (list.get(i).left > right) {//后面的也加进去
                    res.add(list.get(i));
                } else {
                    left = Math.min(left, list.get(i).left);
                    right = Math.max(right, list.get(i).right);
                }
            }
            res.add(cur, new Interval(left, right));
            list = res;
        }

        public boolean queryRange(int left, int right) {
            for (Interval interval : list)
                if(left >= interval.left && right <= interval.right)
                    return true;

            return false;
        }

        /**
         * 删除保证有序 并看情况分裂区间
         */
        public void removeRange(int left, int right) {
            List<Interval> res = new LinkedList<>();
            List<Interval> temp = new LinkedList<>();
            int n = list.size(), cur = 0;
            for (int i = 0; i < n; ++i) {
                if (list.get(i).right <= left) {//前面的加进去
                    res.add(list.get(i));
                    ++cur;
                } else if (list.get(i).left >= right) {//后面的也加进去
                    res.add(list.get(i));
                } else {
                    if (list.get(i).left < left) {
                        temp.add(new Interval(list.get(i).left, left));
                    }
                    if (list.get(i).right > right) {
                        temp.add(new Interval(right, list.get(i).right));
                    }
                }
            }
            res.addAll(cur, temp);
            list = res;
        }
    }

    /**
     * 看看人家！
     */
    public static class RangeModule2 {

        public TreeMap<Integer,Integer> map;

        public RangeModule2() {
            map = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if(start==null && end==null){
                map.put(left, right);
            }else if(start!=null && map.get(start)>=left){
                map.put(start,Math.max(map.get(end), Math.max(map.get(start), right)));
            }else{
                map.put(left,Math.max(right, map.get(end)));
            }
            Map<Integer,Integer> subMap = map.subMap(left, false, right, true);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }

        public boolean queryRange(int left, int right) {
            Integer start = map.floorKey(left);
            if(start==null || map.get(start)<right){
                return false;
            }
            return true;
        }

        public void removeRange(int left, int right) {
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if(end!=null && map.get(end)>right){
                map.put(right, map.get(end));
            }
            if(start!=null && map.get(start)>left){
                map.put(start, left);
            }
            Map<Integer,Integer> subMap = map.subMap(left, true, right, false);
            Set<Integer> set = new HashSet<>(subMap.keySet());
            map.keySet().removeAll(set);
        }
    }
}
