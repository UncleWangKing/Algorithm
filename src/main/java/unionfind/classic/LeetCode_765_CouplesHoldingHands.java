package unionfind.classic;

public class LeetCode_765_CouplesHoldingHands {
    public static void main(String[] args) {
        int row[] = {0, 2, 1, 3};
        System.out.println(minSwapsCouples(row));
    }

    /**
     * 贪心
     */
    public static int minSwapsCouples(int[] row) {
        int res = 0;
        for (int i = 0; i < row.length; i += 2) {
            if (row[i + 1] == (row[i] ^ 1)) continue;
            ++res;
            for (int j = i + 1; j < row.length; ++j) {
                if (row[j] == (row[i] ^ 1)) {
                    row[j] = row[i + 1];
                    row[i + 1] = row[i] ^ 1;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * UF 建立在贪心的基础上
     */
    public static int minSwapsCouples2(int[] row) {
        int cnt = row.length / 2;
        int[] root = new int[row.length];
        for (int i = 0; i < row.length; ++i) root[i] = i;
        for (int i = 0; i < row.length; i += 2) {
            int x = find(root, row[i] / 2);
            int y = find(root, row[i + 1] / 2);
            if (x != y) {
                root[x] = y;
                --cnt;
            }
        }
        return row.length / 2 - cnt;
    }

    public static int find(int[] root, int i) {
        return (i == root[i]) ? i : find(root, root[i]);
    }
}
