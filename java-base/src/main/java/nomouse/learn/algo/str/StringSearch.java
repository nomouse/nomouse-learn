package nomouse.learn.algo.str;

/**
 * @author nomouse
 * @date 2021/9/24
 */
public class StringSearch {

    /**
     * dsddsfsgk
     * [-1,0,1]
     * @param str
     * @return
     */
    public int[] next(String str) {
        char[] chars = str.toCharArray();
        int[] next = new int[chars.length];
        int j = -1;
        next[0] = j;
        for (int i = 1; i < chars.length; i++) {
            while (j >= 0 && chars[i] != chars[j + 1]) {
                j = next[j];
            }

            if (chars[i] == chars[j + 1]) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}
