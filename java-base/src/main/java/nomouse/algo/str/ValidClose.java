package nomouse.algo.str;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 闭合括号
 *
 * @author nomouse
 * @date 2021/8/12
 */
public class ValidClose {

    public static boolean validClose(String str) {

        Set<Character> leftSet = new HashSet<Character>();
        leftSet.add('{');
        leftSet.add('[');
        leftSet.add('(');

        Set<Character> rightSet = new HashSet<Character>();
        rightSet.add('}');
        rightSet.add(']');
        rightSet.add(')');

        Stack<Character> stack = new Stack<Character>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (leftSet.contains(c)) {
                stack.push(c);
                continue;
            }
            Character popC = stack.peek();
            if (!leftSet.contains(popC)) {
                return false;
            }

            if ((popC == '(' && c == ')')
                || (popC == '[' && c == ']')
                || (popC == '{' && c == '}')) {
                stack.pop();
                continue;
            } else {
                return false;
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(validClose("({}){}"));
    }

    public void test1() {

    }

}



