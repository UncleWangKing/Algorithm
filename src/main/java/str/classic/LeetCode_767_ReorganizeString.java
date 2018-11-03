package str.classic;

import java.util.*;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/11/2 19:12
 */
public class LeetCode_767_ReorganizeString {
    public static void main(String[] args) {
        System.out.println(reorganizeString2("vvvlo"));
    }

    /**
     * 还可以的题
     * 数字符数量 最大的字符和数量要缓存
     * 为了两件事
     * 1.判断是否可重排 偶数个不能大于一半 奇数个不能大于一半+1 -- 奇数个参考"aba"
     * 2.如果可重排 就按每次取数量最大的两个字母来拼 知道字母用完
     * 当时写思路是有了 但是第二步没有想到最大堆 所以一直没有信心去写完~
     */
    public static String reorganizeString(String S) {
        StringBuilder builder = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        Queue<Pair> q = new PriorityQueue<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > (S.length() + 1) / 2) return "";////简化了奇偶判断 基操 坐下
            q.add(new Pair(entry.getValue(), entry.getKey()));
        }
        while (q.size() >= 2) {
            Pair t1 = q.poll();
            Pair t2 = q.poll();
            builder.append(t1.character);
            builder.append(t2.character);
            if (--t1.count > 0) q.add(t1);
            if (--t2.count > 0) q.add(t2);
        }
        if (q.size() > 0) builder.append(q.poll().character);
        return builder.toString();
    }

    private static class Pair implements Comparable<Pair>{
        public int count;
        public char character;
        public Pair(int count, char character){
            this.count = count;
            this.character = character;
        }

        @Override
        public int compareTo(Pair o) {
            return o.count - this.count;
        }
    }

    /**
     * 方丈说了多少次了 字符数数什么的 用char[]啊
     * 这里cnt又存了字符又存了位置 秀得飞起
     * 放置时候 从少到多取 从1第一个位置 间隔1个放 完了再从0来一遍 完成 妙不可言
     */
    public static String reorganizeString2(String S){
        int n = S.length(), idx = 1;
        int[] cnt = new int[26];
        char[] list = S.toCharArray();
        for (char c : list) cnt[c - 'a'] += 100;//数量*100
        for (int i = 0; i < 26; ++i) cnt[i] += i;
        Arrays.sort(cnt);
        for (int num : cnt) {
            int t = num / 100;//拿数量
            char ch = (char)('a' + (num % 100));//拿字符
            if (t > (n + 1) / 2) return "";//判合法
            for (int i = 0; i < t; ++i) {
                if (idx >= n) idx = 0;
                list[idx] = ch;
                idx += 2;
            }
        }
        return String.valueOf(list);
    }
}
