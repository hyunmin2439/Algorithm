import java.util.*;

class Solution {
    
    private int[] dy = { -1, 0, 1, 0 };
    private int[] dx = { 0, -1, 0, 1 };
    private int[][] queue;
    private int len, size;
    
    public int solution(int[][] game_board, int[][] table) {
        List<int[]> boardBlock, tableBlock;
        int[] pos;
        int answer = 0;
        
        len = table.length;
        size = len * len;
        
        queue = new int[size][2];
        boardBlock = new ArrayList<>();
        tableBlock = new ArrayList<>();
        
        // 4회전
        for(int i = 0; i < 4; i++) {
            for(int y = 0; y < len; y++) {
                for(int x = 0; x < len; x++) {
                    // 비어있는 공간이면
                    if(game_board[y][x] == 0) {
                        bfs(boardBlock, game_board, y, x, 0);

                        if( findBlock(boardBlock, tableBlock, table) ) {
                            for(int k = 0; k < boardBlock.size(); k++) {
                                pos = boardBlock.get(k);
                                game_board[y + pos[0]][x + pos[1]] = 1;
                            }
                            
                            answer += boardBlock.size();
                        }
                        
                        boardBlock.clear();
                    }
                }
            }
            
            if(i < 3) table = rotateTable(table);
        }
        
        return answer;
    }
    
    private boolean findBlock(List<int[]> boardBlock, List<int[]> tableBlock, int[][] table) {
        int[] boardPos, tablePos;
        boolean check;
        
        for(int y = 0; y < len; y++) {
            for(int x = 0; x < len; x++) {
                if(table[y][x] == 1) {
                    bfs(tableBlock, table, y, x, 1);
                    
                    if(boardBlock.size() != tableBlock.size()) {
                        tableBlock.clear();
                        continue;
                    }
                    
                    check = true;
                    for(int i = 0; i < boardBlock.size(); i++) {
                        boardPos = boardBlock.get(i);
                        tablePos = tableBlock.get(i);
                        
                        if(boardPos[0] != tablePos[0] || boardPos[1] != tablePos[1]) {
                            check = false;
                            break;
                        }
                    }
                    
                    if(check) {
                        for(int i = 0; i < tableBlock.size(); i++) {
                            tablePos = tableBlock.get(i);
                            table[y + tablePos[0]][x + tablePos[1]] = 0;
                        }
                        tableBlock.clear();
                        
                        return true;
                    }
                    
                    tableBlock.clear();
                }
            }
        }
        
        return false;
    }
    
    private void bfs(List<int[]> block, int[][] board, int sy, int sx, int sw) {
        boolean[][] visited = new boolean[len][len];
        int[] curr = new int[2];
        int front = -1, rear = -1, ny, nx;
        
        rear = (rear + 1) % size;
        queue[rear][0] = sy;
        queue[rear][1] = sx;
        
        visited[sy][sx] = true;
        block.add(new int[]{0, 0});
        
        while(front != rear) {
            front = (front + 1) % size;
            curr[0] = queue[front][0];
            curr[1] = queue[front][1];
            
            for(int d = 0; d < dy.length; d++) {
                ny = curr[0] + dy[d];
                nx = curr[1] + dx[d];
                
                if( !(0 <= ny && ny < len && 0 <= nx && nx < len) ) continue;
                
                if( visited[ny][nx] || board[ny][nx] != sw ) continue;
                
                rear = (rear + 1) % size;
                queue[rear][0] = ny;
                queue[rear][1] = nx;

                visited[ny][nx] = true;
                block.add(new int[]{ny - sy, nx - sx});
            }
        }
    }
    
    private int[][] rotateTable(int[][] table) {
        int[][] newTable = new int[len][len];
        
        for(int y = 0; y < len; y++) {
            for(int x = 0; x < len; x++) {
                newTable[y][x] = table[x][len - y - 1];
            }
        }
        
        return newTable;
    }
}