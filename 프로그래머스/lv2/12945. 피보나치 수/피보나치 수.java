class Solution {
    public int solution(int n) {
        int first = 0, second = 1, third = 0, MOD = 1234567;
        
        while(n-- > 1) {
            third = (first + second) % MOD;
            first = second;
            second = third;
        }
        
        return third;
    }
}