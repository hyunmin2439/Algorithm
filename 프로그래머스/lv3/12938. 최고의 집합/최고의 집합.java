class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(n > s) return new int[]{-1};
        
        for(int i = 0; i < n; i++) {
            answer[i] = s / n;
        }
        
        s -= (s / n) * n;
        
        for(int i = n - 1; i >= 0 && s > 0; i--) {
            answer[i]++;
            s--;
        }
        
        return answer;
    }
}