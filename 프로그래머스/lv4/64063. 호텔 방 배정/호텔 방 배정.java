import java.util.Map;
import java.util.HashMap;

class Solution {
    public long[] solution(long k, long[] room_number) {
        int idx = -1, len = room_number.length;
        long room = 0;
        Map<Long, Long> map = new HashMap<>();
        
        while(++idx < len) {
            room = room_number[idx];
            
            room = find(map, room);
            map.put(room, find(map, room + 1));
            
            room_number[idx] = room;
        }
        
        return room_number;
    }
    
    public long find(Map<Long, Long> map, long find) {
        if( !map.containsKey(find) ) return find;
        
        long parent = find(map, map.get(find));
        map.put(find, parent);
        
        return parent;
    }
}