package nomouse.biz.util.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 专用线程池
 *
 * @author wuchunhao on 2023/2/20
 */
public class CustomThreadPool {

    private final static ThreadPoolExecutor executor;

    static {
        executor = new ThreadPoolExecutor(8, 8,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000),
            new BizThreadFactory("UserTarget"), new AbortPolicy());
    }

    public static ThreadPoolExecutor get() {
        return executor;
    }

    public static Future<?> submit(Runnable runnable) {
        return executor.submit(runnable);
    }

    public static <T> Future<T> submit(Callable<T> runnable) {
        return executor.submit(runnable);
    }
}
