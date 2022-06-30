package com.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2947_나무조각 {
  public static void main(String[] args) throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(in.readLine());
      
      int[] arr = new int[5];
      
      for(int i = 0; i < arr.length; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      
      int tmp;
      for(int i = 0; i < 4; i++) {
        for(int j = 0; j < 4 - i; j++) {
          if(arr[j] > arr[j + 1]) {
            tmp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = tmp;
            printArr(arr);
          }
        }
      }
  }
  
  private static void printArr(int[] arr) {
      for(int i = 0; i < arr.length; i++)
          System.out.print(arr[i] + " ");
      System.out.println();
  }
}