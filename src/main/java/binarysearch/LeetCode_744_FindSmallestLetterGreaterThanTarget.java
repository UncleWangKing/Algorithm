package binarysearch;

public class LeetCode_744_FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        char[] letters = {'a', 'b'};
        char target = 'z';
        System.out.println(nextGreatestLetter(letters, target));
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        if(letters[letters.length - 1] <= target)
            target = 'a' - 1;

        int left = 0, right = letters.length - 1;
        while (left != right){
            int mid = left + (right - left) / 2;
            if(target >= letters[mid])
                left = mid + 1;
            else
                right = mid;
        }
        return letters[left];
    }
}
