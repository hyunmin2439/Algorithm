class Solution {
    public int solution(int n) {
        int cnt = Integer.bitCount(n);
        while(cnt != Integer.bitCount(++n));
        return n;
    }
}