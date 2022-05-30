import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ));
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out ));
		
		int t = Integer.parseInt( br.readLine().trim() );
		
		while (t > 0) {
			String[] line = br.readLine().trim().split(" ");

			int x1 = Integer.parseInt(line[0]);
			int y1 = Integer.parseInt(line[1]);
			int r1 = Integer.parseInt(line[2]);

			int x2 = Integer.parseInt(line[3]);
			int y2 = Integer.parseInt(line[4]);
			int r2 = Integer.parseInt(line[5]);

			int plusR = (int) Math.pow(r1 + r2, 2);
			int minusR = (int) Math.pow(r1 - r2, 2);

			int distance = (int) Math.pow(x1 - x2, 2) + (int) Math.pow(y1 - y2, 2);

			int res = 2;

			if (x1 == x2 && y1 == y2 && r1 == r2)
				res = -1;
			else if (distance < minusR)
				res = 0;
			else if (distance > plusR)
				res = 0;
			else if (distance == plusR)
				res = 1;
			else if (distance == minusR)
				res = 1;

			sb.append(res + "\n");
			
			t--;
		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.close();
	}

}