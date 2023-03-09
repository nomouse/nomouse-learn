package nomouse.learn.algo.str;

/**
 * 最长公共前缀
 *
 * @author nomouse
 * @date 2021/8/12
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strArray) {

        StringBuilder sb = new StringBuilder();
        char curr = 0;
        for (int i = 0; i < 200; i++) {
            for (String str : strArray) {
                if (str == null || str.length() <= i) {
                    return sb.toString();
                }
                if (curr == 0) {
                    curr = str.charAt(i);
                } else if (curr != str.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(curr);
            curr = 0;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {"123", "124256", "1235"}));
    }
}
