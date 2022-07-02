import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
  public static void main(String[] args) throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int[] change = { 500, 100, 50, 10, 5, 1 };
      int price = 0, remain = 0, idx = 0, changeCnt = 0, tmpCnt = 0;
      
      price = Integer.parseInt(in.readLine());
      remain = 1000 - price;
      
      while(remain > 0) {
          if(remain < change[idx]) {
              idx++;
              continue;
          }
          
          tmpCnt = remain / change[idx];
          remain -= change[idx] * tmpCnt;
          changeCnt += tmpCnt; 
      }
      
      System.out.print(changeCnt);
  }
}