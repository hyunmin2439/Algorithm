import java.util.Map;
import java.util.HashMap;

class Solution {
    public long[] solution(long k, long[] room_number) {
        int idx = -1, len = room_number.length;
        Map<Long, Long> map = new HashMap<>();
        
        while(++idx < len) {
            room_number[idx] = find(map, room_number[idx]);
        }
        
        return room_number;
    }
    
    public long find(Map<Long, Long> map, long find) {
        if( !map.containsKey(find) ) {
            map.put(find, find + 1);
            return find;
        }
        
        long parent = find(map, map.get(find));
        map.put(find, parent);
        
        return parent;
    }
}