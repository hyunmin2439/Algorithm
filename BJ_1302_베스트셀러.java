package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Memory:14184KB / Time:132ms
public class BJ_1302_베스트셀러 {
	
	public static void main(String[] args) throws Exception {
		List<Book> list = input();
		
		list.sort(null);
		
		System.out.print(list.get(0).title);
	}
	
	private static List<Book> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		List<Book> list = new ArrayList<>();
		
		boolean isExist;
		for(int i = 0; i < N; i++) {
			String bookTitle = in.readLine();
			
			isExist = false;
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).title.equals(bookTitle)) {
					list.get(j).count++;
					isExist = true;
					break;
				}
			}
			
			if( !isExist ) 
				list.add(new Book(bookTitle));
		}
		
		in.close();
		
		return list;
	}
	
	static class Book implements Comparable<Book>{
		String title;
		int count;
		
		public Book(String title) {
			this.title = title;
		}
		
		@Override
		public int compareTo(Book other) {
			
			if(this.count != other.count)
				return other.count - this.count;
			
			return this.title.compareTo(other.title);
		}
	}
}
