class Solution {
    int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m + 1][n + 1];
        int ny, nx, up, left;
        
        dp[0][1] = 1;
        
        for(int y = 1; y <= m; y++) {
            for(int x = 1; x <= n; x++) {
                if(cityMap[y - 1][x - 1] != 0) continue;
                
                ny = y - 1;
                while(ny >= 1 && cityMap[ny - 1][x - 1] == 2) ny--;
                up = dp[ny][x];
                
                nx = x - 1;
                while(nx >= 1 && cityMap[y - 1][nx - 1] == 2) nx--;
                left = dp[y][nx];
                
                dp[y][x] = (up + left) % MOD;
            }
        }
        
        return dp[m][n];
    }
}