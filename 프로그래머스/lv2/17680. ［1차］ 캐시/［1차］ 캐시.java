import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) 
            return cities.length * 5;
        
        int answer = 0;
        String str;
        Queue<String> queue = new LinkedList<>();
        
        for(int i = 0; i < cities.length; i++) {
            str = cities[i].toLowerCase();
            
            if( queue.contains(str) ) {
                answer++;
                queue.remove(str);
                queue.offer(str);
            }
            else {
                if(queue.size() >= cacheSize)
                queue.poll();
                queue.offer(str);
                answer += 5;
            }
        }
        
        return answer;
    }
}