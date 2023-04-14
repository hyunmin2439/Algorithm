class Solution {
    public int solution(String s) {
        int idx = 0, answer = 0, cnt[] = new int[2];
        char c;
        
        for(int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            cnt[0] = 1;
            cnt[1] = 0;
            
            while(++i < s.length()) {
                if(c == s.charAt(i)) 
                    cnt[0]++;
                else 
                    cnt[1]++;
                
                if(cnt[0] == cnt[1]) break;
            }
            
            answer++;
        }
        
        return answer;
    }
}