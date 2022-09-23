class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0, min = 0, max = getMax(stones), mid = 0;
        
        while(min <= max) {
            mid = (min + max) / 2;
            
            if( isCanCrossBridge(stones, k, mid) ) {
                answer = mid;
                min = mid + 1;
            }
            else
                max = mid - 1;
        }
        
        return answer;
    }
    
    private int getMax(int[] stones) {
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] != 0)
                max = Math.max(stones[i], max);
        }
        
        return max;
    }
    
    private boolean isCanCrossBridge(int[] stones, int k, int num) {
        int cnt = 0; // 못 건너는 다리 수
        
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] - num < 0)
                cnt++;
            else
                cnt = 0;
            
            if(cnt >= k) return false;
        }
        
        return true;
    }
}