class Solution {
    private int[] dy = { -1, 1, 0, 0 };
    private int[] dx = { 0, 0, 1, -1 };
    
    public int solution(String dirs) {
        boolean[][][] visited = new boolean[11][11][4];
        int y = 5, x = 5, d = 0, rd = 0, answer = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            switch(dirs.charAt(i)) {
                case 'U': d = 0; rd = 1; break;
                case 'D': d = 1; rd = 0; break;
                case 'R': d = 2; rd = 3; break;
                case 'L': d = 3; rd = 2; break;
            }
            
            y += dy[d];
            x += dx[d];
            
            if( !(0 <= y && y <= 10 && 0 <= x && x <= 10) ) {
                y -= dy[d];
                x -= dx[d];
                continue;
            }
            
            if(!visited[y][x][rd]) {
                visited[ y - dy[d] ][ x - dx[d] ][d] = true;
                visited[y][x][rd] = true;
                answer++;
            }
        }
        
        return answer;
    }
}