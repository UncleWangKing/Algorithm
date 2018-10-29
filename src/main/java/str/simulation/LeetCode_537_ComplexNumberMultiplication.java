package str.simulation;

public class LeetCode_537_ComplexNumberMultiplication {
    public static void main(String[] args) {
        System.out.println(complexNumberMultiply("1+1i", "1+1i"));
    }

    public static String complexNumberMultiply(String a, String b) {
        int[] aa = new int[2];
        int[] bb = new int[2];

        String[] aas;
        String[] bbs;

        int shi, xu;
        String output;

        aas = a.split("[+]");
        bbs = b.split("[+]");

        aa[0]=Integer.parseInt(aas[0]);
        aa[1]=Integer.parseInt(aas[1].split("i")[0]);
        bb[0]=Integer.parseInt(bbs[0]);
        bb[1]=Integer.parseInt(bbs[1].split("i")[0]);

        shi = aa[0] * bb[0] + (-1) * aa[1] * bb[1];
        xu = aa[0] * bb[1] + aa[1] * bb[0];

        output = shi+"+"+xu+"i";

        return output;
    }
}
