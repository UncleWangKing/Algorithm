package str.simulation;

public class LeetCode_551_StudentAttendanceRecordI {
    public static void main(String[] args) {
        System.out.println(checkRecord("PPALLP"));//true
        System.out.println(checkRecord("PPALLL"));//false
    }

    public static boolean checkRecord(String s) {
        int Acount = 0;

        for (int i = 0; i < s.length(); i++) {
            if('A' == s.charAt(i)) {
                if (++Acount > 1)
                    return false;
            }
            else if('L' == s.charAt(i)){
                int j = i;
                while (j + 1 < s.length() && 'L' == s.charAt(j + 1))
                    if(j++ - i > 0)
                        return false;
                i = j;
            }
        }

        return true;
    }
}
