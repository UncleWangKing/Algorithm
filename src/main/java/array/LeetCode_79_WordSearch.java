package array;

public class LeetCode_79_WordSearch {
    public static void main(String[] args) {
        char list[][] = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(exist2(list, "ABCCED"));
    }

    /**
     * 简单深搜
     */
    public static boolean exist(char[][] board, String word) {
        if (0 == board.length || 0 == board[0].length) return false;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(board, word, 0, i, j, visited)) return true;
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, String word, int idx, int i, int j, boolean[][] visited) {
        if (idx == word.length()) return true;
        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || board[i][j] != word.charAt(idx)) return false;
        visited[i][j] = true;
        boolean res = dfs(board, word, idx + 1, i - 1, j, visited)
                || dfs(board, word, idx + 1, i + 1, j, visited)
                || dfs(board, word, idx + 1, i, j - 1, visited)
                || dfs(board, word, idx + 1, i, j + 1, visited);
        visited[i][j] = false;
        return res;
    }

    /**
     * 原地缓存数据深搜
     */
    public static boolean exist2(char[][] board, String word) {
        if (0 == board.length || 0 == board[0].length) return false;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs2(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    public static boolean dfs2(char[][] board, String word, int idx, int i, int j) {
        if (idx == word.length()) return true;
        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != word.charAt(idx)) return false;
        board[i][j] ^= 256;
        boolean res = dfs2(board, word, idx + 1, i - 1, j)
                || dfs2(board, word, idx + 1, i + 1, j)
                || dfs2(board, word, idx + 1, i, j - 1)
                || dfs2(board, word, idx + 1, i, j + 1);
        board[i][j] ^= 256;
        return res;
    }
}
