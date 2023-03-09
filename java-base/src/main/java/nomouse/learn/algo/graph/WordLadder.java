package nomouse.learn.algo.graph;

import java.util.*;

/**
 * @author nomouse
 * @date 2021/11/2 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。 序列中最后一个单词是 endWord 。 每次转换只能改变一个字母。 转换过程中的中间单词必须是字典 wordList 中的单词。 给你两个单词 beginWord 和 endWord
 * 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"] 输出：5 解释：一个最短转换序列是 "hit" ->
 * "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"] 输出：0 解释：endWord "cog"
 * 不在字典中，所以无法进行转换。
 */
public class WordLadder {

    public List<String> ladder(String beginWord, String endWord, String[] wordList) {
        List<String> res = new ArrayList<>();
        Queue<String> bfs = new LinkedList<>();
        bfs.offer(beginWord);
        Map<String, Integer> map = new HashMap<>(wordList.length);
        map.put(beginWord, 1);

        while (!bfs.isEmpty()) {
            String word = bfs.poll();

            // 搜索下一个
            int path = map.get(word);

            // 判断下一个是否到结尾字符
        }

        return res;
    }



    public static void main(String[] args) {
        String[] array = new String[] {
            "hot", "dot", "dog", "lot", "log", "cog"
        };

        System.out.println(new WordLadder()
            .ladder("hot", "cog", array));
    }

}
