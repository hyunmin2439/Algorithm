import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> queue = new LinkedList<>();
        int[] ordered = new int[priorities.length];
        int idx = 1, curr = 0, max = 0;
        
        for(int i = 0; i < priorities.length; i++) {
            queue.offer(i);
        }
        
        while( !queue.isEmpty() ) {
            curr = queue.poll();
                   
            max = priorities[curr];
            for(int i = 0; i < priorities.length; i++) {
                max = Math.max(max, priorities[i]);
            }
            
            if(priorities[curr] >= max) {
                ordered[curr] = idx++;
                priorities[curr] = 0;
            }
            else
                queue.offer(curr);
        }
        
        return ordered[location];
    }
}