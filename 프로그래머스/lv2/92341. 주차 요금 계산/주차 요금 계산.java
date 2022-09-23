import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, List<Integer>> map = new TreeMap<>();
        List<String> keyList;
        List<Integer> timeList;
        String number;
        int minute, answer[];
        
        // init
        for(int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            
            minute = getMinute(st.nextToken());
            number = st.nextToken();
            
            if( !map.containsKey(number) )
                map.put(number, new LinkedList<>());
            
            map.get(number).add(minute);
        }
        
        // 출차 기록 없으면 추가
        keyList = new ArrayList<>(map.keySet());
        for(int i = 0; i < keyList.size(); i++) {
            timeList = map.get( keyList.get(i) );
            
            if(timeList.size() % 2 != 0) 
                timeList.add(23 * 60 + 59);
        }
        
        answer = new int[keyList.size()];
        
        // 총 주차 시간 계산하기
        for(int i = 0; i < keyList.size(); i++) {
            timeList = map.get( keyList.get(i) );
            minute = 0;
            
            for(int j = 1; j < timeList.size(); j += 2) {
                minute += timeList.get(j) - timeList.get(j - 1);
            }
            
            answer[i] = getFee(fees, minute);
        }
        
        return answer;
    }
    
    private int getMinute(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
    
    private int getFee(int[] fees, int minute) {
        if(minute <= fees[0])
            return fees[1];
        
        minute -= fees[0];
        
        return fees[1] + (int)Math.ceil( (double)minute / fees[2] ) * fees[3];
    }
}