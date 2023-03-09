package nomouse.learn.algo.str;

/**
 * @author nomouse
 * @date <p> 2021/9/30 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class Manacher {

    // abbcba

    public static String search(String str) {
        int size = str.length();
        char[] manacher = new char[size * 2 + 1];

        manacher[0] = '^';
        for (int i = 0; i < size; i++) {
            manacher[i * 2 + 1] = str.charAt(i);
            manacher[(i + 1) * 2] = '#';
        }
        manacher[size * 2] = '$';

        int max = 0;
        int maxI = 0;
        for (int i = 1; i < size * 2; i++) {
            int count = 0;
            int left = i - 1;
            int right = i + 1;
            while (left > 0 && right < size * 2 &&
                manacher[left] == manacher[right]) {
                left--;
                right++;

                count++;
            }

            if (count > max) {
                max = count;
                maxI = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = maxI - max; j < max + maxI; j++) {
            if (manacher[j] != '#') {
                sb.append(manacher[j]);
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String source = "abbcba";

        String sub = search(source);

        System.out.println(sub);
    }
}
