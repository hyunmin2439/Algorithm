class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        int[] cnt = new int[food.length + 1];
        
        for(int i = 1; i < food.length; i++) {
            cnt[i] = food[i] / 2;
        }
        
        for(int i = 1; i < cnt.length; i++) {
            for(int j = 0; j < cnt[i]; j++) {
                sb.append(i);
            }
        }
        
        sb.append(0);
        
        for(int i = cnt.length - 1; i >= 1; i--) {
            for(int j = 0; j < cnt[i]; j++) {
                sb.append(i);
            }
        }
        
        return sb.toString();
    }
}