class Solution {
    public int solution(int n) {
        int answer = 1, num = 0, start = 1, len = (int)Math.sqrt(n), maxNum = n / 2 + 1;
        
        while( (1 + len) * len / 2 < n ) len++;        
        
        while(len > 1) {
            num = 0;
            
            for(int i = 0; i < len; i++) {
                num += start + i;
            }
            
            if(num == n) {
                answer++;
                start++;
                len--;
            }
            else if(num < n)
                start++;
            else 
                len--;
        }
        
        return answer;
    }
}