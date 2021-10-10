package nomouse.algo.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nomouse
 * @date 2021/9/21
 */
public class Anagram {

    public boolean isAnagram(String str1, String str2) {
        HashMap<Character, Integer> countMap1 = getMap(str1);
        HashMap<Character, Integer> countMap2 = getMap(str2);

        if (countMap1.size() != countMap2.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : countMap1.entrySet()) {
            Integer count1 = entry.getValue();
            Integer count2 = countMap2.get(entry.getKey());
            if (!count1.equals(count2)) {
                return false;
            }
        }

        return true;
    }

    private HashMap<Character, Integer> getMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>(26);

        char[] chars = str.toCharArray();
        for (char c : chars) {
            Integer count = map.get(c);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            map.put(c, count);
        }

        return map;
    }

    public static void main(String[] args) {
        String str1 = "anagram";
        String str2 = "nagaram1";

        System.out.println(new Anagram().isAnagram(str1, str2));
    }
}
