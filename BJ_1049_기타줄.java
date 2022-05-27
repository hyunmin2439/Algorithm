import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1049_기타줄 {
    
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int pack = Integer.MAX_VALUE, one = Integer.MAX_VALUE;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            
            pack = Math.min( pack, Integer.parseInt(st.nextToken()) );
            one = Math.min( one, Integer.parseInt(st.nextToken()) );
        }
        
        // 3가지 케이스로 나뉨
        // 1. 6개 pack이 가장 싼 경우 6개 기타줄만 모두 구입
        // 2. 1개의 기타줄 가격이 가장 싼 경우 1개 기타줄로만 모두 구입
        // 3. 섞어서 사는 것이 싼 경우
        
        int allPack = (N % 6 == 0 ? N / 6 : N / 6 + 1) * pack;
        int allOne = N * one;
        int packOne = (N / 6 * pack) + ( (N % 6) * one );
        
        System.out.println( Math.min(allPack, Math.min(allOne, packOne)) );
    }
}