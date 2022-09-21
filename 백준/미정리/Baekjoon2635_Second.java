package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수 이어가기
// 입력 N이 주어지면 임의의 양의 정수 X에 대하여
// 1(N) 2(N - X) 3(N - (N - X) => X) 4(N - X - X) ...
// 위의 규칙으로 이어져 간다고 할 때,
// 음의 정수가 되기 전까지의 수열의 길이 중,
// 가장 긴 길이를 출력하고, 이 수열을 차례대로 출력하라.
// 최대 개수 여러개이면 아무거나 출력

// 첫 번째, 마지막 수열 출력을 위해 List를 사용, 
// 출력을 위해 list를 사용할 필요 없음
// X를 구하고 위의 규칙에 따라 숫자를 감소해 나가며 계속 출력.

// List를 사용한 것보다 공간, 시간 복잡도 증가
// 자원 소모 : 출력을 위한 연산 > List 사용

// // memory : 16420KB / time:172ms
public class Baekjoon2635_Second {
	
	static int N, X, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		// X가 N/2보다 작을 경우 수열의 길이는 3
		for (int i = N / 2; i <= N; i++) {
			int len = maxArray(i);
			if( max < len ) {
				X = i;
				max = len;
			}
		}
		
		System.out.println(max + "\n" + printArray());
	}
	
	private static int maxArray(int x) {
		int len = 2;
		int[] num = {N, x, N - x};
		
		while( num[2] >= 0 ) {
			len++;
			
			num[0] = num[1];
			num[1] = num[2];
			num[2] = num[0] - num[1];
		}
		
		return len;
	}
	
	private static String printArray() {
		StringBuilder sb = new StringBuilder();
		sb.append(N).append(" ").append(X).append(" ");
				
		int[] num = {N, X, N - X};
		
		while( num[2] >= 0 ) {
			sb.append(num[2]).append(" ");
			
			num[0] = num[1];
			num[1] = num[2];
			num[2] = num[0] - num[1];
		}
		
		return sb.toString();
	}
}
