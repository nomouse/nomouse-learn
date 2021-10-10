package nomouse.algo.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 * *
 * @author nomouse
 * @date 2021/8/13
 */
public class DictSorted {

    public boolean isAlienSorted(String[] words, String order) {

        Map<Character,Integer> orderMap = getCharOrder(order);
        int size = words.length;
        for(int i = 0; i<20; i++){
            Character[] charArray = new Character[size];
            for(int j=0;j<size;j++){
               String word = words[j];
               charArray[j] = getChar(word,i);
            }

            Character curr = charArray[0];
            for(int n=1;n<size;n++){
                Character next = charArray[n];
                if(!large(curr,next,orderMap)){
                    return false;
                }
                curr = next;
            }
        }

        return true;
    }


    private Map<Character,Integer> getCharOrder(String order){
        Map<Character,Integer> map = new HashMap<Character, Integer>(32);
        char[] chars = order.toCharArray();
        for(int i=0;i<chars.length;i++){
            map.put(chars[i],i);
        }
        return map;
    }


    private Character getChar(String word,int index){
        if(word.length()<=index){
            return null;
        }else {
            return word.charAt(index);
        }
    }

    private boolean large(Character c1, Character c2, Map<Character,Integer> map){
        if( c2 == null ){
            return true;
        }else if(c1 == null){
            return false;
        }else {
           return  map.get(c1) >=map.get(c2);
        }

    }


    public static void main(String[] args) {

        String[] words =new String[]{"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";

        System.out.println(new DictSorted().isAlienSorted(words,order));
    }
}
