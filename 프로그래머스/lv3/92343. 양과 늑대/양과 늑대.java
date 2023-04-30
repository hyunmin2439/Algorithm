import java.util.Arrays;

class Solution {
    private final int SHEEP = 0;
    
    private int[] parent, level, startRoute, destRoute, idxOfSheep;
    private int len, answer;
    
    public int solution(int[] info, int[][] edges) {
        len = info.length;
        
        setParentAndLevel(edges);
        setIdxOfSheep(info);
        startRoute = new int[len];
        destRoute = new int[len];
        
        permutaion(new boolean[idxOfSheep.length], new int[idxOfSheep.length], info, 0);
        
        return answer;
    }
    
    private void permutaion(boolean[] used, int[] perm, int[] info, int idx) {
        if(answer == idxOfSheep.length) return;
        
        if(idx == idxOfSheep.length) {
            answer = Math.max(answer, simulation(info, perm));
            return;
        }
        
        for(int i = 0; i < idxOfSheep.length; i++) {
            if(used[i]) continue;
            
            perm[idx] = idxOfSheep[i];
            used[i] = true;
            permutaion(used, perm, info, idx + 1);
            used[i] = false;
        }
    }
    
    private int simulation(int[] info, int[] perm) {
        boolean[] visited = new boolean[len];
        int[] routeIdx;
        int idx = 0, curr = 0, sheep = 1, wolf = 0;
        
        visited[0] = true;
        
        while(idx < perm.length) {
            routeIdx = lca(curr, perm[idx]);
            
            for(int i = 0; i <= routeIdx[0]; i++) {
                if(sheep <= wolf) return sheep;
                
                curr = startRoute[i];
                
                if(visited[curr]) continue;
                
                visited[curr] = true;
                
                if(info[curr] == SHEEP)
                    sheep++;
                else
                    wolf++;
            }
            
            while(routeIdx[1] >= 0) {
                if(sheep <= wolf) return sheep;
                
                curr = destRoute[ routeIdx[1]-- ];
                
                if(visited[curr]) continue;
                
                visited[curr] = true;
                
                if(info[curr] == SHEEP)
                    sheep++;
                else
                    wolf++;
            }
            
            idx++;
        }
        
        return sheep;
    }
    
    private void setParentAndLevel(int[][] edges) {
        int curr, lv;
        
        parent = new int[len];
        level = new int[len];
        
        for(int i = 0; i < edges.length; i++) {
            parent[ edges[i][1] ] = edges[i][0];
        }
        
        for(int i = 0; i < len; i++) {
            curr = i;
            lv = 0;
            
            while(curr != parent[curr]) {
                curr = parent[curr];
                lv++;
            }
            
            level[i] = lv;
        }
    }
    
    private void setIdxOfSheep(int[] info) {
        int idx = 0, numOfSheep = 0;
        
        for(int i = 0; i < info.length; i++) {
            if(info[i] == SHEEP) numOfSheep++;
        }
        
        idxOfSheep = new int[numOfSheep];
        
        for(int i = 0; i < info.length; i++) {
            if(info[i] == SHEEP) idxOfSheep[idx++] = i;
        }
    }
    
    private int[] lca(int start, int dest) {
        int rear = -1, top = -1;
        
        while(level[start] > level[dest]) {
            start = parent[start];
            startRoute[++rear] = start;
        }
        
        while(level[start] < level[dest]) {
            destRoute[++top] = dest;
            dest = parent[dest];
        }
        
        // start 이동 후 위치 기록, dest는 이동 전 위치 기록
        // 같은 경우 startRoute에만 기록되어 있음
        // 예시1에서 start : 5, dest : 7일 경우
        // startRoute : 6 4 1 0 / destRoute : 7 8
        while(start != dest) {
            start = parent[start];
            startRoute[++rear] = start;
            
            destRoute[++top] = dest;
            dest = parent[dest];
        }
        
        return new int[] { rear, top };
    }
}