import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		LinkedList<Integer> cows = new LinkedList<>();
		LinkedList<Integer> horses = new LinkedList<>();
		int t = 0, T, N, M, xDist, cow, horse, zDist, minDist, minCnt;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine());
			xDist = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				cows.add( Integer.parseInt(st.nextToken()) );
			}
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < M; i++) {
				horses.add( Integer.parseInt(st.nextToken()) );
			}
			
			Collections.sort(cows);
			Collections.sort(horses);

			cow = cows.poll();
			horse = horses.poll();
			minDist = Math.abs(cow - horse);
			minCnt = 1;
			
			while(!cows.isEmpty() && !horses.isEmpty()) {
				if(Math.abs(cows.get(0) - horse) <= Math.abs(cow - horses.get(0))) {
					cow = cows.poll();
				}
				else {
					horse = horses.poll();
				}

				zDist = Math.abs(cow - horse);
				
				if(zDist == minDist) minCnt++;
				else if(zDist < minDist) {
					minDist = zDist;
					minCnt = 1;
				}
			}
			
			while(!cows.isEmpty()) {
				cow = cows.poll();
				zDist = Math.abs(cow - horse);
				
				if(zDist == minDist) minCnt++;
				else if(zDist < minDist) {
					minDist = zDist;
					minCnt = 1;
				}
			}
			
			while(!horses.isEmpty()) {
				horse = horses.poll();
				zDist = Math.abs(cow - horse);
				
				if(zDist == minDist) minCnt++;
				else if(zDist < minDist) {
					minDist = zDist;
					minCnt = 1;
				}
			}
		
			sb.append("#").append(t).append(" ")
			.append(xDist + minDist).append(" ").append(minCnt)
			.append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
}