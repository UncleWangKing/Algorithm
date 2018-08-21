package array;

import util.ZDaPangArrayUtil;

import java.util.*;

public class LeetCode_739_DailyTemperatures {
    public static void main(String[] args) {
        int list[] = {73, 74, 75, 71, 69, 72, 76, 73};

        ZDaPangArrayUtil.printArray(dailyTemperaturesv5(list));
    }

    //数组代替栈
    public static int[] dailyTemperaturesv5(int[] temperatures) {
        int[] result = new int[temperatures.length];
        int[] stack = new int[temperatures.length];
        int top = -1;
        for(int i = 0;i < temperatures.length; i++) {
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int index = stack[top--];
                result[index] = i - index;
            }
            stack[++top] = i;
        }
        return result;
    }

    //改用 栈
    public static int[] dailyTemperaturesv4(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temperatures.length; i++) {
            while(! stack.empty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }

    //数组 去掉多余的二分 n 从大到小 但是数组删除会浪费时间 用链表也会浪费查找时间
    public static int[] dailyTemperatures3(int[] temperatures) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < temperatures.length; i++) {
            //强依赖遍历顺序 0->list.size()
            for (int j = list.size() - 1; list.size() > 0 && temperatures[i] > temperatures[list.get(j)]; j--) {
                temperatures[list.get(j)] = i - list.get(j);
                list.remove(list.get(j));
            }
            list.add(i);
        }

        for (int i = 0; i < list.size(); i++) {
            temperatures[list.get(i)] = 0;
        }

        return temperatures;
    }

    //数组二分查询 n^log2n 从大到小
    public static int[] dailyTemperatures2(int[] temperatures) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < temperatures.length; i++) {
            int insertIndex = binarySearch(0, list.size(), list, temperatures, temperatures[i]);
            list.add(insertIndex, i);
            //强依赖遍历顺序 0->list.size()
            for (int j = list.size() - 1; j > insertIndex ; j--) {
                temperatures[list.get(j)] = i - list.get(j);
                list.remove(list.get(j));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            temperatures[list.get(i)] = 0;
        }

        return temperatures;
    }

    public static int binarySearch(int begin, int end, List<Integer> list, int [] temperatures, int value){
        if(begin == end)
            return begin;

        int mid = (end + begin) / 2;

        if (value > temperatures[list.get(mid)]) {
            return binarySearch(begin, mid, list, temperatures, value);
        } else
            return binarySearch(mid + 1, end, list, temperatures, value);
    }

    //超时 map遍历导致n^2
    public static int[] dailyTemperatures(int[] temperatures) {
        Map<Integer, Integer> map = new HashMap<>(temperatures.length);

        for (int i = 0; i < temperatures.length; i++) {
            map.put(i, 0);
            Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Integer, Integer> entry = it.next();
                if(temperatures[entry.getKey()] < temperatures[i]) {
                    temperatures[entry.getKey()] = entry.getValue();
                    it.remove();
                }else
                    entry.setValue(entry.getValue() + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            temperatures[entry.getKey()] = 0;
        }

        return temperatures;
    }
}
