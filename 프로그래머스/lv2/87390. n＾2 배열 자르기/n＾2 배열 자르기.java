class Solution {
    public int[] solution(int n, long left, long right) {
        return makeArray(n, left, right);
    }
    
    private int[] makeArray(int n, long left, long right) {
        long y = n - 1, x = 0, idx = -1, len = right - left + 1;
        int[] answer = new int[(int)len];
        
        while(y * n > left) y--;
        
        x = left - y * n;
        
        while(++idx < len) {
            // 2차원 배열을 생성할 필요가 없다.
            // y, x 중 높은 값 + 1을 하면 그 행렬에 들어있을 번호
            answer[(int)idx] = (int)(Math.max(y, x) + 1);
            
            if(++x >= n) {
                x = 0;
                y++;
            }
        }
        
        return answer;
    }
}
    