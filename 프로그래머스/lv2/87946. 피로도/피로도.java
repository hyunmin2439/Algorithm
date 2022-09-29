class Solution {
    
    private int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        getMaxDungeon(dungeons, new boolean[dungeons.length], k, 0);
        return answer;
    }
    
    private void getMaxDungeon(int[][] dungeons, boolean[] visited, int hp, int cnt) {
        for(int i = 0; i < dungeons.length; i++) {
            if(visited[i] || dungeons[i][0] > hp) continue;
            
            visited[i] = true;
            getMaxDungeon(dungeons, visited, hp - dungeons[i][1], cnt + 1);
            visited[i] = false;
        }
        
        answer = Math.max(answer, cnt);
    }
}