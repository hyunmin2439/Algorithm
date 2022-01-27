package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 발생했던 문제점
 * 
 * for(int i = 3; i < end; i += 2)
 * 
 * 소수 판별하는 메서드에서 end까지 체크를 해야 하는데
 * 
 * =을 빠트려서 디버깅하는데 시간이 좀 걸렸다.
 * 
 * Memory:14444KB / Time:136ms
 */

public class BJ_1963_소수경로_BFS {
	
	private static int T, from[], to[];

	public static void main(String[] args) throws Exception {
		input();
		
		solve();
	}
	
	private static void solve() {
		Queue<Prime> queue = new LinkedList<>();
		
		for(int i = 0; i < T; i++) {
			int res = bfs(queue, from[i], to[i]);
			
			System.out.println(res != -1 ? res : "Impossible");
		}
	}
	
	private static int bfs(Queue<Prime> queue, int from, int to) {
		boolean[] visited = new boolean[10000];

		queue.clear();
		queue.offer(new Prime(from, 0));
		visited[from] = true;
		
		while( !queue.isEmpty() ) {
			Prime prime = queue.poll();
			
			// 찾았으면 횟수 return하고 종료
			if(prime.num == to) {
				return prime.cnt;
			}
			
			for(int i = 0; i <= 3; i++) {
				int digit = (int)Math.pow(10, i);
				// 시작하자 마자 digit을 더하기 때문에 0일 경우도 필요하므로 미리 빼놓기
				int tmpNum = changeXDigitsToZero(prime.num, digit) - digit;
				
				for(int j = 0; j <= 9; j++) {
					tmpNum += digit;
					
					// 방문했거나 1000 이하의 숫자면
					if( visited[tmpNum] || tmpNum < 1000 ) continue;
					
					// 방문해서 소수가 아닌 숫자도 방문했다고 표시해야
					// 소수 판단 메서드 반복 계산 작업 줄일 수 있음
					// 때문에 소수이든 소수가 아니든 방문 표시
					visited[tmpNum] = true;
					
					// 소수인지 판단하여 소수가 아니면
					if( !isPrimeNumber(tmpNum) ) continue;
					
					queue.offer(new Prime(tmpNum, prime.cnt + 1));
				}
			}
		}
		
		return -1;
	}
	
	// 각 자리 숫자를 10으로 바꿔주는 메서드
	private static int changeXDigitsToZero(int num, int digit) {
		return num - (num / digit) % 10 * digit;
	}
	
	// 소수인지 판단하는 메서드
	private static boolean isPrimeNumber(int num) {
		int end = (int)Math.sqrt(num);
		
		if(num % 2 == 0) return false;
		
		for(int i = 3; i <= end; i += 2) {
			if(num % i == 0) return false;
		}
		
		return true;
	}

	private static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		from = new int[T];
		to = new int[T];
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			from[i] = Integer.parseInt(st.nextToken());
			to[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
	}

	static class Prime {
		int num, cnt;
		
		public Prime(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}
