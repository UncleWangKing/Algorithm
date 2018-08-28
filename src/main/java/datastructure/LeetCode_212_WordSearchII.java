package datastructure;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_212_WordSearchII {
    public static void main(String[] args) {
        char [][] list = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};
//        char [][] list = {{'a'}};
        String[] words = {"oath","pea","eat","rain"};
//        String[] words = {"a"};
        System.out.println(findWords(list, words));
    }
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> resultList = new ArrayList<>();
        if (0 == board.length || 0 == board[0].length) return resultList;

        Trie root = new Trie();
        for (int i = 0; i < words.length; i++) {
            root.insert(words[i]);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(null != root.children[board[i][j] - 'a']){
                    dfs(board, root.children[board[i][j] - 'a'], i, j, visited, resultList);
                }
            }
        }

        return resultList;
    }

    /**
     * 和79一样 只是在比较搜索结果时 利用了字典树 防止words数组过大造成的重复比较过多
     */
    public static void dfs(char[][] board, Trie node, int i, int j, boolean[][] visited, List<String> resulList) {
        if(null != node.str) {//或者end判断是不是单词
            resulList.add(node.str);
            node.str = null;//防重复操作
        }
        int direction[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true;
        for (int [] d : direction) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && !visited[x][y] && null != node.children[board[x][y] - 'a']) {
                dfs(board, node.children[board[x][y] - 'a'], x, y, visited, resulList);
            }
        }
        visited[i][j] = false;
    }

    static class Trie {
        String str;
        Trie[] children = new Trie[26];

        public void insert(String word) {
            this.add(this, word.toCharArray(), 0);
        }

        public void add(Trie t, char[] chars, int index) {
            if (chars.length > index) {
                if (t.children[chars[index] - 'a'] == null)
                    t.children[chars[index] - 'a'] = new Trie();
                if (chars.length - 1 == index)
                    t.children[chars[index] - 'a'].str = String.valueOf(chars);
                t.add(t.children[chars[index] - 'a'], chars, index + 1);
            }
        }
    }
}
