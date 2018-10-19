package str.simulation;

public class LeetCode_824_GoatLatin {
    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
    }

    public static String toGoatLatin(String S) {
        StringBuilder sb = new StringBuilder();
        String[] words = S.split(" ");
        char[] map = new char[256];
        map['A'] = map['E'] = map['I'] = map['O'] = map['U'] =  1;
        map['a'] = map['e'] = map['i'] = map['o'] = map['u'] =  1;
        StringBuilder as = new StringBuilder("a");
        for (int i = 0; i < words.length; ++i) {
            // AEIOUaeiou
            if (1 == map[words[i].charAt(0)]) {
                sb.append(words[i]).append("ma");
            } else {
                sb.append(words[i].substring(1)).append(words[i].charAt(0)).append("ma");
            }
            sb.append(as);
            as.append("a");
            if (i != words.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
