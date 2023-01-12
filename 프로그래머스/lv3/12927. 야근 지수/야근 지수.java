import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pqueue = new PriorityQueue<>((a, b) -> b - a);
        long answer = 0;
        int quot = 2, len = works.length;
        
        for(int i = 0; i < len; i++) {
            answer += works[i];
            pqueue.offer(works[i]);
        }
        
        if(answer - n <= 0) return 0;
        
        while(quot > 1) {
            quot = n / len;
            n -= quot;
            pqueue.offer( (pqueue.poll() - quot) );
        }
        
        for(int i = 0; i < n; i++) {
            pqueue.offer( (pqueue.poll() - 1) );
        }
        
        answer = 0;
        for(int i = 0; i < len; i++) {
            answer += (long)Math.pow(pqueue.poll(), 2);
        }
        
        return answer;
    }
}