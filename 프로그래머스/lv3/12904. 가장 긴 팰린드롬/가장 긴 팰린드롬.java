class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        char[] str = s.toCharArray();
        
        for(int start = 0; start < str.length - 1; start++) {
            for(int end = start + 1; end < str.length; end++) {
                if(isPalindrome(str, start, end))
                    answer = Math.max(answer, end - start + 1);
            }
        }
        return answer;
    }
    
    private boolean isPalindrome(char[] str, int start, int end) {
        int mid = (start + end) / 2;
        
        if(end - start < 2 && str[start] != str[end])
            return false;
        
        for(int i = start; i <= mid; i++) {
            if(str[start++] != str[end--])
                return false;
        }

        return true;
    }
}