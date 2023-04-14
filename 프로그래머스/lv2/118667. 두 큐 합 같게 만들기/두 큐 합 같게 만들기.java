import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0, size = queue1.length * 3;
        int[] idx = new int[2], len = new int[2];
        int[][] queue = new int[2][size];
        long[] sum = new long[2];
        
        len[0] = len[1] = queue1.length;
        
        for(int i = 0; i < len[0]; i++) {
            queue[0][i] = queue1[i];
            sum[0] += queue1[i];
            queue[1][i] = queue2[i];
            sum[1] += queue2[i];
        }

        if( (sum[0] + sum[1]) % 2 != 0 ) return -1;
        
        while(true) {
            if( !(idx[0] < len[0] && idx[1] < len[1])
               || !(len[0] < size && len[1] < size)
               || sum[0] == sum[1] ) break;
            
            if(sum[0] > sum[1]) {
                queue[1][len[1]] = queue[0][idx[0]];
                
                
                sum[0] -= queue[0][idx[0]++];
                sum[1] += queue[1][len[1]++];
                
                queue[0][idx[0] - 1] = 0;
            }
            else {
                queue[0][len[0]] = queue[1][idx[1]];
                
                
                sum[0] += queue[0][len[0]++];
                sum[1] -= queue[1][idx[1]++];
                
                queue[1][idx[1] - 1] = 0;
            }
            
            answer++;
        }
            
        return sum[0] != sum[1] ? -1 : answer;
    }
}