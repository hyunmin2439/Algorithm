class Solution {
    
    private int[] dy = { -1, 1, 0, 0 };
    private int[] dx = { 0, 0, -1, 1 };
    
    public int solution(String[] board) {
        int ny = -1, nx = -1, yLen = board.length, xLen = board[0].length();
        char[][] charBoard = new char[yLen][];
        
        for(int y = 0; y < yLen; y++) {
            charBoard[y] = board[y].toCharArray();
            
            if(ny != - 1) continue;
            
            for(int x = 0; x < xLen; x++) {
                if(charBoard[y][x] == 'R') {
                    ny = y;
                    nx = x;
                }
            }
        }
        
        return bfs(charBoard, yLen, xLen, ny, nx);
    }
    
    private int bfs(char[][] board, int yLen, int xLen, int ny, int nx) {
        int size = yLen * xLen, front = -1, rear = -1;
        boolean[][] visited = new boolean[yLen][xLen];
        Node curr, queue[] = new Node[size];
        
        rear = (rear + 1) % size;
        queue[rear] = new Node(ny, nx, 0);
        visited[ny][nx] = true;
        
        while(front != rear) {
            front = (front + 1) % size;
            curr = queue[front];
            
            for(int d = 0; d < dy.length; d++) {
                ny = curr.y + dy[d];
                nx = curr.x + dx[d];
                
                while( (0 <= ny && ny < yLen && 0 <= nx && nx < xLen) && board[ny][nx] != 'D' ) {
                    ny += dy[d];
                    nx += dx[d];
                }
                
                ny -= dy[d];
                nx -= dx[d];
                
                if(visited[ny][nx]) continue;
                
                if(board[ny][nx] == 'G') return curr.mv + 1;
                
                rear = (rear + 1) % size;
                queue[rear] = new Node(ny, nx, curr.mv + 1);
                visited[ny][nx] = true;
            }
        }
            
        return -1;
    }
    
    class Node {
        int y, x, mv;
        
        public Node(int y, int x, int mv) {
            this.y = y;
            this.x = x;
            this.mv = mv;
        }
    }
}