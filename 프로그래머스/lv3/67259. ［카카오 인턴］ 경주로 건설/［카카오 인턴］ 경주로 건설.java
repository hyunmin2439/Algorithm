import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[][] board) {
        return bfs(board, board.length);
    }
    
    private int bfs(int[][] board, int n) {
        Queue<Pos> queue = new LinkedList<>();
        int[][][] feeBoard = initFeeBoard(n);
        int[] dy = { -1, 0, 1, 0 };
        int[] dx = { 0, -1, 0, 1 };
        int answer = Integer.MAX_VALUE;
        final int WALL = 1;
        Pos curr;
        
        // 초기 세팅
        for(int i = 0; i < 4; i++)
            feeBoard[i][0][0] = 0;
        if(board[1][0] != WALL) { // 아래측
            queue.offer(new Pos(1, 0, 2, 100));
            
            // 시작 지점에서 바로 옆이기 때문에 이 값이 제일 낮은 값
            // 한바퀴 돌아서 현재 지점으로 와서 반복하는 일이 없도록
            for(int i = 0; i < 4; i++)
                feeBoard[i][1][0] = 100;
        }
        if(board[0][1] != WALL) { // 우측
            queue.offer(new Pos(0, 1, 3, 100));
            
            for(int i = 0; i < 4; i++)
                feeBoard[i][0][1] = 100;
        }
        
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];
                int nfee = curr.fee + 100 + (curr.d == d ? 0 : 500);
                
                if( !(0 <= ny && ny < n && 0 <= nx && nx < n) ) continue;
                
                if( board[ny][nx] == WALL || feeBoard[d][ny][nx] < nfee ) continue;
                
                feeBoard[d][ny][nx] = nfee;
                
                if( !(ny == n - 1 && nx == n - 1) )
                    queue.offer(new Pos(ny, nx, d, nfee));
            }
        }
        
        for(int i = 0; i < 4; i++)
            answer = Math.min(answer, feeBoard[i][n - 1][n - 1]);
        
        return answer;
    }
    
    private int[][][] initFeeBoard(int n) {
        int[][][] feeBoard = new int[4][n][n];
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < n; j++) 
                Arrays.fill(feeBoard[i][j], Integer.MAX_VALUE);
        
        return feeBoard;
    }
    
    private class Pos {
        int y, x, d, fee;
        
        public Pos(int y, int x, int d, int fee) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.fee = fee;
        }
    }
}