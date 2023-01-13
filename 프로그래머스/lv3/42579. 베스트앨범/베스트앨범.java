import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> sum = new HashMap<>();
        Map<String, PriorityQueue<int[]>> rank = new HashMap<>();
        List<Map.Entry<String, Integer>> entryList;
        List<Integer> list = new LinkedList<>();
        PriorityQueue<int[]> pqueue;
        String key;
        int[] answer;
        
        for(int i = 0; i < plays.length; i++) {
            if(sum.containsKey(genres[i])) {
                sum.put(genres[i], sum.get(genres[i]) + plays[i]);
                rank.get(genres[i]).offer(new int[]{i, plays[i]});
            }   
            else {
                sum.put(genres[i], plays[i]);
                pqueue = new PriorityQueue<>( (a, b) -> {
                    if(b[1] != a[1])
                        return b[1] - a[1];
                    
                    return a[0] - b[0];
                });
                pqueue.offer(new int[]{i, plays[i]});
                rank.put(genres[i], pqueue);
            }
        }
        
        entryList = new LinkedList<>(sum.entrySet());
        entryList.sort((a, b) -> sum.get(b.getKey()) - sum.get(a.getKey()));
        
        for(Map.Entry<String, Integer> entry : entryList){
            key = entry.getKey();
            pqueue = rank.get(key);
            
            list.add(pqueue.poll()[0]);
            if(!pqueue.isEmpty()) 
                list.add(pqueue.poll()[0]);
        }
        
        answer = list.stream().mapToInt(i->i).toArray();
        
        return answer;
    }
}