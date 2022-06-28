import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
      BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
  
      StringTokenizer st = new StringTokenizer(in.readLine());
      int N = 0, len = 0, arr[];
  
      N = Integer.parseInt(st.nextToken());
      len = Integer.parseInt(st.nextToken());
  
      arr = new int[N];
      st = new StringTokenizer(in.readLine());
      for(int i = 0; i < N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
  
      for(int i = 0; i < N; i++) {
          if(len >= arr[i]) len++;
      }
  
      System.out.println(len);
      in.close();
  }
}