package nomouse.learn.base.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuchunhao on 2023/3/24
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();
        map.put(1L,2L);
    }

}
