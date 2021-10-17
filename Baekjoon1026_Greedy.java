package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* Greedy 
 * 
 * S = A[0] × B[0] + ... + A[N-1] × B[N-1]
 * 
 * 조건1: S의 값을 가장 작게 만들기 위해 A의 수를 재배열하자. 
 * 
 * 조건2: 단, B에 있는 수는 재배열하면 안된다.
 * 
 * B를 재배열하면 안된다고 되어있지만 단순하게 생각해서
 * 
 * A와 B를 곱하는 것이니 A와 B를 정렬해서 반대로 곱셈
 * 
 * Memory:14324KB / Time:132ms
 * 
 * */

public class Baekjoon1026_Greedy {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(in.readLine());
        
        int[] A = new int[N];
        int[] B = new int[N];
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        // 배열 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        
        int S = 0;
        for (int i = 0; i < N; i++) {
			S += A[i] * B[N - 1 - i];
		}
        
        System.out.println(S);
    }
    
}
