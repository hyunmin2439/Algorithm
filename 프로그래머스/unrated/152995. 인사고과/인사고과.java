import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = new int[]{ scores[0][0], scores[0][1] };
        int wanhoScore = wanho[0] + wanho[1], max = 0, rank = 1;
        
        if(scores.length == 1) return 1;
        
        Arrays.sort(scores, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        
        for(int i = 0; i < scores.length; i++) {
            if(max > scores[i][1]) {
                if(wanho[0] == scores[i][0] && wanho[1] == scores[i][1]) return -1;
                scores[i][0] = -1;
                scores[i][1] = -1;
            }
            else max = scores[i][1];
            
            if(wanhoScore < scores[i][0] + scores[i][1])
                rank++;
        }
        
        return rank;
    }
}