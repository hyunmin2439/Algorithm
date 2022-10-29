class Solution {
    int solution(int[][] land) {
        int max = 0, dp[][] = new int[land.length + 1][4];
        
        for(int y = 1; y <= land.length; y++) {
            for(int x = 0; x < 4; x++) {
                max = 0;
                for(int i = 0; i < 4; i++) {
                    if(x == i) continue;
                    max = Math.max(max, dp[y - 1][i]);
                }
                dp[y][x] = max + land[y - 1][x];
            }
        }
        
        for(int x = 0; x < 4; x++) {
            max =  Math.max(max, dp[land.length][x]);
        }

        return max;
    }
}