class Solution {
    public int solution(int n, int[] stations, int w) {
        int left = 0, right = 0, range = 1 + 2 * w, answer = 0;
        
        left = 1;
        right = stations[0] - w - 1;
        
        if(left <= right)
            answer += (int)Math.ceil((double)(right - left + 1) / range);
        
        for(int i = 1; i < stations.length; i++) {
            left = stations[i - 1] + w + 1;
            right = stations[i] - w - 1;
            
            answer += (int)Math.ceil((double)(right - left + 1) / range);
        }
        
        left = stations[stations.length - 1] + w + 1;
        right = n;
        
        if(left <= right)
            answer += (int)Math.ceil((double)(right - left + 1) / range);
        
        return answer;
    }
}