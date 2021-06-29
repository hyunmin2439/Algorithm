package com.example;

import java.io.*;
import java.util.Arrays;

public class Baekjoon2751 {
  static int n;
  static int[] arr;
  
  public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
  
  n = Integer.parseInt( br.readLine().trim() );
  arr = new int[n];
  
  for(int i = 0; i < n; i++){
      arr[i] = Integer.parseInt( br.readLine().trim() );
  }
  
  Arrays.sort(arr);
  
  for(int i : arr){
    bw.write(i + "\n");
  }
  
  br.close();
  bw.close();
  
  } // main end
} // class end