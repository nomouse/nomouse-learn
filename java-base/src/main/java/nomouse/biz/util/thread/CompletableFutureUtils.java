package nomouse.biz.util.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * @author wuchunhao on 2023/2/22
 */
public class CompletableFutureUtils {

    /**
     * @param t
     * @param future
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> completeOnTimeout(T t, CompletableFuture<T> future,
        long timeout, TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return future.applyToEither(timeoutFuture, Function.identity())
            .exceptionally((throwable) -> t);
    }

    /**
     * @param future
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> orTimeout(CompletableFuture<T> future, long timeout,
        TimeUnit unit) {
        final CompletableFuture<T> timeoutFuture = timeoutAfter(timeout, unit);
        return future.applyToEither(timeoutFuture, Function.identity());
    }


    /**
     * 哪个先完成就apply哪一个结果 这是一个关键的API,exceptionally出现异常后返回默认值
     *
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<>();
        // timeout 时间后 抛出TimeoutException 类似于sentinel / watcher
        Delayer.delay(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    /**
     * Singleton delay scheduler, used only for starting and * cancelling tasks.
     */
    static final class Delayer {

        static ScheduledFuture<?> delay(Runnable command, long delay,
            TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static final ScheduledThreadPoolExecutor delayer;

        // 注意，这里使用一个线程就可以搞定 因为这个线程并不真的执行请求 而是仅仅抛出一个异常
        static {
            (delayer = new ScheduledThreadPoolExecutor(
                1, new DaemonThreadFactory())).
                setRemoveOnCancelPolicy(true);
        }
    }


}
