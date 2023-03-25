package nomouse.learn.base.concurrent;


import java.text.SimpleDateFormat;

/**
 * @author wuchunhao on 2023/3/24
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

        threadLocal.set(null);
    }
}
