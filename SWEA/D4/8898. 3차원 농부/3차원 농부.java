import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * LinkedList를 사용하지 않고 배열을 사용하여 Queue 직접 구현
 * 8,969ms -> 2,461ms 73% 개선
 */

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int ANIMAL_MAX_SIZE = 500_000;
		int[] cows;
		int[] horses;
		int t = 0, T, N, M, xDist, zDist, minDist, minCnt;
		int cow, cowFront, cowRear, horse, horseFront, horseRear;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			minCnt = 1;
			cowFront = cowRear = horseFront = horseRear = -1;
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cows = new int [N];
			horses = new int [M];
			
			st = new StringTokenizer(in.readLine());
			xDist = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				cowRear = (cowRear + 1) % ANIMAL_MAX_SIZE;
				cows[cowRear] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < M; i++) {
				horseRear = (horseRear + 1) % ANIMAL_MAX_SIZE;
				horses[horseRear] = Integer.parseInt(st.nextToken());
			}
			
			// 병합정렬로 변경
			Arrays.sort(cows);
			Arrays.sort(horses);
			
			cowFront = (cowFront + 1) % ANIMAL_MAX_SIZE;
			cow = cows[cowFront];
			horseFront = (horseFront + 1) % ANIMAL_MAX_SIZE;
			horse = horses[horseFront];
			minDist = Math.abs(cow - horse);
			minCnt = 1;
			
			while(cowFront != cowRear && horseFront != horseRear) {
				if( Math.abs(cows[(cowFront + 1) % ANIMAL_MAX_SIZE] - horse) 
						<= Math.abs(cow - horses[(horseFront + 1) % ANIMAL_MAX_SIZE]) ) {
					cowFront = (cowFront + 1) % ANIMAL_MAX_SIZE;
					cow = cows[cowFront];
				}
				else {
					horseFront = (horseFront + 1) % ANIMAL_MAX_SIZE;
					horse = horses[horseFront];
				}

				zDist = Math.abs(cow - horse);
				
				if(zDist == minDist) minCnt++;
				else if(zDist < minDist) {
					minDist = zDist;
					minCnt = 1;
				}
			}
			
			while(cowFront != cowRear) {
				cowFront = (cowFront + 1) % ANIMAL_MAX_SIZE;
				cow = cows[cowFront];
				zDist = Math.abs(cow - horse);
				
				if(zDist == minDist) minCnt++;
				else if(zDist < minDist) {
					minDist = zDist;
					minCnt = 1;
				}
			}
			
			while(horseFront != horseRear) {
				horseFront = (horseFront + 1) % ANIMAL_MAX_SIZE;
				horse = horses[horseFront];
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