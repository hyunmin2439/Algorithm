class Solution {
    public int[] solution(String s) {
        int len = 0, answer[] = new int [2];
        String tmp;
        
        while(s.length() > 1) {
            len = s.replaceAll("[0]", "").length();
            
            answer[1] += s.length() - len;
            
            s = Integer.toBinaryString(len);
            
            answer[0]++;
        }
        
        return answer;
    }
}