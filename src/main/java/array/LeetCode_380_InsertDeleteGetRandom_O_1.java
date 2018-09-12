package array;

import java.util.*;

public class LeetCode_380_InsertDeleteGetRandom_O_1 {
    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(0));
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(0));
        System.out.println(obj.insert(2));
        System.out.println(obj.remove(1));
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
    }
    //size的使用 提高了删除效率 比List要快 因为不用实际删除 但是可能有回收问题 因为未被实际删除
    //去掉size 使用list.size()即可保持正常 但OJ会慢一些
    private static class RandomizedSet {
        Map<Integer, Integer> map;
        List<Integer> list;
        Random random;
        int size = 0;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map =  new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
            size = 0;
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val))
                return false;

            list.add(size, val);//数组长度并不真实，不能直接add
            map.put(val, size++);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val))
                return false;

            int tailKey = list.get(size - 1);
            map.put(tailKey,map.get(val));
            list.set(map.get(val),tailKey);
            size--;
            map.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(random.nextInt(size));
        }
    }
}
