class Solution {
    public int solution(String[] board) {
        int[][] cell = new int[3][3];
        int[] cnt = new int[5];
        int num = 0;
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                num = board[y].charAt(x);
                num = num == '.' ? 0 : (num == 'O' ? 1 : 4);
                cell[y][x] = num;
                cnt[num]++;
            }
        }
        
        if(cnt[1] < cnt[4] || (cnt[1] != cnt[4] && cnt[1] - 1 != cnt[4]) ) return 0;
        
        // 대각선
        num = 0;
        for(int i = 0; i < 3; i++) {
            num += cell[i][i];
        }
        if(num == 3 && cnt[1] == cnt[4]) return 0;
        if(num == 12 && cnt[1] > cnt[4]) return 0;

        // 대각선
        num = 0;
        for(int i = 0; i < 3; i++) {
            num += cell[i][2 - i];
        }
        if(num == 3 && cnt[1] == cnt[4]) return 0;
        if(num == 12 && cnt[1] > cnt[4]) return 0;

        for(int i = 0; i < 3; i++) {
            // 가로
            num = 0;
            for(int x = 0; x < 3; x++) {
                num += cell[i][x];
            }

            if(num == 3 && cnt[1] == cnt[4]) return 0;
            if(num == 12 && cnt[1] > cnt[4]) return 0;
            
            // 세로
            num = 0;
            for(int y = 0; y < 3; y++) {
                num += cell[y][i];
            }

            if(num == 3 && cnt[1] == cnt[4]) return 0;
            if(num == 12 && cnt[1] > cnt[4]) return 0;
        }
        
        return 1;
    }

}