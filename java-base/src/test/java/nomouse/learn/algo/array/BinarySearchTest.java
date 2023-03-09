package nomouse.learn.algo.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author nomouse
 * @date 2021/9/20
 */
public class BinarySearchTest {

    @Test
    public void test() {
        BinarySearch search = new BinarySearch();

        Assert.assertEquals(-1,
            search.binarySearch(null, -1));

        Assert.assertEquals(-1,
            search.binarySearch(new int[] {}, -1));

        Assert.assertEquals(4,
            search.binarySearch(new int[] {-1, 0, 3, 5, 9, 12}, 9));

        Assert.assertEquals(-1,
            search.binarySearch(new int[] {-1, 0, 3, 5, 9, 12}, 2));

    }
}
