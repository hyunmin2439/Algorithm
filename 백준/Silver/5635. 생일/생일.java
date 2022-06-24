import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<Student> list = new LinkedList<>();
		StringTokenizer st = null;
		int N, year, month, day;
		String name;
		
		N = Integer.parseInt(in.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			name = st.nextToken();
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			list.add(new Student(year, month, day, name));
		}
		
		Collections.sort(list);
		
		System.out.print(list.get(list.size() - 1).name + '\n' + list.get(0).name);
	}
	
	static class Student implements Comparable<Student> {
		int year, month, day;
		String name;
		
		public Student(int year, int month, int day, String name) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.name = name;
		}
		
		public int compareTo(Student other) {
			if(this.year != other.year)
				return this.year - other.year;
			
			if(this.month != other.month)
				return this.month - other.month;
			
			return this.day - other.day;
		}
	}

}
