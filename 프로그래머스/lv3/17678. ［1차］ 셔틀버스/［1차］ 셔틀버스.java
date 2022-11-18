import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int hour = 9, min = 0, cnt = 0, idx = 0, numOfCrews = timetable.length;
        StringTokenizer st;
        Crew bus = new Crew(hour, min),
             crews[] = new Crew[numOfCrews];
            
        for(int i = 0; i < numOfCrews; i++) {
            st = new StringTokenizer(timetable[i], ":");
            hour = Integer.parseInt(st.nextToken());
            min = Integer.parseInt(st.nextToken());
            crews[i] = new Crew(hour, min);
        }
        
        Arrays.sort(crews);
        
        while(n-- > 0) {
            cnt = 0;
            
            while( idx < numOfCrews && cnt < m && crews[idx].isArrive(bus) ) {
                cnt++;
                idx++;
            }
            
            bus.addTime(t);
        }
        
        bus.substractTime(t);
        
        // 자리 여유 있음
        if(cnt < m) return getTimeString(bus.hour, bus.min);
        
        // 자리 여유 없으면 마지막 탑승자보다 1분 빨리
        crews[--idx].substractTime(1);
        return getTimeString(crews[idx].hour, crews[idx].min);
    }
    
    private String getTimeString(int hour, int min) {
        return (hour > 9 ? hour : "0" + hour) + ":" + (min > 9 ? min : "0" + min);
    }
    
    private class Crew implements Comparable<Crew> {
        int hour, min;
        
        public Crew(int hour, int min) {
            this.hour = hour;
            this.min = min;
        }
        
        public boolean isArrive(Crew other) {
            if(this.hour > other.hour) 
                return false;
            else if(this.hour < other.hour)
                return true;
            
            if(this.min > other.min) 
                return false;
                
            return true;
        }
        
        public void addTime(int min) {
            this.min += min;
            
            if(this.min < 60) return;
            
            this.min -= 60;
            this.hour += 1;
        }
        
        public void substractTime(int min) {
            this.min -= min;
            
            if(this.min >= 0) return;
            
            this.min += 60;
            this.hour -= 1;
        }
        
        @Override
        public int compareTo(Crew other) {
            if(this.hour != other.hour)
                return this.hour - other.hour;
            
            return this.min - other.min;
        }
    }
}