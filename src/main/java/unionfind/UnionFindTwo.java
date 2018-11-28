package unionfind;

public class UnionFindTwo {
    int[] father;
    int m, n;
    int count = 0;
    UnionFindTwo(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        father = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int id = i * n + j;
                    father[id] = id;
                    count++;
                }
            }
        }
    }
    public void union(int node1, int node2) {
        int find1 = find(node1);
        int find2 = find(node2);
        if(find1 != find2) {
            father[find1] = find2;//这个写法是x归到y根下
            count--;
        }
    }
    public int find (int node) {
        if (father[node] == node) {
            return node;
        }
        father[node] = find(father[node]);
        return father[node];
    }
}
