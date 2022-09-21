package uploaded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// Memory : 72308KB  time : 660ms
public class Baekjoon2493 {
	
	static int N, idx, hIdx, top = -1;
	
	static int[] topList;
	static int[] hList; // stack
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		topList = new int[N + 1]; // 0 : dummy
		hList   = new int[N];
		
		st = new StringTokenizer(br.readLine());		
		
		while(N-- > 0) {
			// 탑 번호 증가
			idx++; 
			// 탑 높이 입력
			topList[idx] = Integer.parseInt(st.nextToken());
			// 전파 도달하는 위치 찾기
			findWaveDest();
			// 출력
			sb.append(hIdx).append(" ");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void findWaveDest() {
		// 바로 앞의 탑 > 현재 위치의 탑
		if(topList[idx - 1] > topList[idx]) {
			hIdx = idx - 1;
			hList[++top] = hIdx; // hList에 담음
			return;
		}
		// 위 조건에 걸리지 않을 시
		for (int i = top; i >= 0; i--) {
			hIdx = hList[top--]; 
			
			if(topList[hIdx] > topList[idx]) {
				hList[++top] = hIdx; return;
			}
			
			hIdx = 0;
		}
	}
}

/*
Stack 변화 예시 : findWaveDest() 함수 이해

n번탑     n번탑
 1.hList [ 0 ]    / 0 : dummy
 
 2.hList [  ]     / pop0 -> t0 < t2
 
 3.hList [ 2 ] 	  / t2 > t3 -> push2
 
 4.hList [ 2 ] 	  / pop2 -> t2 > t4 -> push2
 
 5.hList [ 2, 4 ] / t4 > t5 -> push4
 
 6.hList [ 2 ]    / pop4 -> t4 < t6 -> pop2 -> t2 > t6 -> push2
 
 7.hList [ 2, 6 ] / t6 > t7 -> push6
 
 6 ~ 7번 경우에서 보면 Stack에 들어있던 4번 탑은 6번 탑보다 낮기 때문에 
 7번 탑 이후 부터는 4번 탑에 신호가 도달할리가 없음. 이유는 6번에서 다 걸리기 때문에.
 때문에 Stack에서 pop하여 검사를 하고 자신보다 높을 경우에만 다시 push를 한다.
   
	-----l            
	     l-----------l
             l-----l     l
             l     l     l--l
        --l  l     l     l  l
          l  l     l--l  l  l
          l  l--l  l  l  l  l
Index	  1  2  3  4  5  6  7
dest pos  0  0  2  2  4  2  6
*/
