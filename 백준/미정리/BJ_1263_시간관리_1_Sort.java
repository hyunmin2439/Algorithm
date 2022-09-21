import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Greedy + Sort
 * 
 * 일을 끝 마쳐야 하는 시간 순으로 정렬을 하고
 * 
 * 정렬된 첫 번째 일 마감시간 - 걸리는 시간을 시작으로
 * 
 * for문을 돌려서 마지막까지 구해지지 않으면 -1
 * 
 * 중간에 구해지면 그것이 startTime으로 출력을 하게 풀었다.
 * 
 * Memory: 14588KB / Time:156ms
 */

public class BJ_1263_시간관리_1_Sort {

	static int N, startTime;
	
	static Job[] jobList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		startTime = -1;
		N = Integer.parseInt(in.readLine());
		jobList = new Job[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int T = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			jobList[i] = new Job(T, S);
		}
		
		Arrays.sort(jobList);
		
		// 이제 일 끝내는 시간을 어떻게 할 것이냐
		for(int i = jobList[0].S - jobList[0].T; i >= 0; i--) {
			boolean jobEnd = true;
			int time = i;
			
			for(int j = 0; j < N; j++) {
				time += jobList[j].T;
				if( time > jobList[j].S ) {
					jobEnd = false;
					break;
				}
			}
			
			if( jobEnd ) {
				startTime = i;
				break;
			}
		}
		
		System.out.println( startTime );
		
		in.close();
	}

	static class Job implements Comparable<Job> {
		int T, S; // T:걸리는 시간, S:끝내야 하는 시간

		public Job(int T, int S) {
			super();
			this.T = T;
			this.S = S;
		}

		@Override
		public int compareTo(Job other) {
			return this.S - other.S;
		}

	}
}
