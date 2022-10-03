import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) { 
        int[][] shortest = ployd(n, fares);
        int answer = Integer.MAX_VALUE;
        
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, shortest[s][i] + shortest[i][a] + shortest[i][b]);
        }
        
        return answer;
    }
    
    private int[][] ployd(int n, int[][] fares) {
        int[][] shortest = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            Arrays.fill(shortest[i], 20_000_000); // 딱 맞는 값으로 하니 실패함 최대값 높게 잡을 것.
            shortest[i][i] = 0;
        }
            
        for(int i = 0; i < fares.length; i++)
            shortest[fares[i][0]][fares[i][1]] = shortest[fares[i][1]][fares[i][0]] = fares[i][2];
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(i == k) continue;
                for(int j = 1; j <= n; j++) {
                    if(j == k || j == i) continue;
                    
                    shortest[i][j] = Math.min(shortest[i][j], shortest[i][k] + shortest[k][j]);
                }
            }
        }
        
        return shortest;
    }
}