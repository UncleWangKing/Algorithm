package str.simulation;

public class LeetCode_125_ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        char pre, last;
        char arr[] = s.toCharArray();
        while(start < end){
            pre = arr[start];
            last = arr[end];
            if(('0' > pre || pre > '9') && ('a' > pre || pre > 'z') && ('A' > pre || pre > 'Z')) {
                start++;
                continue;
            }
            if(('0' > last || last > '9') && ('a' > last || last > 'z') && ('A' > last || last > 'Z')) {
                end--;
                continue;
            }
            if(pre >= 'A' && pre <= 'Z') pre = (char) (pre - 'A' + 'a');
            if(last >= 'A' && last <= 'Z') last = (char) (last - 'A' + 'a');
            if(pre != last)
                return false;
            else {start++;end--;}
        }
        return true;
    }
}
