package CodingNeetcodeLeetcode.stack;

import java.util.Stack;

public class BalancedParenthesis {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch); // push opening bracket
            } else {
                if (stack.isEmpty()) return false; // no opening to match
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false; // mismatch
                }
            }
        }
        return stack.isEmpty(); // all opened brackets matched
    }

    public static void main(String[] args) {
        String expr1 = "{[()]}";
        String expr2 = "{[(])}";
        System.out.println(expr1 + " → " + isValid(expr1)); // true
        System.out.println(expr2 + " → " + isValid(expr2)); // false
    }
}
