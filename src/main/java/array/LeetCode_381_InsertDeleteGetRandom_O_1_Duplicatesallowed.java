package array;

import java.util.*;

public class LeetCode_381_InsertDeleteGetRandom_O_1_Duplicatesallowed {
    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
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

    private static class RandomizedCollection {
        List<Integer> list;
        Map<Integer, Set<Integer>> map;
        Random rand = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean contain = map.containsKey(val);
            if (! contain) map.put(val, new LinkedHashSet<>());
            map.get(val).add(list.size());//set里放下标
            list.add(val);//list放值
            return ! contain;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (! map.containsKey(val)) return false;
            int setFirstIndex = map.get(val).iterator().next();//取val的set里第一个下标
            map.get(val).remove(setFirstIndex);//删掉set里第一个 -- set删本来就是O(1)不用特殊处理
            if (setFirstIndex < list.size() - 1) {
                int listLastIndex = list.size() - 1;
                int listLastVal = list.get(listLastIndex); //和list最后一个交换
                list.set(setFirstIndex, listLastVal);
                //一删一加 就是交换
                map.get(listLastVal).remove(listLastIndex);
                map.get(listLastVal).add(setFirstIndex);
            }
            list.remove(list.size() - 1);//list只有删的是最后一个元素 这样才是O(1)

            if (map.get(val).isEmpty()) map.remove(val);
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}
