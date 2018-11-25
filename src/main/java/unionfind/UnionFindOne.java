package unionfind;

public class UnionFindOne {
    private int size;
    private int count;
    private int[] root;

    /**
     * @Description    构造并初始化
     * @param          n 规模
     */
    public UnionFindOne(int n) {
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
