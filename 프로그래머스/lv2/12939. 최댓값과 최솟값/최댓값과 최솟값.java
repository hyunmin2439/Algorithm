import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        int num = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(s);
        
        while(st.hasMoreTokens()) {
            num = Integer.parseInt(st.nextToken());
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        return String.valueOf(min + " " + max);
    }
}