import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<LinkedList<Integer>> disks = new LinkedList<>();
		List<int[]> deleteList = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			T = Integer.parseInt(st.nextToken()),
			x, d, k, answer = 0;
		
		for(int i = 0; i <= N; i++) {
			disks.add(new LinkedList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				disks.get(i).add(Integer.valueOf(st.nextToken()));
			}
		}
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			for(int i = x; i <= N; i += x)
				rotate(disks.get(i), d, k);
			
			delete(disks, deleteList, N, M);
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				answer += disks.get(i).get(j);
			}
		}
		
		System.out.println(answer);
	}
	
	private static void rotate(LinkedList<Integer> disk, int d, int k) {
		if(d == 0) // 시계 방향
			for(int i = 0; i < k; i++) 
				disk.offerFirst(disk.pollLast());
		else // 반시계 방향
			for(int i = 0; i < k; i++) 
				disk.offer(disk.poll());
	}
	
	private static void delete(List<LinkedList<Integer>> disks, List<int[]> deleteList, int N, int M) {
		int curr, sum = 0, cnt = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				curr = disks.get(i).get(j);
				
				if(curr == 0) continue;
				
				sum += curr;
				cnt++;
				
				if( (i > 1 && disks.get(i - 1).get(j) == curr) ||
					(i < N && disks.get(i + 1).get(j) == curr) ||
					(j > 0 && disks.get(i).get(j - 1) == curr) ||
					(j < M - 1 && disks.get(i).get(j + 1) == curr) ||
					(j == 0 && disks.get(i).get(M - 1) == curr) ||
					(j == M - 1 && disks.get(i).get(0) == curr) ) {
					deleteList.add(new int[] {i , j});
				}
			}
		}
		
		if( deleteList.isEmpty() ) {
			avg(disks, (double)sum / cnt, N, M);
			return;
		}
		
		deleteList.forEach( d -> disks.get(d[0]).set(d[1], 0) );
		deleteList.clear();
	}
	
	private static void avg(List<LinkedList<Integer>> disks, double avg, int N, int M) {
		int curr;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				curr = disks.get(i).get(j);
				
				if(curr == 0) continue;
				
				if(curr > avg)
					curr--;
				else if(curr < avg)
					curr++;
				
				disks.get(i).set(j, curr);
			}
		}
	}

}