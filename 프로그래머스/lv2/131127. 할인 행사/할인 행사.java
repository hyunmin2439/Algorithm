import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> cntMap = new HashMap<>();
        int j, end = discount.length - 10, answer = 0;
        
        for(int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
            cntMap.put(want[i], 0);
        }
        
        for(int i = 0; i <= end; i++) {
            j = i;
            
            for(int k = 0; k < 10; k++, j++) {
                if( cntMap.containsKey(discount[j]) )
                    cntMap.put(discount[j], cntMap.get(discount[j]) + 1);
            }
            
            if( isCorrect(map, cntMap, want) ) answer++;
            
            for(int k = 0; k < want.length; k++) {
                cntMap.put(want[k], 0);
            }
        }
        
        return answer;
    }
    
    private boolean isCorrect(Map<String, Integer> map, Map<String, Integer> cntMap, String[] want) {
        for(int i = 0; i < want.length; i++) {
            if(map.get(want[i]) != cntMap.get(want[i])) return false;
        }
        
        return true;
    }
}