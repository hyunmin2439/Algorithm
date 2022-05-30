import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static boolean isFind;
	private static int N, size;
	private static List<Integer> primeList;
	
	public static void main(String[] args) throws Exception {
		input();
		initPrimeNumber();
		dfs(new int[4], 0, 0, N);
		if( !isFind ) System.out.print(-1);
	}
	
	private static void dfs(int[] selPrimeNums, int selIdx, int listIdx, int num) {
		if(isFind) return;
		
		if(listIdx >= size) return;
		
		if(selIdx == 4) {
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

	private static int input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());

		in.close();
		
		return N;
	}
	
	private static void initPrimeNumber() {
		primeList = new ArrayList<>();
		
		int startNum = N % 2 == 0 ? N - 1 : N;
		
		for(int i = startNum; i >= 3; i -= 2) {
			if(isPrimeNumber(i)) 
				primeList.add(i);
		}
		primeList.add(2);
		
		size = primeList.size();
	}
	
	private static boolean isPrimeNumber(int number) {
		int endNum = (int)Math.sqrt(number);
		
		for(int i = 3; i <= endNum; i += 2) {
			if(number % i == 0) return false;
		}
		
		return true;
	}
}
