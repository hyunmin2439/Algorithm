import java.util.Queue;
import java.util.LinkedList;

class Solution {
    
    private static int[] dy = { -1, 0, 1, 0 };
    private static int[] dx = { 0, -1, 0, 1 };
    
    public int[] solution(String[][] places) {
        Queue<Pos> queue = new LinkedList<>();
        int[] answer = new int[places.length];
        
        for(int i = 0; i < places.length; i++) {
            answer[i] = check(queue, places[i]);
        }
        
        return answer;
    }
    
    private int check(Queue<Pos> queue, String[] place) {
        boolean[][] visited = new boolean[5][5];
        
        for(int y = 0; y < 5; y++) {
            for(int x = 0; x < 5; x++) {
                if( place[y].charAt(x) == 'P' && !visited[y][x] && bfs(queue, place, visited, y, x) )
                    return 0;
            }
        }
        
        return 1;
    }
    
    private boolean bfs(Queue<Pos> queue, String[] place, boolean[][] visited, int y, int x) {
        Pos curr;
        
        queue.clear();
        queue.offer(new Pos(y, x, 0));
        visited[y][x] = true;
        
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];
                
                if( !(0 <= ny && ny < 5 && 0 <= nx && nx < 5) ) continue;
                
                if(visited[ny][nx] || place[ny].charAt(nx) == 'X') continue;
                
                if( place[ny].charAt(nx) == 'P' ) return true;

                if(curr.dist == 1) continue;
                
                queue.offer(new Pos(ny, nx, 1));
                visited[ny][nx] = true;
            }
        }
        
        return false;
    }
    
    private class Pos {
        int y, x, dist;
        
        public Pos(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
}