import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> pqueue = new PriorityQueue<>( (a, b) -> {
            if(a[1] != b[1])
                return a[1] - b[1];
            
            return a[0] - b[0];
        });
        
        int idx = 1, time = 0, answer = 0, job[];
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        time = jobs[0][0];
        pqueue.offer(jobs[0]);
        while(idx < jobs.length && jobs[idx][0] <= time)
            pqueue.offer(jobs[idx++]);
        
        while(!pqueue.isEmpty()) { 
            job = pqueue.poll();
            time += job[1];
            answer += time - job[0];
            
            while(idx < jobs.length && jobs[idx][0] <= time)
                pqueue.offer(jobs[idx++]);
            
            if(idx < jobs.length && pqueue.isEmpty()) {
                time = jobs[idx][0];
                pqueue.offer(jobs[idx++]);
            }
        }
        
        return answer / jobs.length;
    }
}