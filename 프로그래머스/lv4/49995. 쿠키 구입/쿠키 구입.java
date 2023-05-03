class Solution {
    public int solution(int[] cookie) {
        int a = 0, b = 0, l, r, answer = 0;
        int len = (int) Math.pow(2, Math.ceil( Math.log(cookie.length) / Math.log(2) ) );
        int[] segment = initSegmentTree(cookie, len);
        
        for(int m = cookie.length - 2; m >= 0; m--) {
            l = 0;
            r = cookie.length - 1;
            
            a = getSum(segment, len, l, m + 1);
            b = getSum(segment, len, m + 1, r + 1);
            
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
    
    private int[] initSegmentTree(int[] cookie, int len) {
        int[] segment = new int[len * 2];
        
        for(int i = 0; i < cookie.length; i++) {
            segment[i + len] = cookie[i];
        }
        
        for(int i = len - 1; i >= 1; i--) {
            segment[i] = segment[i << 1] + segment[i << 1 | 1];
        }
        
        return segment;
    }
    
    private int getSum(int[] segment, int len, int l, int r) {
        int sum = 0;
        
        for(l += len, r += len; l < r; l >>= 1, r >>= 1) {
            if((l & 1) == 1)
                sum += segment[l++];
            if((r & 1) == 1)
                sum += segment[--r];
        }
        
        return sum;
    }
}