package nomouse.midware.limit;

import java.util.concurrent.atomic.LongAdder;

/**
 * 固定窗口计数
 *
 * @author nomouse
 * @date 2021/8/20
 */
public class FixedWindowThrottler {

    private long windowInTime;

    /**
     * 当前时间
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
