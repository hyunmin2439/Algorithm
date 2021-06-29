package com.example;

import java.io.*;

public class Baekjoon2750 {
  static int n;
  static int[] arr;
  
  public static void insertionSort() {
      int tmp, idx;
      
      for(int i = 1; i < n; i++){
          idx = i;
          for(int j = i - 1; j > -1; j--){
              if(arr[idx] < arr[j]) {
                tmp = arr[j];
                arr[j] = arr[idx];
                arr[idx] = tmp;
                idx = j;
              }
              else break;
          }
      }
  }
  
  public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
  
  n = Integer.parseInt( br.readLine().trim() );
  arr = new int[n];
  
  for(int i = 0; i < n; i++){
      arr[i] = Integer.parseInt( br.readLine().trim() );
  }
  
  insertionSort();
  
  for(int i : arr){
    bw.write(i + "\n");
  }
  
  br.close();
  bw.close();
  
  } // main end
} // class end