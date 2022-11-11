import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0, height = triangle.length - 1, width = 1, dp[][] = new int[height + 1][];
        
        dp[0] = new int[1];
        dp[0][0] = triangle[0][0];
        
        for(int h = 1; h <= height; h++) {
            dp[h] = new int[width + 1];
            dp[h][0] = dp[h - 1][0] + triangle[h][0];
            
            for(int w = 1; w < width; w++) {
                dp[h][w] = triangle[h][w] + Math.max(dp[h - 1][w - 1], dp[h - 1][w]);
            }
            
            dp[h][width] = dp[h - 1][width - 1] + triangle[h][width];
            
            width++;
        }
        
        for(int w = 0; w < width; w++) {
            answer = Math.max(answer, dp[height][w]);
        }
        
        return answer;
    }
}