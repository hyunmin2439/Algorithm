import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] isVictory = new boolean[n + 1][n + 1];
        int cnt, answer = 0;
        
        for(int i = 0; i < results.length; i++) {
            isVictory[results[i][0]][results[i][1]] = true;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(k == i) continue;
                
                for(int j = 1; j <= n; j++) {
                    if(k == j || i == j || isVictory[i][j] || isVictory[j][i]) continue;
                    
                    isVictory[i][j] = isVictory[i][k] && isVictory[k][j];
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            cnt = 0;

            for(int j = 1; j <= n; j++) {
                if(isVictory[i][j] | isVictory[j][i]) cnt++;
            }
            
            if(cnt == n - 1) answer++;
        }
        
        return answer;
    }
}