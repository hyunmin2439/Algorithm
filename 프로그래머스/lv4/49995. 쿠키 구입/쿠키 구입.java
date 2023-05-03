class Solution {
    public int solution(int[] cookie) {
        int[] acc = new int[cookie.length];
        int a, b, l, r, answer = 0;
        
        acc[0] = cookie[0];
        for(int i = 1; i < cookie.length; i++) {
            acc[i] = acc[i - 1] + cookie[i];
        }
        
        for(int m = cookie.length - 2; m >= 0; m--) {
            l = 0;
            r = cookie.length - 1;
            
            a = acc[m];
            b = acc[r] - acc[m];
            
            while(l <= m && m < r) {
                if(Math.max(a, b) < answer) break;
                
                if(a == b) {
                    answer = Math.max(answer, a);
                    break;
                }
                
                if(a > b)
                    a -= cookie[l++];
                else
                    b -= cookie[r--];
            }
        }
        
        return answer;
    }
}