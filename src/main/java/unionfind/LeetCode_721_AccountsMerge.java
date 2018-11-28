package unionfind;

import java.util.*;

public class LeetCode_721_AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        System.out.println(accountsMerge(accounts));
    }

    private static int find(int[] arr, int x) {
        if (arr[x] != x) {
            return find(arr, arr[x]);
        } else {
            return x;
        }
    }

    private static void union(int[] arr, int x1, int x2) {
        int f1 = find(arr, x1);
        int f2 = find(arr, x2);
        if (f1 != f2) {
            arr[Math.max(f1, f2)] = Math.min(f1, f2);//字典序决定根
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        int[] person = new int[accounts.size()];
        for (int i = 0; i < person.length; ++i) {
            person[i] = i;
        }
        Map<String, Integer> emailMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            List<String> list = accounts.get(i);
            for (int j = 1; j < list.size(); ++j) {
                if (emailMap.containsKey(list.get(j))) {
                    union(person, emailMap.get(list.get(j)), i);
                } else {
                    emailMap.put(list.get(j), i);
                }
            }
        }
        Map<Integer, Set<String>> posMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailMap.entrySet()) {
            int p = find(person, entry.getValue());
            Set<String> set = posMap.getOrDefault(p, new HashSet<>());
            set.add(entry.getKey());
            posMap.put(p, set);
        }
        for (Map.Entry<Integer, Set<String>> entry : posMap.entrySet()) {
            List<String> list = new ArrayList<>();
            list.addAll(entry.getValue());
            Collections.sort(list);
            list.add(0, accounts.get(entry.getKey()).get(0));
            result.add(list);
        }
        return result;
    }
}
