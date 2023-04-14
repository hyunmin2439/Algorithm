class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        String str;
        int answer, len, idx, cnt, unit, end;
        
        unit = 1;
        answer = len = s.length();
        end = len / 2;
        
        while(unit <= end) {
            idx = 0;
            
            while(idx < len) {
                str = s.substring(idx, Math.min(len, idx + unit));
                idx += unit;
                cnt = 1;

                while(idx < len && str.equals( s.substring(idx, Math.min(len, idx + unit) ) )) {
                    idx += unit;
                    cnt++;
                }

                if(cnt > 1)
                    sb.append(cnt);
                
                sb.append(str);
            }

            answer = Math.min(answer, sb.length());
            sb.setLength(0);
            unit++;
        }
        
        return answer;
    }
}