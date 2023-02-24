import java.util.PriorityQueue;

class Solution {
    private final int HASH_SIZE = 200_003;
    
    public int solution(int k, int[] tangerine) {
        PriorityQueue<Integer> pqueue = new PriorityQueue<>( (a, b) -> b - a );
        int[][] hash = new int[HASH_SIZE][2];
        int cnt = 0, answer = 0;
        
        for(int i = 0; i < tangerine.length; i++) {
            addCnt(hash, tangerine[i]);
        }
        
        for(int i = 0; i < HASH_SIZE; i++) {
            pqueue.offer(hash[i][1]);
        }
        
        while(cnt < k) {
            cnt += pqueue.poll();
            answer++;
        }
        
        return answer;
    }
    
    private void addCnt(int[][] hash, int size) {
        int hashNum = size % HASH_SIZE;
        
        while(hash[hashNum][0] != size && hash[hashNum][0] != 0)
            hashNum++;
        
        hash[hashNum][0] = size;
        hash[hashNum][1]++;
    }
}