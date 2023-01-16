import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int left = 0, right = B.length - 1, answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i = A.length - 1; i >= 0; i--) {
            if(A[i] >= B[right]) left++;
            else {
                answer++;
                right--;
            }
        }
        
        return answer;
    }
}