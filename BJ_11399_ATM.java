package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ATM
// 정렬하고 누적합을 구하는 문제

// memory:14412KB / time:152ms
public class BJ_11399_ATM {

	static int N, sum, res; // 사람의 수, 누적합, 결과값
	static int[] time; // 사람들의 돈 인출시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time);
		
		for (int i = 0; i < N; i++) {
			sum += time[i];
			res += sum;
		}
		
		System.out.print(res);
		br.close();
	}

}
