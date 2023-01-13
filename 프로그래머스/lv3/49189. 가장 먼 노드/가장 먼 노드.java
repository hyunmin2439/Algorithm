import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> edges = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++) {
            edges.get(edge[i][0]).add(edge[i][1]);
            edges.get(edge[i][1]).add(edge[i][0]);
        }
           
        return bfs(edges);
    }
    
    private int bfs(List<List<Integer>> edges) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> edge = new ArrayList<>();
        boolean[] visited = new boolean[edges.size()];
        int curr, node, cycle = 1;
        
        queue.offer(1);
        visited[1] = true;
        
        while( !queue.isEmpty() ) {
            cycle = queue.size();
            
            for(int i = 0; i < cycle; i++) {
                curr = queue.poll();
                
                edge = edges.get(curr);
                
                for(int j = 0; j < edge.size(); j++) {
                    node = edge.get(j);
                    
                    if(visited[node]) continue;
                    
                    queue.offer(node);
                    visited[node] = true;
                }
            }
        }
        
        return cycle;
    }
}