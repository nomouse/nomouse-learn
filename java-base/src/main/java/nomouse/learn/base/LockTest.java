package nomouse.learn.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nomouse
 * @date 2021/7/29
 */
public class LockTest {

    public void lockTest() {
        Lock lock = new ReentrantLock();

        lock.tryLock();

        lock.unlock();
    }
}
