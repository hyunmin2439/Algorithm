import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:35584KB / Time:392ms
public class Baekjoon13305 {

	static int N;
	
	static long minValueOfOil, minCost;
	static long[] distance;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 마지막 도시 기름 가치 읽고 버림, N - 1 값이 더 의미 있음
		N = Integer.parseInt(in.readLine()) - 1;
		minValueOfOil = Long.MAX_VALUE;
		distance = new long[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			long cost = Long.parseLong(st.nextToken());
			
			if(minValueOfOil > cost)
				minValueOfOil = cost;
			
			minCost += minValueOfOil * distance[i];
		}
		
		st.nextToken(); // 마지막 도시 기름가격 읽고 버리기
		
		System.out.println(minCost);
		in.close();
	}

}
