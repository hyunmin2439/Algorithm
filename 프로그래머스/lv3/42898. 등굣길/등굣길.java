class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int mod = 1_000_000_007, dp[][] = new int[n][m];
        boolean[][] isBlocked = new boolean[n][m];
        
        for(int i = 0; i < puddles.length; i++)
            isBlocked[puddles[i][1] - 1][puddles[i][0] - 1] = true;
        
        for(int y = 0; y < n; y++) {
            if(isBlocked[y][0]) break;
            dp[y][0] = 1;
        }
        
        for(int x = 0; x < m; x++) {
            if(isBlocked[0][x]) break;
            dp[0][x] = 1;
        }
        
        for(int y = 1; y < n; y++) {
            for(int x = 1; x < m; x++) {
                if(isBlocked[y][x]) continue;
                
                dp[y][x] = (dp[y - 1][x] + dp[y][x - 1]) % mod;
            }
        }
        
        return dp[n - 1][m - 1];
    }
}