import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        
        if(charArr[0] == ')' || charArr[charArr.length - 1] == '(')
            return false;
        
        for(int i = 0; i < charArr.length; i++) {
            if( !stack.isEmpty() && charArr[i] == ')' && stack.peek() == '(' )
                stack.pop();
            else
                stack.push(charArr[i]);
        }
        
        return stack.isEmpty() ? true : false;
    }
}