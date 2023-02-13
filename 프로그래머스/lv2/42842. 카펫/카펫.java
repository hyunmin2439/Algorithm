class Solution {
    public int[] solution(int brown, int yellow) {
        boolean check = true;
        int x = 0, y = 1;
        
        while(check) {
            x++;
            y = 1;
            for(y = 1; y <= x; y++) {
                if(2 * y + 2 * (x - 2) == brown 
                   && (y - 2) * (x - 2) == yellow) {
                    check = false;
                    break;
                }
            }
        }
        
        return new int[]{x, y};
    }
}