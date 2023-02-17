import java.util.Arrays;

class Solution {
    public int solution(int[] money) {
        int[] dp = new int [money.length], // 첫번째 집 선택, 마지막 집 선택 안함
              dp2 = new int[money.length]; // 첫번째 집 선택 안함, 마지막 집 선택
        
        if(money.length == 3) {
            return Math.max(money[0], Math.max(money[1], money[2]));
        }
        
        dp[0] = money[0];
        dp[2] = money[0] + money[2];
        for(int i = 3; i < money.length - 1; i++) {
            dp[i] = money[i] + Math.max(dp[i - 2], dp[i - 3]);
        }
        
        dp2[1] = money[1]; // 두번째 집
        dp2[2] = money[2]; // 세번째 집
        for(int i = 3; i < money.length; i++) {
            dp2[i] = money[i] + Math.max(dp2[i - 2], dp2[i - 3]);
        }
        
        return Math.max(Math.max(dp[money.length - 3], dp[money.length - 2]), Math.max(dp2[money.length - 2], dp2[money.length - 1]));
    }
}