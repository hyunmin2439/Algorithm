import java.util.Stack;

class Solution {
    public String solution(String p) {
        if( isCorrect(p) )
            return p;
        
        return recur(p);
    }
    
    private String recur(String p) {
        // 1. 입력이 빈 문자열
        if(p.isEmpty()) return "";
        
        String u, v, tmp = "";
        int idx = 0, cnt[] = new int[2];
        cnt[ p.charAt(idx++) == '(' ? 0 : 1 ]++;
        
        // 2. u, v로 분리
        while( cnt[0] != cnt[1] )
            cnt[ p.charAt(idx++) == '(' ? 0 : 1 ]++;
        
        u = p.substring(0, idx);
        v = p.substring(idx, p.length());
        
        // 3. u가 올바른 괄호 문자열인지 판단
        if( isCorrect(u) )
            return u + recur(v);
        // 4. u가 올바른 괄호 문자열이 아니라면
        else 
            return tmp = '(' + recur(v) + ')' + reverse(u.substring(1, u.length() - 1));
    }
    
    private boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        
        while(idx < p.length()) {
            if(p.charAt(idx) == ')')
                if(stack.isEmpty() || stack.peek() != '(')
                    return false;
                else
                    stack.pop();
            else
                stack.push('(');
        
            idx++;
        }
        
        return true;
    }
    
    private String reverse(String p) {
        char[] tmp = p.toCharArray();
        
        for(int i = 0; i < tmp.length; i++)
            tmp[i] = tmp[i] == '(' ? ')' : '(';
        
        return String.valueOf(tmp);
    }
}