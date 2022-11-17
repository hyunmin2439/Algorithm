import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int idx = 0, len = routes.length, pos = 0, answer = 0;
        
        Arrays.sort(routes, new Comparator<int[]>() {
           @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        pos = routes[idx][1];
        answer++;
        
        while(++idx < len) {
            if( !(routes[idx][0] <= pos && pos <= routes[idx][1]) ) {
                pos = routes[idx][1];
                answer++;
            }
        }
        
        return answer;
    }
}