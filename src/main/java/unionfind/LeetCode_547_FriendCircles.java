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

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1)
                    uf.union(i, j);
            }
        }
        return uf.getCount();
    }

    static class UnionFind {
        private int size;
        private int count;
        private int[] root;

        /**
         * @Description    构造并初始化
         * @param          n 规模
         */
        public UnionFind(int n) {
            size = n;
            count = n;
            root = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        /**
         * @MethodName       findFather
         * @Description      查找祖先：压缩路径（迭代版） 效率优于递归
         *                   找到最久远的祖先时把它的子孙直接连接到它上面，可以保证树最多只有两层
         * @param            x 子孙节点
         * @return           int 根节点
         */
        private int find(int x) {
            if (root[x] == x) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        /**
         * @MethodName       union
         * @Description      合并子树
         * @param            x 子树根节点
         * @param            y 子树根节点
         */
        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                root[fy] = fx;
                count--;
            }
        }

        /**
         * @MethodName       getCount
         * @Description      获取连通分支数
         * @return           int 连通分支数
         */
        public int getCount() {
            return count;
        }
    }
}
