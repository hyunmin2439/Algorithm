package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Memory:14,300KB / Time:128ms
 */
public class BJ_1015_수열정렬 {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		Node[] arr = input();
		
		// 숫자로 정렬
		Arrays.sort( arr, (n1, n2) -> n1.num - n2.num );
		
		// 정렬된 인덱스 기록
		for(int i = 0; i < N; i++) {
			arr[i].sortedIdx = i;
		}
		
		// 원래 인덱스 순서로 정렬
		Arrays.sort( arr, (n1, n2) -> n1.idx - n2.idx );
		
		// 최종 결과값
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(arr[i].sortedIdx).append(' ');
		}
		
		System.out.println(sb);
	}
	
	private static Node[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		Node[] arr = new Node[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = new Node(Integer.parseInt(st.nextToken()), i);
		}
		
		in.close();
		
		return arr;
	}
	
	static class Node {
		int num, idx, sortedIdx;
		
		public Node(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}

}
