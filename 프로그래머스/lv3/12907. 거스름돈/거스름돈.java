class Solution {
    public int solution(int n, int[] money) {
        int answer = 0, MOD = 1_000_000_007;
        int[] dp = new int[n + 1];
        
        for(int i = 0; i < money.length; i++) {
            dp[ money[i] ]++;
            
            for(int j = money[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - money[i]]) % MOD;
            }
        }
        
        return dp[n];
    }
}