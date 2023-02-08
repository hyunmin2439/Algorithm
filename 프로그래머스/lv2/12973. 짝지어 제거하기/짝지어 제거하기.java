import java.util.*;

class Solution
{
    public int solution(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int idx = 0, len = s.length();
        
        if((s.length() % 2 & 1) == 1) return 0;
        
        stack.push(s.charAt(idx++));
        while(idx < len) {
            if(!stack.isEmpty() && stack.peek() == s.charAt(idx)) {
                stack.pop();
                idx++;
            }
            else
                stack.push(s.charAt(idx++));
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}