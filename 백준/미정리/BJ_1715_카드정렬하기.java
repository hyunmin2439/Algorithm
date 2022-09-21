package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 카드 수가 가장 작은 묶음과 그 다음 작은 묶음 더하기
// 이 과정을 반복. 이를 위해 우선순위 큐를 사용하여
// 값이 가장 작은 두 수를 더하고 다시 넣기
// queue의 사이즈가 1이 될 때까지 반복
public class BJ_1715_카드정렬하기 {
	
	static int N;
	static long cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> queue = new PriorityQueue<>( (a, b) -> a - b );
		for (int i = 0; i < N; i++) {
			queue.offer( Integer.parseInt(br.readLine()) );
		}

		while (queue.size() > 1) {
			Integer temp = queue.poll() + queue.poll();
			cnt += temp;
			queue.offer(temp);
		}

		System.out.println(cnt);
		br.close();
	}

}
