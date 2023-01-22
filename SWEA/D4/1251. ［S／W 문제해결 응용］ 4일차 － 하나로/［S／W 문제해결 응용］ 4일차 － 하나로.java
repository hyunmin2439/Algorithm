import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = 0, T, N, x[], y[];
		double E, matrix[][];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			
			x = new int[N];
			y = new int[N];
			matrix = new double[N][N];
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(in.readLine());
			
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					matrix[j][i] = matrix[i][j] = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
				}
			}
			
			sb.append("#").append(t).append(" ")
			.append(Math.round(E * prim(matrix, N)))
			.append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}

	public static double prim(double[][] matrix, int N) {
		boolean[] visited = new boolean[N];
		double[] minDist = new double[N];
		double min, total = 0;
		int tmp = 0, vtx = 0, cnt = 0;
		
		for(int i = 1; i < N; i++) {
			minDist[i] = matrix[0][i];
		}
		
		while(cnt++ < N) {
			vtx = tmp;
			visited[vtx] = true;
			total += minDist[vtx];
			min = Double.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				if(visited[i]) continue;
				
				minDist[i] = Math.min(minDist[i], matrix[vtx][i]);
				
				if(min > minDist[i]) {
					tmp = i;
					min = minDist[i];
				}
			}
		}
		
		return total;
	}
}