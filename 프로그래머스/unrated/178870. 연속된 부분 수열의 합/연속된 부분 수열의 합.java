class Solution {
    public int[] solution(int[] sequence, int k) {
        int s = 0, e = 0, sum = sequence[0], len = sequence.length, fs = 0, fe = 0, fLen = len + 1;
        
        while(true) {
            if(s < e && sum > k) {
                sum -= sequence[s++];
            }
            else {
                if(sum == k && fLen > e - s + 1) {
                    fLen = e - s + 1;
                    fs = s;
                    fe = e;
                }
                
                if(e + 1 == len) break;
                
                sum += sequence[++e];
            }
        }
        
        return new int[] {fs, fe};
    }
}