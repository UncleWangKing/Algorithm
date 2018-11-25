package unionfind;

import util.ZDaPangArrayUtil;

public class LeetCode_684_RedundantConnection {
    public static void main(String[] args) {
        int [][]edges = {{1,2}, {1,3}, {2,3}};
        ZDaPangArrayUtil.printArray(findRedundantConnection2(edges));
    }

    public static int[] findRedundantConnection(int[][] edges) {
        int []root = new int[2001];//root[i]初始可以是i 也可以是任意 这里用0 因为输入里没有0
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
        for (int[] edge : edges) {
            int x = find(root, edge[0]), y = find(root, edge[1]);
            if (x == y) return edge;
            root[x] = y;
        }
        return null;
    }

    public static int find(int[] root, int i) {
        while (root[i] != i) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }

    public static int[] findRedundantConnection2(int[][] edges) {
        int []root = new int[2001];//root[i]初始可以是i 也可以是任意 这里用0 因为输入里没有0
        for (int[] edge : edges) {
            int x = find2(root, edge[0]), y = find2(root, edge[1]);
            if (x == y) return edge;
            root[x] = y;
        }
        return null;
    }

    public static int find2(int[] root, int i) {
        while (root[i] != 0) {
            /**
             * 首先上方while里是0 而且这里不能压缩~
             * 因为root的初始值都是0
             * 是不带区分功能
             * 能表示根据0判断是否是根 而不能表示哪个根
             * 不过改改还是可以 只是注意这里根值用的是"上一轮循环"到达root[i] == 0的值
             * 可通过观察i的返回值看到
             */
//            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
}
