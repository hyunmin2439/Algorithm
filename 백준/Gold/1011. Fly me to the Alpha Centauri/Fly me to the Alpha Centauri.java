import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine().trim());

		while (cnt < t) {
			String[] line = br.readLine().trim().split(" ");
			long x = Integer.parseInt(line[0]);
			long y = Integer.parseInt(line[1]);

			long dis = y - x;
			long count = (int) Math.sqrt(dis) - 1;
			long sumVal = 0;
			long result = 0;

			if (dis < 3)
				result = dis;
			else {
				while (sumVal < dis) {
					count++;
					sumVal = count * count + count;
				} // while end
				
				if (sumVal - count < dis)
					result = count * 2;
				else
					result = count * 2 - 1;
				
			} // else end

			bw.write(String.valueOf(result) + "\n");

			cnt++;
		} // while end

		br.close();
		bw.close();

	} // main end
} // class end