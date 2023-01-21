import java.util.Arrays;

class Solution {
    public int solution(int sticker[]) {
        int idx = 0, end = sticker.length - 1,
            dp[][] = new int[2][end + 1];

        if(end == 0)
            return sticker[0];
        if(end < 3)
            return Math.max(sticker[0], Math.max(sticker[1], end == 2 ? sticker[2] : 0));
        
        // 첫 번째 스티커 선택 
        dp[0][0] = dp[0][1] = sticker[0];
        
        // 두 번째 스티커 선택
        dp[1][1] = sticker[1];
        
        for(int i = 2; i < end; i++) {
            dp[0][i] = Math.max(sticker[i] + dp[0][i - 2], dp[0][i - 1]);
            dp[1][i] = Math.max(sticker[i] + dp[1][i - 2], dp[1][i - 1]);
        }
        
        dp[1][end] = Math.max(sticker[end] + dp[1][end - 2], dp[1][end - 1]);
        
        return Math.max(dp[0][end - 1], dp[1][end]);
    }
}