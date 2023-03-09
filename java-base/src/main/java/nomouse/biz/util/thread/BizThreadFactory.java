package nomouse.biz.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wuchunhao on 2023/2/20
 */
public class BizThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程组名称，在jstack问题排查时，非常有帮助
    BizThreadFactory(String feature) {
        namePrefix = "BizThread-" + feature + "-Worker";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        return thread;
    }
}


