class Solution {
    public int solution(String s) {
        char[] stack = new char[1000];
        char[] str = s.toCharArray();
        int answer = 0;
        
        for(int i = 0; i < str.length; i++) {
            if( isCorrect(stack, str, str.length, i) )
                answer++;
        }
        
        return answer;
    }
    
    private boolean isCorrect(char[] stack, char[] str, int len, int start) {
        int top = 0, idx = start;
        
        if(str[idx] == ')' || str[idx] == ']' || str[idx] == '}') return false;
            
        stack[top] = str[idx];
        idx = (idx + 1) % len;
        
        while(idx != start) {
            switch(str[idx]) {
                case ')' :
                    if(top == -1 || stack[top--] != '(') return false;
                    break;
                case ']' :
                    if(top == -1 || stack[top--] != '[') return false;
                    break;
                case '}' : 
                    if(top == -1 || stack[top--] != '{') return false;
                    break;
                default :
                    stack[++top] = str[idx];
                    break;
            }
            
            idx = (idx + 1) % len;
        }
        
        return top == -1 ? true : false;
    }
}