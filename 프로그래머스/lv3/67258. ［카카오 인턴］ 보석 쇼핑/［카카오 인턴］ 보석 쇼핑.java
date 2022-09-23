import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        int tot = 0, answer[];
        
        for(int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }
        
        tot = set.size();
        System.out.println(tot);
        answer = twoPointer(gems, tot);
        
        return answer;
    }
    
    private int[] twoPointer(String[] gems, int tot) {
        Map<String, Integer> map = new HashMap<>();
        int cnt = 0, left = 0, right = 0, answer[] = new int[]{0, gems.length - 1};
        
        map.put(gems[left], 1);
        if(map.size() == tot) answer[1] = 0;
        
        while(true) {
            if(map.size() < tot) {
                right++;
                
                if(right >= gems.length)
                    break;
                
                cnt = map.containsKey(gems[right]) ? map.get(gems[right]) + 1 : 1;
                map.put(gems[right], cnt);
            }
            else {
                cnt = map.get(gems[left]);
                
                if(cnt == 1)
                    map.remove(gems[left]);
                else
                    map.put(gems[left], cnt - 1);
                
                left++;
            }
            
            if(map.size() == tot) {
                if(right - left < answer[1] - answer[0]){
                    answer[0] = left;
                    answer[1] = right;
                }
            }    
        }
        
        return new int[]{answer[0] + 1, answer[1] + 1};
    }
}