class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length, M = board[0].length, answer = 0;
        int r1, c1, r2, c2, degree;
        int[][] accBoard = new int[N][M];
        
        for(int i = 0; i < skill.length; i++) {
            r1 = skill[i][1];
            c1 = skill[i][2];
            r2 = skill[i][3] + 1;
            c2 = skill[i][4] + 1;
            degree = (skill[i][0] == 1 ? -1 : 1) * skill[i][5];
            
            accBoard[r1][c1] += degree;
            if(r2 < N) accBoard[r2][c1] -= degree;
            if(c2 < M) accBoard[r1][c2] -= degree;
            if(r2 < N && c2 < M) accBoard[r2][c2] += degree;
        }
        
        for(int r = 1; r < N; r++)
            accBoard[r][0] += accBoard[r - 1][0];
        
        for(int c = 1; c < M; c++)
            accBoard[0][c] += accBoard[0][c - 1];
        
        for(int r = 1; r < N; r++) {
            for(int c = 1; c < M; c++) {
                accBoard[r][c] += accBoard[r - 1][c] + accBoard[r][c - 1] - accBoard[r - 1][c - 1];
            }
        }
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(board[r][c] + accBoard[r][c] > 0) answer++;
            }
        }
        
        return answer;
    }
}