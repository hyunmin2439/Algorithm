class Solution {
    public long solution(int n) {
        long MOD = 1_234_567L;
        long[] dp = new long[n + 1];
        dp[0] = dp[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        
        return dp[n];
    }
}