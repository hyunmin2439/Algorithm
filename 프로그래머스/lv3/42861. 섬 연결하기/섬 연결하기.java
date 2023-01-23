import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        PriorityQueue<int[]> pqueue = new PriorityQueue<>( (a, b) -> a[2] - b[2] );
        int cnt = 1, tot = 0, edge[], parent[] = new int[n];
        
        for(int i = 1; i < n; i++)
            parent[i] = i;
        
        for(int i = 0; i < costs.length; i++)
            pqueue.offer(costs[i]);
        
        while(cnt < n) {
            edge = pqueue.poll();
            
            if(!union(parent, edge[0], edge[1])) continue;
            
            cnt++;
            tot += edge[2];
        }
        
        return tot;
    }
    
    public int find(int[] parent, int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }
    
    public boolean union(int[] parent, int a, int b) {
        int aParent = find(parent, a),
            bParent = find(parent, b);
        
        if(aParent == bParent) return false;
        
        if(aParent > bParent)
            parent[aParent] = bParent;
        else
            parent[bParent] = aParent;
        
        return true;
    }
}