import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 최대값을 묻는 문제임으로 알파벳 수가 10개 이하일 땐
 * 
 * 큰 수부터 대입하는 것이 더 유리한 방식.
 * 
 * 다른 숏코딩 케이스들 보다 속도가 매우 느림.
 * 
 * 다시 풀어볼 필요가 있음.
 * 
 * 15780KB / Time:744ms
 */

public class BJ_1339_단어수학_1_Permutation {
	
	static int N, maxOfSum, numOfUsedAlphabet;
	
	static char[][] words;
	static int[] numberToAlphabet;
	static boolean[] usedAlphabet;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());

		words = new char[N][];
		numberToAlphabet = new int[26]; // 알파벳에 대응되는 숫자
		usedAlphabet = new boolean[26]; // 사용하는 알파벳
		
		for(int i = 0; i < N; i++) {
			words[i] = in.readLine().toCharArray();
			
			for(int j = 0; j < words[i].length; j++) {
				// 숫자로 변경된 알파벳에 또 'a'를 빼면 음수가 나옴, 이미 사용한다고 표시된 것
				if( words[i][j] - 'A' < 0 ) continue;
				
				words[i][j] -= 'A'; // 숫자로 바꿔주기				
					
				if( usedAlphabet[ words[i][j] ] ) continue; // 이미 방문한 알파벳 넘어감

					usedAlphabet[ words[i][j] ] = true; // 사용한다고 표시
				numOfUsedAlphabet++; // 사용된 알파벳 수 증가
			}
			
		}
		
		permu(0, 9);
		
		System.out.println(maxOfSum);
	}
	
	private static int calc() {
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			int num = 0;
			
			for(int j = 0; j < words[i].length; j++) {
				num += numberToAlphabet[ words[i][j] ];
				num *= 10;
			}
			
			num /= 10; // 마지막에 10이 한번 더 곱해지므로 10 나눠주기
			
			sum += num;
		}
		
		return sum;	
	}
	
	private static void permu(int idx, int number) {
		if(idx == numOfUsedAlphabet) {
			maxOfSum = Math.max(maxOfSum, calc());
			return;
		}
		
		for(int i = 0; i < 26; i++) {
			if( !usedAlphabet[i] ) continue;
			
			usedAlphabet[i] = false; // visited 배열로 활용
			
			numberToAlphabet[i] = number; // 숫자 대입
			
			permu(idx + 1, number - 1); // 재귀
			
			// numberToAlphabet[i] = 0; // 숫자는 덮어 씌워지기 때문에 해제 불필요
			
			usedAlphabet[i] = true; // visited 해제
			
		}
	}
}
