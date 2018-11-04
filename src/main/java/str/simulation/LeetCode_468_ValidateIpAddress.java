package str.simulation;

public class LeetCode_468_ValidateIpAddress {
    public static void main(String[] args) {
        System.out.println(validIPAddress("172.16.254.1"));
    }

    public static String validIPAddress(String IP) {
        if (IP == null || IP.length() <= 0) {
            return "Neither";
        }
        if (isIPV4(IP)) {
            return "IPv4";
        }
        if (isIPV6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }
    private static boolean isIPV4(String IP) {
        if (IP.charAt(IP.length() - 1) == '.') {
            return false;
        }
        String[] nums = IP.split("\\.");
        if (nums.length != 4) {
            return false;
        }
        for (String val : nums) {
            // 注意：192.0.0.1这种情况。当子串length>1，这是就不能以0开头。
            if ("".equals(val) || val.length() > 3 || (val.length() > 1 && val.charAt(0) == '0')) {
                return false;
            }
            for (int i = 0; i < val.length(); i++) {
                if (!(val.charAt(i) >= '0' && val.charAt(i) <= '9')) {
                    return false;
                }
            }
            if (Integer.parseInt(val) > 255) {
                return false;
            }
        }
        return true;
    }
    private static boolean isIPV6(String IP) {
        if (IP.charAt(IP.length() - 1) == ':') {
            return false;
        }
        String[] nums = IP.toLowerCase().split("\\:");
        if (nums.length != 8) {
            return false;
        }
        for (String val : nums) {
            if (val.length() <= 0 || val.length() > 4) {
                return false;
            }
            for (int i = 0; i < val.length(); i++) {
                char c = val.charAt(i);
                // 16进制的字母组合的IPV6地址
                if (c < '0' || ( c > '9' && c < 'a') || c > 'f') {
                    return false;
                }
            }
        }
        return true;
    }
}
