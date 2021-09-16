package nomouse.algo.stack;

import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author nomouse
 * @date 2021/8/27
 */
public class MinStackTest {

    public static void main(String[] args) {

    }

    public class MinStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public Integer pop() {
            Integer value = stack.pop();

            minStack.pop();

            return value;
        }

        public void push(Integer value) {
            stack.push(value);

            Integer lastMin = minStack.peek();
            if (lastMin > value) {
                minStack.push(value);
            } else {
                minStack.push(lastMin);
            }
        }

        public Integer getMin() {
            return minStack.peek();
        }
    }
}
