package datastructure;

public class LeetCode_208_ImplementTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));;   // returns true
        System.out.println(trie.search("app"));;     // returns false
        System.out.println(trie.startsWith("app"));; // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));// returns true
    }
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    static class Trie {
        class TrieNode{ // 字典树节点
            int num;// 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
            TrieNode[] son;// 所有的儿子节点
            boolean isEnd;// 是不是最后一个节点
            char val;// 节点的值

            TrieNode(){
                this.num = 1;
                this.son = new TrieNode[26];
                this.isEnd = false;
            }
        }

        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if("".equals(word) || 0 == word.length()) return;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int pos = chars[i] - 'a';
                if(null == node.son[pos]){
                    node.son[pos] = new TrieNode();
                    node.son[pos].val = chars[i];
                }else{
                    node.son[pos].num++;
                }
                node = node.son[pos];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if("".equals(word) || 0 == word.length()) return false;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int pos = chars[i] - 'a';
                if(null == node.son[pos])
                    return false;
                node = node.son[pos];
            }

            return node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if("".equals(prefix) || 0 == prefix.length()) return false;
            char[] chars = prefix.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                int pos = chars[i] - 'a';
                if(null == node.son[pos])
                    return false;
                node = node.son[pos];
            }

            return true;
        }
    }
}
