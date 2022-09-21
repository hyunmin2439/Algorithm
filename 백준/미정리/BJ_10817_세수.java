import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10817_세수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		int[] arr = new int[3];
		
		arr[0] = Integer.parseInt(st.nextToken());
		arr[1] = Integer.parseInt(st.nextToken());
		arr[2] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		System.out.println(arr[1]);
		
		in.close();
	}

}
