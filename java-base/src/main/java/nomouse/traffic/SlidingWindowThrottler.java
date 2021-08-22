package nomouse.traffic;

import java.util.concurrent.atomic.LongAdder;

/**
 * 滑动窗口计数
 *
 * @author nomouse
 * @date 2021/8/20
 */
public class SlidingWindowThrottler {

    /**
     * 窗口分片
     */
    private int slice;

    /**
     * 时间窗口
     */
    private long windowInTime;

    /**
     * 上次统计时间
     */
    private volatile long lastTime = System.currentTimeMillis();

    /**
     * 阈值
     */
    private int threshold;

    /**
     * 计数器
     */
    private LongAdder count = new LongAdder();

    public boolean tryAcquire(String key) {
        // 判断是否当前时间
        long now = System.currentTimeMillis();
        if (now > lastTime + windowInTime) {
            lastTime = now;
            count = new LongAdder();
        }

        if (count.longValue() < threshold) {
            count.increment();
            return true;
        } else {
            return false;
        }

    }

}
