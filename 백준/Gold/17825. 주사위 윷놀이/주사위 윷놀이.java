import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 도착지점을 지났을 때 경로가 겹치지 않게 도착지점 5칸 여유롭게 설정( 주사위 수 최대 5 )
		int[] pan = new int[] { 
			//  0  1  2  3  4  5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20 21 22 23 24
				0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, 0, 0, 0, // 0 ~ 24
			//  25  26  27  28  29  30  31  32  33
				10, 13, 16, 19, 25, 30, 35, 40,  0, // 25 ~ 33
			//  34  35  36  37  38  39  40  41  
				20, 22, 24, 25, 30, 35, 40, 0, // 34 ~ 41
			//  42  43  44  45  46  47  48  49 50 51 52 53 54
				30, 28, 27, 26, 25, 30, 35, 40, 0, 0, 0, 0, 0 // 42 ~ 54
		};
		
		int[] numbers = new int[10];
		for(int i = 0; i < 10; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		permu(pan, numbers, new int[10], 10, 0);
		
		System.out.println(answer);
	}
	
	private static void permu(int[] pan, int[] numbers, int[] select, int N, int idx) {
		if(idx == N) {
			play(pan, numbers, select, N);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			select[idx] = i;
			
			permu(pan, numbers, select, N, idx + 1);
		}
	}
	
	private static void play(int[] pan, int[] numbers, int[] select, int N) {
		int totScore = 0, horse[] = new int[4];
		
		for(int i = 0; i < N; i++) {
			// 탈출한 말이면 넘기기
			if( horse[ select[i]] >= 50 ) continue;
			
			horse[ select[i] ] += numbers[i];
			
			switch(horse[ select[i] ]) {
				case 5:
					horse[ select[i] ] = 25;
					break;
				case 10: 
					horse[ select[i] ] = 34;
					break;
				case 15: 
					horse[ select[i] ] = 42;
					break;
				case 29: case 37: 
					horse[ select[i] ] = 46;
					break;
				case 30: case 38: 
					horse[ select[i] ] = 47;
					break;
				case 31: case 39: // 35
					horse[ select[i] ] = 48;
					break;
				case 20: case 32: case 40: // 40
					horse[ select[i] ] = 49;
					break;
				case 21: case 22: case 23: case 24: case 33: case 41: // 도착
					horse[ select[i] ] = 50;
					break;
			}

			// 위치가 겹치는 방법은 잘못된 방법
			if( horse[ select[i]] < 50 && isSamePos(horse, select[i], horse[ select[i] ]) ) return;
			
			totScore += pan[ horse[select[i]] ];
		}
		
		answer = Math.max(answer, totScore);
	}
	
	private static boolean isSamePos(int[] horse, int curr, int pos) {
		for(int i = 0; i < horse.length; i++) {
			if(curr != i  && pos == horse[i]) return true;
		}
		
		return false;
	}
}