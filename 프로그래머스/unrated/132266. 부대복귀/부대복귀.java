import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> edges = makeEdges(roads, n);
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] answer = new int[sources.length];
        
        for(int i = 0; i < sources.length; i++) {
            if(sources[i] != destination)
                answer[i] = bfs(edges, queue, visited, sources[i], destination);
        }
        
        return answer;
    }
    
    private int bfs(List<List<Integer>> edges, LinkedList<int[]> queue, boolean[] visited, int source, int destination) {
        List<Integer> list;
        int[] curr;
        
        queue.clear();
        Arrays.fill(visited, false);
        
        visited[source] = true;
        queue.offer(new int[]{ source, 0 });
        
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            list = edges.get(curr[0]);
            
            for(int i = 0; i < list.size(); i++) {
                source = list.get(i);
                
                if(source == destination) return curr[1] + 1;
                
                if(visited[source]) continue;
                
                visited[source] = true;
                queue.offer(new int[]{ source, curr[1] + 1 });
            }
        }
        
        return -1;
    }
    
    private List<List<Integer>> makeEdges(int[][] roads, int n) {
        List<List<Integer>> edges = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        
        for(int i = 0; i < roads.length; i++) {
            edges.get( roads[i][0] ).add( roads[i][1] );
            edges.get( roads[i][1] ).add( roads[i][0] );
        }
        
        return edges;
    }
}