class Solution {
    public int solution(int storey) {
        int mod = 0, answer = 0;
        
        while(storey > 0) {
            mod = storey % 10;
            storey /= 10;
            
            if(mod > 5 || (mod == 5 && storey % 10 >= 5) ) {
                mod = 10 - mod;
                storey += 1;
            }
            
            answer += mod;
        }
        
        return answer;
    }
}