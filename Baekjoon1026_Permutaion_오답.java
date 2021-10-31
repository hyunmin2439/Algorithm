package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 단순히 순열 완탐으로 푸니 시간초과 */

public class BJ_1026_보물_1_fail_Permutaion {
    
    private static int N, S;
    private static int[] A, B;
    private static boolean[] used;
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(in.readLine());
        
        A = new int[N];
        B = new int[N];
        used = new boolean[N];
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        S = Integer.MAX_VALUE;
        perm(0, 0);
        
        System.out.println(S);
    }
    
    private static void perm(int idx, int sum) {
        if(sum >= S) return;
    	
    	if(idx == N) {
            S = Math.min(S, sum);
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(used[i]) continue;
            used[i] = true;
            perm(idx + 1, sum + A[i] * B[idx]);
            used[i] = false;
        }
    }
}
