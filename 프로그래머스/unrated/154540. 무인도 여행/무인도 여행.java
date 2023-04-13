import java.util.*;

class Solution {
    
    private final int SEA = 0;
    
    private int row, col, size;
    private int[] dy = { -1, 0, 1, 0 };
    private int[] dx = { 0, -1, 0, 1 };
    
    public int[] solution(String[] maps) {
        char c;
        int cnt = 0;
        int[][] map, queue;
        boolean[][] visited;
        List<Integer> list;
        
        row = maps.length;
        col = maps[0].length();
        size = row * col;
        
        map = new int[row][col];
        queue = new int[size][2];
        visited = new boolean[row][col];
        list = new LinkedList<>();
        
        for(int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                c = maps[y].charAt(x);
                map[y][x] = c == 'X' ? 0 : c - '0';
            }
        }
        
        for(int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        
        
        for(int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                if(visited[y][x] || map[y][x] == SEA) continue;
                
                list.add( bfs(visited, map, queue, y, x) );
            }
        }
        
        Collections.sort(list);
        
        if(list.isEmpty()) return new int[] {-1};
            
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    private int bfs(boolean[][] visited, int[][] map, int[][] queue, int ny, int nx) {
        int sum = 0, front = -1, rear = -1;
        int[] curr = new int[2];
        
        sum += map[ny][nx];
        visited[ny][nx] = true;
        
        rear = (rear + 1) % size;
        queue[rear][0] = ny;
        queue[rear][1] = nx;
        
        while(front != rear) {
            front = (front + 1) % size;
            curr[0] = queue[front][0];
            curr[1] = queue[front][1];
            
            for(int d = 0; d < 4; d++) {
                ny = curr[0] + dy[d];
                nx = curr[1] + dx[d];
                
                if(!(0 <= ny && ny < row && 0 <= nx && nx < col)) continue;
                
                if(visited[ny][nx] || map[ny][nx] == SEA) continue;
                
                sum += map[ny][nx];
                visited[ny][nx] = true;
                
                rear = (rear + 1) % size;
                queue[rear][0] = ny;
                queue[rear][1] = nx;
            }
        }
        
        return sum;
    }
}