package solved;

import java.io.*;

/*
 * 입력
 * N (int <= 100)
 * 1 (String : 알파벳 소문자, length <= 100)
 * 2
 * ...
 * N
 * 
 * kina k, i, n, a이 연속, 그룹 단어 O
 * aabbbccb는 b가 떨어짐, 그룹 단어 X
 * 
 * 출력
 * 그룹단어 개수 출력
 */

// int[] alpha 배열 => aaab b로 바뀌면 a위치 -1
public class BJ_1316_그룹단어체커 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n, result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cnt = 0;
		n = Integer.parseInt( br.readLine().trim() );
		
		while(cnt < n) {
			String line = br.readLine().trim();
			int length = line.length();
			//System.out.print(line + ":" +length);
			
			if(length > 1) {
				boolean check = true;
				int alpha[] = new int[26];

				for(int i = 1; i < length; i++) {
					int prv = line.charAt(i - 1) - 97; // 이전 문자
					int pre = line.charAt(i) - 97; // 현재 문자

					if( prv != pre) {
						if( alpha[prv] == -1 || alpha[pre] == -1) {
							check = false;
							break;
						}
						else alpha[prv] = -1;
					}
					prv = pre;
				}
				if(check) result++;
				
			}
			else {
				// 문자열 길이가 0면 증가x, 1이면 증가
				if(length == 1) result++;
			}
			//System.out.println(", result:" + result);
			cnt++;
		}
		
		
		bw.write(result + "");
		br.close();
		bw.close();
	}

}
