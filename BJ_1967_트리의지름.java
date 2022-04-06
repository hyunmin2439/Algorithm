package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Memory:21,636KB / Time:684ms
public class BJ_1967_트리의지름 {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		List<List<Node>> list = input();
		
		solve(list);
	}
	
	private static void solve(List<List<Node>> list) {
		int totMaxLen = 0, maxLen[] = new int[N + 1];
		
		Node first, second;
		List<Node> eachList = null;
		
		for(int i = N; i >= 1; i--) {
			eachList = list.get(i);
			
			if( eachList.isEmpty() ) continue;
			
			for(int j = 0; j < eachList.size(); j++) {
				first = eachList.get(j);
				// 각 노드 최대값 더해주기
				first.dist += maxLen[ first.num ];
			}
			
			// 길이 순 정렬
			Collections.sort(eachList);
			
			first = eachList.get(0);
			maxLen[i] = first.dist;
			totMaxLen = Math.max(totMaxLen, first.dist);
			
			if(eachList.size() < 2) continue;
			
			second = eachList.get(1);
			totMaxLen = Math.max(totMaxLen, first.dist + second.dist);
		}
		
		System.out.print(totMaxLen);
	}
	
	private static List<List<Node>> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		
		List<List<Node>> list = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) list.add(new LinkedList<>());
		
		
		int parent, child, dist;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			parent = Integer.parseInt(st.nextToken());
			child = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			
			list.get(parent).add(new Node(child, dist));
		}
		
		in.close();
		
		return list;
	}
	
	static class Node implements Comparable<Node> {
		int num, dist;
		
		public Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
		
		public int compareTo(Node other) {
			return other.dist - this.dist;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", dist=" + dist + "]";
		}
		
	}
}
