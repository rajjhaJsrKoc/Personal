package CodingNeetcodeLeetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args){
        int n = 3;
        List result = new ArrayList<>();
        generateParenthesis(0,0,new StringBuffer(),n,result);
        System.out.println(result);

    }

    private static void generateParenthesis(int open, int close, StringBuffer stringBuffer, int n, List result) {

        if (stringBuffer.length() == 2*n){
            result.add(stringBuffer.toString());
            return;
        }
        if(open<n){
            stringBuffer.append('(');
            generateParenthesis(open+1,close,stringBuffer,n,result);
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        if(close<open){
            stringBuffer.append(')');
            generateParenthesis(open,close+1,stringBuffer,n,result);
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }

    }
}
