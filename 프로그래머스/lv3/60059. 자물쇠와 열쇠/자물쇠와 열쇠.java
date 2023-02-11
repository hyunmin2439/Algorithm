import java.util.*;

class Solution {
    
    private int[] dy = { -1, 0, 1, 0 };
    private int[] dx = { 0, -1, 0, 1 };
    
    public boolean solution(int[][] key, int[][] lock) {
        return bfs(key, lock, key.length, lock.length);
    }
    
    private boolean bfs(int[][] key, int[][] lock, int M, int N) {
        int ny, nx, homeCnt, len = N + 2 * (M - 1);
        int[][] rKey = key;
        
        Key curr, next;
        Queue<Key> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[4][len][len];
        
        homeCnt = calcHomeCnt(lock, N);
        if(homeCnt == 0) return true;
        
        curr = new Key(rKey, 0);
        
        queue.offer(curr);
        visited[0][0][0] = true;
        
        for(int i = 1; i < 4; i++) {
        	rKey = rotate(rKey, M);
            next = new Key(rKey, i);
            queue.offer(next);
            visited[i][0][0] = true;
        }
        
        while(!queue.isEmpty()) {
            curr = queue.poll();
            
            for(int d = 0; d < dy.length; d++) {
                ny = curr.y + dy[d];
                nx = curr.x + dx[d];
                
                if(!(0 <= ny && ny < len && 0 <= nx && nx < len)) continue;
                
                if( visited[curr.d][ny][nx] ) continue;
                
                next = new Key(curr.list, ny, nx, curr.d);
                
                if(next.isUnlock(lock, homeCnt, N)) return true;
                visited[curr.d][ny][nx] = true;
                queue.offer(next);
            }
        }
        
        return false;
    }
    
    private int calcHomeCnt(int[][] lock, int N) {
        int homeCnt = 0;
        
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                if(lock[y][x] == 0)
                    homeCnt++;
            }
        }
        
        return homeCnt;
    }
    
    private int[][] rotate(int[][] key, int M) {
        int[][] rKey = new int[M][M];
        
        for(int y = 0; y < M; y++) {
            for(int x = 0; x < M; x++) {
                rKey[y][x] = key[x][M - y - 1];
            }
        }
        
        return rKey;
    }
    
    class Key {
        int y, x, d;
        List<int[]> list;
        
        public Key(int[][] key, int d) {
            this.y = 0;
            this.x = 0;
            this.d = d;
            
            list = new ArrayList<>();
            
            for(int y = 0; y < key.length; y++) {
                for(int x = 0; x < key.length; x++) {
                    if(key[y][x] == 1)
                        list.add(new int[] {y - (key.length - 1), x - (key.length - 1)});
                }
            }
        }
        
        public Key(List<int[]> list, int y, int x, int d) {
            this.list = list;
            this.y = y;
            this.x = x;
            this.d = d;
        }
        
        public boolean isUnlock(int[][] lock, int homeCnt, int N) {
            int p[], ny, nx, cnt = 0, inCnt = 0;
            
            for(int i = 0; i < list.size(); i++) {
                p = list.get(i);
                ny = p[0] + this.y;
                nx = p[1] + this.x;
                
                if(!(0 <= ny && ny < N && 0 <= nx && nx < N)) continue;
                
                if(lock[ny][nx] == 0) cnt++;
                else inCnt++;
            }
            
            return (homeCnt == cnt && inCnt == 0) ? true : false;
        }
    }
}