import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int left = 0, right = people.length - 1, answer = 0;
        
        Arrays.sort(people);
        
        while(left <= right) {
            answer++;
            
            if(people[left] + people[right] <= limit) {
                left++;
                right--;
            }
            else right--;
        }
        
        return answer;
    }
}