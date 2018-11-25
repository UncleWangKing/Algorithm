package unionfind;

public class LeetCode_547_FriendCircles {
    public static void main(String[] args) {
        int[][] M = {
            {1,1,0},
            {1,1,0},
            {0,0,1}
        };
        System.out.println(findCircleNum(M));
    }

    public static int findCircleNum(int[][] M) {
        int root[] = new int[M.length];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
        int res = M.length;//如上方root初始化一般 先假设每个不同根
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if(1 == M[i][j]){
                    int p1 = getRoot(root, i);
                    int p2 = getRoot(root, j);
                    if(p1 != p2){//不同根
                        res--;
                        root[p2] = p1;//计数完后 归入一类
                    }
                }
            }
        }
        return res;
    }

    public static int getRoot(int[] root, int i){
        while (i != root[i]){
            root[i] = root[root[i]];//压缩路径操作
            i = root[i];
        }

        return i;
    }

    public static int findCircleNum2(int[][] M) {
        int n = M.length;
        if (n == 0) {
            return 0;
        }

        UnionFindOne uf = new UnionFindOne(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1)
                    uf.union(i, j);
            }
        }
        return uf.getCount();
    }
}
