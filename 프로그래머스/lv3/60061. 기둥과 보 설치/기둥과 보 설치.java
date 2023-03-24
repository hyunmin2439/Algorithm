class Solution {
    private final int PILLAR = 0, GIRDER = 1, DESTROY = 0, BUILD = 1;
    
    private boolean[][] pillar, girder;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer;
        int cnt = 0, idx = 0;
        
        pillar = new boolean[n + 2][n + 2]; // 기둥 indexOutException 방지용 +1 공간 더 잡음
        girder = new boolean[n + 2][n + 2]; // 보
        
        for(int i = 0; i < build_frame.length; i++) {
            switch(build_frame[i][3]) {
                case DESTROY :
                    if( isDestroy(build_frame[i][2], build_frame[i][1], build_frame[i][0]) )
                        cnt--;
                    break;
                    
                case BUILD :
                    if( isBuild(build_frame[i][2], build_frame[i][1], build_frame[i][0]) ) {
                        if(build_frame[i][2] == PILLAR)
                            pillar[ build_frame[i][1] ][ build_frame[i][0] ] = true;
                        else
                            girder[ build_frame[i][1] ][ build_frame[i][0] ] = true;
                        cnt++;
                    }
                    break;
            }
        }
        
        answer = new int[cnt][3];
        for(int x = 0; x <= n; x++) {
            for(int y = 0; y <= n; y++) {
                if(pillar[y][x]) {
                    answer[idx][0] = x;
                    answer[idx][1] = y;
                    answer[idx++][2] = PILLAR;
                }
                if(girder[y][x]) {
                    answer[idx][0] = x;
                    answer[idx][1] = y;
                    answer[idx++][2] = GIRDER;
                }
            }
        }
        
        return answer;
    }
    
    private boolean isBuild(int type, int y, int x) {
        if(type == PILLAR) {
            if(y == 0 || pillar[y - 1][x] || girder[y][x] || (x - 1 >= 0 && girder[y][x - 1]) ) 
                return true;
            
            return false;
        }
        
        if( (pillar[y - 1][x] || pillar[y - 1][x + 1]) || (x - 1 >= 0 && girder[y][x - 1] && girder[y][x + 1]) ) 
            return true;
        
        return false;
    }
    
    private boolean isDestroy(int type, int y, int x) {
        if(type == PILLAR) {
            // 파괴 후 검증
            pillar[y][x] = false;
            // 이어진 기둥이나 보가 있다면 문제없는지 설치 메서드를 통해 검증
            if( ( pillar[y + 1][x] && !isBuild(PILLAR, y + 1, x) )
               || ( girder[y + 1][x] && !isBuild(GIRDER, y + 1, x ) )
               || ( x - 1 >= 0 && girder[y + 1][x - 1] && !isBuild(GIRDER, y + 1, x - 1 ) ) 
              ) {
                // 문제 있으면 되돌리기
                pillar[y][x] = true;
                return false;
            }
            
            return true;
        }
        
        girder[y][x] = false;
        // 이어진 기둥이나 보가 있다면 문제없는지 설치 메서드를 통해 검증
        if( ( pillar[y][x] && !isBuild(PILLAR, y, x) )
           || ( pillar[y][x + 1] && !isBuild(PILLAR, y, x + 1) )
           || ( x - 1 >= 0 && girder[y][x - 1] && !isBuild(GIRDER, y, x - 1 ) )
           || ( girder[y][x + 1] && !isBuild(GIRDER, y, x + 1) ) 
           ) {
            // 문제 있으면 되돌리고 false
            girder[y][x] = true;
            return false;
        }
        
        return true;
    }
}