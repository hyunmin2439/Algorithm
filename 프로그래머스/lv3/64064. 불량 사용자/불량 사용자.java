class Solution {
    
    private static boolean isCorrect;
    private static String[] userId, bannedId;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0, flag = -1, endFlag = (int)Math.pow(2, user_id.length), n = user_id.length, r = banned_id.length;
        userId = user_id;
        bannedId = banned_id;
        
        while(flag++ < endFlag) {
            if(Integer.bitCount(flag) != banned_id.length) continue;
            
            perm(new int[r], new boolean[n], n, r, flag, 0);
            
            if(isCorrect) answer++;
            
            isCorrect = false;
        }
        
        return answer;
    }
    
    private void perm(int[] ordered, boolean[] selected, int n, int r, int flag, int idx) {
        if(isCorrect) return; // 이미 맞는 케이스로 판단 났으면
        
        if(idx == r) {
            isCorrect = check(ordered);
            return;
        }
        
        for(int i = 0; i < n; i++) {
            if((flag & 1 << i) == 0 || selected[i]) continue;
            
            ordered[idx] = i;
            selected[i] = true;
            perm(ordered, selected, n, r, flag, idx + 1);
            selected[i] = false;
        }
    }
    
    /**
     * 맞는 케이스인지 판단하는 메서드
     */
    private boolean check(int[] ordered) {
        for(int i = 0; i < ordered.length; i++) {
            if( !isSame(userId[ ordered[i] ], bannedId[i]) ) 
                return false;
        }
        
        return true;
    }
    
    /**
     * 두 아이디가 일치 하는지 판단하는 메서드
     */
    private boolean isSame(String id, String ban) {
        if(id.length() != ban.length())
            return false;
        
        for(int i = 0; i < id.length(); i++) {
            if(ban.charAt(i) == '*' || ban.charAt(i) == id.charAt(i))
                continue;
            
            return false;
        }
        
        return true;
    }
    
}