class Solution {
    private final int WALL = 0;
    
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    private int bfs(int[][] maps) {
        boolean[][] visited;
        int[][] queue;
        int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 }, curr;
        int queueSize, N, M, front = -1, rear = -1, ny, nx, ans = -1;
        
        N = maps.length;
        M = maps[0].length;
        queueSize = N * M;
        queue = new int[queueSize][3];
        visited = new boolean[N][M];
        
        rear = (rear + 1) % queueSize;
        queue[rear][0] = queue[rear][1] = 0;
        queue[rear][2] = 1;
        visited[0][0] = true;
        
        while(front != rear) {
            front = (front + 1) % queueSize;
            curr = queue[front];
            
            for(int d = 0; d < 4; d++) {
                ny = curr[0] + dy[d];
                nx = curr[1] + dx[d];
                
                if(!(0 <= ny && ny < N && 0 <= nx && nx < M)) continue;
                
                if(visited[ny][nx] || maps[ny][nx] == WALL) continue;
                
                if(ny == N - 1 && nx == M - 1)
                    return curr[2] + 1;
                
                rear = (rear + 1) % queueSize;
                queue[rear][0] = ny;
                queue[rear][1] = nx;
                queue[rear][2] = curr[2] + 1;
                visited[ny][nx] = true;
            }
        }
        
        return -1;
    }
}