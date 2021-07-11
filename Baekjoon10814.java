package com.algo;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * stable sort
 * Sort algorithm that does not reposition 
 * elements with the same value
 * 
 * Input
 * n (1 <= n <= 100,000)
 * age name (1 <= age(int) <= 200, 
 * 			 name(String, upper case || lower case) <= 200)
 * ... n-line
 * 
 * Output
 * In order of age
 * In the order entered if the ages are the same
 */

class Member {
	private int age;
	private String name;
	
	public Member(String age, String name) {
		super();
		this.age = Integer.parseInt(age);
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
}

public class Baekjoon10814 {

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<Member> list = new ArrayList<>();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt( br.readLine().trim() );
		
		while(n > 0) {
			st = new StringTokenizer(br.readLine().trim());
			list.add( new Member(st.nextToken(), st.nextToken()) );			
			n--;
		}
		
		list.sort(new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				if(o1.getAge() < o2.getAge() ) return -1;
				if(o1.getAge() > o2.getAge() ) return 1;
				return 0; // same age, not reposition
			}
		});
		
		for(Member mem : list) {
			sb.append(mem.getAge() + " " + mem.getName() + "\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
