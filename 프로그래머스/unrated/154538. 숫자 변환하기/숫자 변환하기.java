import java.util.Arrays;

class Solution {
    
    private final int INF = Integer.MAX_VALUE;
    
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];
        
        Arrays.fill(dp, INF);
        
        dp[x] = 0;
        
        for(int i = x; i <= y; i++) {
            if(i % 2 == 0 && i / 2 >= x && dp[i / 2] != INF)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
                
            if(i % 3 == 0 && i / 3 >= x && dp[i / 3] != INF)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            
            if(i - n >= x && dp[i - n] != INF)
                dp[i] = Math.min(dp[i], dp[i - n] + 1);
        }
        
        return dp[y] == INF ? -1 : dp[y];
    }
}