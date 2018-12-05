package binarysearch;

public class LeetCode_374_GuessNumberHigherOrLower {
    public static void main(String[] args) {
        System.out.println(guessNumber(10));
    }

    public static int guessNumber(int n) {
        int left = 1, right = n;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(0 == guess(mid))
                return mid;
            else if(1 == guess(mid))
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }

    public static int guess(int num){
        if(10 == num)
            return 0;
        else if(10 > num)
            return 1;
        else
            return -1;

    }
}
