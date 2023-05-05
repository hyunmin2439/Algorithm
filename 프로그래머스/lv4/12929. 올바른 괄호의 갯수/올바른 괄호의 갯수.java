class Solution {
    public int solution(int n) {
        return dfs(n, n, 0, 0);
    }
    
    private int dfs(int remainLeft, int remainRight, int leftCnt, int rightCnt) {
        if(leftCnt < rightCnt || remainRight < 0) return 0;
        
        if(remainLeft == 0) {
            return 1;
        }
        
        return dfs(remainLeft - 1, remainRight, leftCnt + 1, rightCnt) + dfs(remainLeft, remainRight + 1, leftCnt, rightCnt + 1);
    }
}