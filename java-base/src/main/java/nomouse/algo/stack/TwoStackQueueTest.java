package nomouse.algo.stack;

import java.util.Stack;

/**
 * @author nomouse
 * @date 2021/8/27
 */
public class TwoStackQueueTest {

    public class StackQuery {
        Stack<Integer> inStack = new Stack<>();
        Stack<Integer> outStack = new Stack<>();

        public void push(Integer value) {
            if (outStack.isEmpty()) {
                inStack.push(value);
                return;
            }

            while (!outStack.isEmpty()) {
                inStack.push(outStack.pop());
            }
        }

        public Integer pop() {
            if (inStack.isEmpty()) {

            }

            return null;
        }

    }
}
