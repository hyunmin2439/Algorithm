package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 모든 문자가 같은지 검사하기 위하여
 * 
 * answer[j] = ( charSum == strArr[0][j] * N ) ? strArr[0][j] : '?';
 * 
 * 단어의 각 자리를 더해 그 값이 첫번째 단어 * 단어수와 같으면 전부 같은 단어,
 * 
 * 아니면 다른 단어가 있기 때문에 ?를 넣는 방식으로 풀어서 예제는 다 맞았으나,
 * 
 * 틀렸습니다가 나왔다. 이유는 b(98) + a(97) + c(99) == b(98) + b(98) + b(98)
 * 
 * 위와 같은 경우가 있기 때문에 틀렸다고 나옴. 때로는 단순하게 푸는것이 최고.
 * 
 * Memory:14248KB / Time:132ms
 */

public class BJ_1032_명령프롬프트_1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		char[][] strArr = new char[N][];
		
		for(int i = 0; i < N; i++) {
			strArr[i] = in.readLine().toCharArray();
		}
		
		char[] answer = new char[ strArr[0].length ];
		for(int j = 0; j < strArr[0].length; j++) {
			boolean isSameChar = true;
			
			for(int i = 0; i < N - 1; i++) {
				if(strArr[i][j] != strArr[i + 1][j]) {
					isSameChar = false;
					break;
				}
			}
			
			answer[j] = isSameChar ? strArr[0][j] : '?';
		}
		
		System.out.print(answer);
		
		in.close();
	}

}
