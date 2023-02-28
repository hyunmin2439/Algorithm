class Solution {
    public int[] solution(int[] numbers) {
        int[] dp = new int[numbers.length];
        
        dp[numbers.length - 1] = -1;
        for(int i = numbers.length - 2, j = 0; i >= 0; i--) {
            j = i + 1;
            
            if(numbers[i] < numbers[j])
                dp[i] = numbers[j];
            else {
                while(j < numbers.length && dp[j] != -1 && numbers[i] >= dp[j]) j++;
                dp[i] = dp[j];
            }
        }
        
        return dp;
    }
}