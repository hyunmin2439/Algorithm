class Solution {
    
    private int answer = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        dfs(picks, minerals, 0, 0, 0);
        return answer;
    }
    
    private void dfs(int[] picks, String[] minerals, int idx, int pick, int acc) {
        if(idx >= minerals.length) {
            answer = Math.min(answer, acc);
            return;
        }
        
        if(idx % 5 != 0) {
            dfs(picks, minerals, idx + 1, pick, acc + getFatigue(minerals[idx], pick));
            return;
        }
        
        if(picks[0] + picks[1] + picks[2] == 0) {
            answer = Math.min(answer, acc);
            return;
        }
        
        for(int i = 0; i < 3; i++) {
            if(picks[i] == 0) continue;

            picks[i]--;
            dfs(picks, minerals, idx + 1, i, acc + getFatigue(minerals[idx], i));
            picks[i]++;
        }
    }
    
    private int getFatigue(String mineral, int pick) {
        switch(mineral) {
            case "diamond": 
                return pick == 0 ? 1 : (pick == 1 ? 5 : 25);
            case "iron":
                return pick != 2 ? 1 : 5;
            default:
                return 1;
        }
    }
}