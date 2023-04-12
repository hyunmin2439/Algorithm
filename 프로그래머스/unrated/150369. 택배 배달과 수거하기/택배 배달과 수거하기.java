class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int dTop = n - 1, pTop = n - 1, cnt;
        long answer = 0;
        
        while(dTop >= 0 && deliveries[dTop] == 0) dTop--;
        
        while(pTop >= 0 && pickups[pTop] == 0) pTop--;
        
        while(dTop >= 0 || pTop >= 0) {
            answer += ((dTop > pTop ? dTop : pTop) + 1) * 2;
            
            cnt = 0;
            while(dTop >= 0 && cnt <= cap) {
                if(deliveries[dTop] + cnt <= cap) {
                    cnt += deliveries[dTop];
                    dTop--;
                }
                else {
                    deliveries[dTop] -= cap - cnt;
                    cnt = cap + 1;
                }
            }
            
            cnt = 0;
            while(pTop >= 0 && cnt <= cap) {
                if(pickups[pTop] + cnt <= cap) {
                    cnt += pickups[pTop];
                    pTop--;
                }
                else {
                    pickups[pTop] -= cap - cnt;
                    cnt = cap + 1;
                }
            }
        }
        
        return answer;
    }
}