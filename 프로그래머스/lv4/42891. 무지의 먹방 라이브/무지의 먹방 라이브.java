import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int curr = 0, prev = 0, diff = 0;
        long numOfCycle = 0;
        LinkedList<Food> queue = new LinkedList<>();
        
        for(int i = 0; i < food_times.length; i++) {
            queue.offer(new Food(food_times[i], i + 1));
        }
        
        sort(queue, false);

        while( !queue.isEmpty() && k > 0 ) {
            curr = queue.peek().cnt;
            diff = curr - prev;
            numOfCycle = (long)diff * queue.size();
            
            if( numOfCycle > k ) break;
            
            k -= numOfCycle;
            prev = curr;
            queue.poll();
        }
        
        // 다 먹은 음식 처리
        while( !queue.isEmpty() && queue.peek().cnt == prev ) queue.poll();
        
        // 음식 다먹었으면 -1
        if( queue.isEmpty() ) return -1;
        
        // 번호 순으로 다시 정렬
        sort(queue, true);
        
        return queue.get((int)(k % queue.size())).idx;
    }
    
    private void sort(LinkedList<Food> queue, boolean isOrderedIdx) {
        // 번호 순 정렬
        if(isOrderedIdx) {
            Collections.sort(queue, new Comparator<Food>(){
                @Override
                public int compare(Food a, Food b) {
                    return a.idx - b.idx;
                }
            });
        }
        // 남은 음식 순으로 정렬
        else {
            Collections.sort(queue, new Comparator<Food>(){
                @Override
                public int compare(Food a, Food b) {
                    return a.cnt - b.cnt;
                }
            });
        } 
    }
    
    private class Food {
        int cnt, idx;
        
        public Food(int cnt, int idx) {
            this.cnt = cnt;
            this.idx = idx;
        }
        
        @Override
        public String toString() {
            return "[" + idx + ":" + cnt + "]";
        }
    }
}