package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Memory:17900KB / Time:284ms
 */

public class BJ_1153_네개의소수 {
	
	private static boolean isFind;
	private static int N, size;
	private static List<Integer> primeList;
	
	public static void main(String[] args) throws Exception {
		input();
		
		initPrimeNumber();
		
		dfs(new int[4], 0, 0, N);
		
		if( !isFind ) 
			System.out.print(-1); // 최종 못 찾았으면 -1 출력
	}

	private static int input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());

		in.close();
		
		return N;
	}
	
	private static void initPrimeNumber() {
		primeList = new ArrayList<>();
		
		// 소수는 2말고는 전부 홀수
		int startNum = N % 2 == 0 ? N - 1 : N;
		
		// 소수인지 체크하고 소수면 리스트에 넣기
		for(int i = startNum; i >= 3; i -= 2) {
			if(isPrimeNumber(i)) 
				primeList.add(i);
		}
		primeList.add(2); // 2도 소수
		
		size = primeList.size();
	}
	
	// 소수인지 검사하는 메서드
	private static boolean isPrimeNumber(int number) {
		int endNum = (int)Math.sqrt(number);
		
		for(int i = 3; i <= endNum; i += 2) {
			if(number % i == 0) return false;
		}
		
		return true;
	}
	
	private static void dfs(int[] selPrimeNums, int selIdx, int listIdx, int num) {
		if(isFind) return; // 값을 찾았으면 
		
		if(listIdx >= size) return; // 모든 소수 리스트를 순회했으면
		
		if(selIdx == 4) {
			// 값이 0으로 떨어져야 함
			if(num != 0) return;
			
			for(int i = 3; i >= 0; i--) {
				System.out.print(selPrimeNums[i] + " ");
			}
			isFind = true;
			
			return;
		}
		
		for(int i = listIdx; i < size; i++) {
			int primeNum = primeList.get(i);
			
			if( !(num - primeNum >= 0) ) continue;
			
			selPrimeNums[selIdx] = primeNum;
			
			dfs(selPrimeNums, selIdx + 1, i, num - primeNum);
		}
	}
}
