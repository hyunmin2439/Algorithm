import java.util.StringTokenizer;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        Long number;
        
        String notation = getNotation(n, k);
        StringTokenizer st = new StringTokenizer(notation, "0");
        
        while( st.hasMoreTokens() ) {
            number = Long.parseLong(st.nextToken());
            
            if( isPrime(number) )
                answer++;
        }
        
        return answer;
    }
    
    private String getNotation(int n, int k) {
        String str = "";
        
        while( n > k - 1 ) {
            str = n % k + str;
            n /= k;
        }
        
        return n + str;
    }
    
    private boolean isPrime(Long number) {
        int end = (int) Math.sqrt(number);
        
        if( number == 1 || (number != 2 && number % 2 == 0) )
            return false;
        
        for(int i = 3; i <= end; i += 2) {
            if(number % i == 0)
                return false;
        }
        
        return true;
    }
}