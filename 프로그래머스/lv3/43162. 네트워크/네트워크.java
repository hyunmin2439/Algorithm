import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int end, answer = 0;
        
        for(int i = 0; i < computers.length; i++) {
            if(visited[i]) continue;
        
            for(int j = 0; j < computers[i].length; j++) {
                if(visited[j] || computers[i][j] != 1) continue;
                
                answer++;
                
                visited[i] = true;
                dfs(computers, visited, n, i);
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) answer++;
        }
        
        return answer;
    }
    
    private void dfs(int[][] computers, boolean[] visited, int n, int start) {
        for(int i = 0; i < n; i++) {
            if(start == i || visited[i] || computers[start][i] != 1) continue;
            
            visited[i] = true;
            dfs(computers, visited, n, i);
        }
    }
}