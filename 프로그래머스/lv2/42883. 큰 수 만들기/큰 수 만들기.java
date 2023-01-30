class Solution {
    public String solution(String number, int k) {
        char[] answer = new char[number.length() - k];
        char max;
        int idx = 0, maxIdx, start = 0, end = k;
        
        while(k > 0 && end < number.length()) {
            maxIdx = start;
            max = number.charAt(start);

            for(int i = start + 1; i <= end; i++) {
                if(number.charAt(i) <= max) continue;
                
                maxIdx = i;
                max = number.charAt(i);
            }
            
            answer[idx++] = max;
            k -= maxIdx - start;
            start = maxIdx + 1;
            end = start + k;
        }
        
        if(k == 0)
            for(int i = start; i < number.length(); i++) {
                answer[idx++] = number.charAt(i);
            }
        
        return String.valueOf(answer);
    }
}