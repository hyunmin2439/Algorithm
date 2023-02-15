class Solution
{
    public int solution(int n, int a, int b) {
        int tmp, answer = 1;
        
        if(a > b) {
            tmp = a;
            a = b;
            b = tmp;
        }
        
        while( !(a + 1 == b && (a & 1) == 1 && (b & 1) == 0) ) {
            a = ((a & 1) == 0 ? a : a + 1) >> 1;
            b = ((b & 1) == 0 ? b : b + 1) >> 1;
            answer++;
        }
        
        return answer;
    }
}