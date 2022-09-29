class Solution {
    public int[] solution(int n) {
        int[] dy = new int[]{ 1, 0, -1 };
        int[] dx = new int[]{ 0, 1, -1 };
        int[][] matrix = new int[n][n];
        
        int y = -1, x = 0, num = 1, idx = 0, cnt = n;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < cnt; j++) {
                y += dy[idx];
                x += dx[idx];
                matrix[y][x] = num++;
            }
            cnt--;
            if(++idx > 2) idx = 0;
        }
        
        int[] answer = new int [--num];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j >= 0; j--) {
                answer[--num] = matrix[i][j];
            }
        }
        
        return answer;
    }
}