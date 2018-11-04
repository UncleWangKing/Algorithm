package str.simulation;

public class LeetCode_165_CompareVersionNumbers {
    public static void main(String[] args) {
//        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(compareVersion("1", "0"));
    }

    public static int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("[.]");
        String[] arr2 = version2.split("[.]");

        int length = Math.max(arr1.length, arr2.length);

        for(int i = 0; i < length; i++) {
            Integer a1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            Integer a2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;

            int compare = a1.compareTo(a2);
            if(0 != compare) return compare;
        }
        return 0;
    }
}
