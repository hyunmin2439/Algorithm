package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Memory:14244KB / Time:132ms
public class BJ_16509_장군 {
	
	private static Node king, firstPos;
	
	// 8 방향
	private static int[][] dy = { 
			{-1, -2, -3}, {-1, -2, -3}, 
			{0, -1, -2}, {0, 1, 2}, 
			{1, 2, 3}, {1, 2, 3}, 
			{0, 1, 2}, {0, -1, -2} 
	};
	private static int[][] dx = { 
			{0, -1, -2}, {0, 1, 2}, 
			{1, 2, 3}, {1, 2, 3}, 
			{0, 1, 2}, {0, -1, -2}, 
			{-1, -2, -3}, {-1, -2, -3} 
	};
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = bfs();
		
		System.out.print(res);
	}
	
	private static int bfs() {
		int res = -1;
		boolean[][] visited = new boolean[10][9];
		Queue<Node> queue = new LinkedList<>();
		
		visited[firstPos.y][firstPos.x] = true;
		queue.offer(firstPos);
		
		while( ! queue.isEmpty() ) {
			Node currPos = queue.poll();
			
			for(int d = 0; d < 8; d++) {
				int ny = currPos.y + dy[d][2];
				int nx = currPos.x + dx[d][2];
				
				if( ! (0 <= ny && ny < 10 && 0 <= nx && nx < 9) || visited[ny][nx] ||
					
					(currPos.y + dy[d][0] == king.y && currPos.x + dx[d][0] == king.x) ||
					
					(currPos.y + dy[d][1] == king.y && currPos.x + dx[d][1] == king.x) 
				) 
					continue;
				
				if(ny == king.y && nx == king.x) return currPos.d + 1;
				
				visited[ny][nx] = true;
				queue.offer(new Node(ny, nx, currPos.d + 1));
			}
		}
		
		return res;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		firstPos = new Node(y, x, 0);
		
		st = new StringTokenizer(in.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		king = new Node(y, x, 0);
		
		in.close();
	}
	
	static class Node {
		int y, x, d;
		
		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

}
