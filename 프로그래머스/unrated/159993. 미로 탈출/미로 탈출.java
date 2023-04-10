class Solution {
    public int solution(String[] maps) {
        int row = maps.length, col = maps[0].length(), sy = -1, sx = -1;
        char[][] charMaps = new char[row][];
        
        for(int y = 0; y < row; y++) {
            charMaps[y] = maps[y].toCharArray();
            
            if(sy >= 0) continue;
            
            for(int x = 0; x < col; x++) {
                if(charMaps[y][x] == 'S') {
                    sy = y;
                    sx = x;
                    break;
                }
            }
        }
        
        return bfs(charMaps, row, col, sy, sx);
    }
    
    private int bfs(char[][] maps, int row, int col, int ny, int nx) {
        int size = row * col, front = -1, rear = -1, openExit;
        int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 };
        Node curr, queue[] = new Node[size];
        boolean[][][] visited = new boolean[2][row][col];
        
        rear = (rear + 1) % size;
        queue[rear] = new Node(0, ny, nx, 0);
        visited[0][ny][nx] = true;
        
        while(front != rear) {
            front = (front + 1) % size;
            curr = queue[front];
            
            for(int d = 0; d < dy.length; d++) {
                ny = curr.y + dy[d];
                nx = curr.x + dx[d];
                openExit = curr.openExit;
                
                if(!(0 <= ny && ny < row && 0 <= nx && nx < col)) continue;
                
                if(visited[openExit][ny][nx] || maps[ny][nx] == 'X') continue;
                
                if(curr.openExit == 0 && maps[ny][nx] == 'L') openExit = 1;
                
                if(curr.openExit == 1 && maps[ny][nx] == 'E') return curr.t + 1;
                
                rear = (rear + 1) % size;
                queue[rear] = new Node(openExit, ny, nx, curr.t + 1);
                visited[openExit][ny][nx] = true;
            }
        }
        
        return -1;
    }
    
    class Node {
        int openExit, y, x, t;
        
        public Node(int openExit, int y, int x, int t) {
            this.openExit = openExit;
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }
}