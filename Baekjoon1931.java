package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Grid 알고리즘
// 회의종료 시각으로 정렬,
// 회의종료 시간이 같을 경우 회의 시작시간으로 정렬
// 차례대로 조건이 맞을 경우 cnt++
public class Baekjoon1931 {

	static int N, meeting[][];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		meeting = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meeting[i][0] = Integer.parseInt(st.nextToken());
			meeting[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(meeting, (a, b) -> {
			if(a[1] != b[1]) return a[1] - b[1];
			else return a[0] - b[0];
		});
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(meeting[i][0] + " " + meeting[i][1]);
//		}
		
		System.out.print(checkSchedule());
		
		br.close();
	}

	private static int checkSchedule() {
		int cnt = 1; // 첫 회의
		int endTime = meeting[0][1];
		
		for(int i = 1; i < N; i++) {
			if(endTime <= meeting[i][0]) {
				cnt++;
				endTime = meeting[i][1];
			}
		}
		
		return cnt;
	}

}
