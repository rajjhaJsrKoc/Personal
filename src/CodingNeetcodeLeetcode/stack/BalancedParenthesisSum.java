package CodingNeetcodeLeetcode.stack;

import java.util.Stack;

public class BalancedParenthesisSum {
    public static void main(String[] args){
    String code = "Hello(13{12}5H6)";
    StringBuilder text = new StringBuilder();
    Stack<Integer> stack = new Stack<>();
    for (char ch : code.toCharArray()){
        if (Character.isLetter(ch)){
            text.append(Character.toString(ch));
        }// (1
        else if (Character.isDigit(ch)){
            stack.push(ch -'0');
            // stack has value 1 3
        } else if (ch == ')'|| ch == '}') {
            int value = 0;
            //1
            while (!stack.empty()){
                value = value+ stack.pop();
            }
            stack.push(value);
        }
    }
        System.out.println(text.toString() +stack.peek());
}
}
