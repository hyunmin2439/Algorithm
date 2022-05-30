import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

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
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int S = 0;
        for (int i = 0; i < N; i++) {
			S += A[i] * B[N - 1 - i];
		}
        
        System.out.println(S);
    }
    
}