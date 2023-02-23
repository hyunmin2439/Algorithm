import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();
        int answer = 0;
        
        for(int i = 0; i < scoville.length; i++) {
            pqueue.add(scoville[i]);
        }
        
        while( pqueue.size() > 1) {
            if(pqueue.peek() >= K) break;
            pqueue.offer( pqueue.poll() + pqueue.poll() * 2 );
            answer++;
        }
        
        return pqueue.peek() >= K ? answer : -1;
    }
}