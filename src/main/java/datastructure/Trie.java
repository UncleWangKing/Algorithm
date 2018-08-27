package datastructure;

class Trie {
    boolean end = false;
	Trie[] children = new Trie[26];

	public void insert(String word) {
		this.add(this, word.toCharArray(), 0);
	}

	public void add(Trie t, char[] chars, int index) {
		if (chars.length > index) {
			if (t.children[chars[index] - 'a'] == null) {
				t.children[chars[index] - 'a'] = new Trie();
			}
			if (chars.length - 1 == index)
				t.children[chars[index] - 'a'].end = true;
			t.add(t.children[chars[index] - 'a'], chars, index + 1);
		}
	}

	public boolean search(String word) {
		Trie node = this;
		for (char c : word.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				return false;
			}
			node = node.children[c - 'a'];
		}
		return node.end;
	}

	public boolean startsWith(String prefix) {
		Trie node = this;
		for (char c : prefix.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				return false;
			}
			node = node.children[c - 'a'];
		}
		return true;
	}
}