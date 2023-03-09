package nomouse.learn.algo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author nomouse
 * @date 2021/8/27
 */
public class PlatStackTest {

    public class PlatStack {
        List<Stack<Integer>> stackList = new ArrayList<>();

        public void push(Integer value) {
            Stack<Integer> currentStack;
            if (stackList.isEmpty()) {
                currentStack = new Stack<Integer>();
                stackList.add(currentStack);
                currentStack.push(value);
                return;
            }
            currentStack = stackList.get(stackList.size() - 1);

            Integer lastValue = currentStack.peek();
            if (lastValue > value) {
                currentStack.push(value);
            } else {
                currentStack = new Stack<Integer>();
                stackList.add(currentStack);
                currentStack.push(value);
            }
        }

    }
}
