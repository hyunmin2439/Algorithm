package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Comparable을 적용하기 좋은 문제
 *
 * Memory:56940KB / Time:716ms
 */

public class BJ_10825_국영수_Comparable {

    static int N;

    public static void main(String[] args) throws Exception {
        PriorityQueue<Student> students = input();

        output(students);
    }

    private static void output(PriorityQueue<Student> students) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            Student student = students.poll();

            sb.append(student.name).append('\n');
        }

        System.out.print(sb);
    }

    private static PriorityQueue<Student> input() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Student> pqueue = new PriorityQueue<>();

        N = Integer.parseInt(in.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            pqueue.offer(new Student(name, kor, eng, math));
        }

        return pqueue;
    }

    static class Student implements Comparable<Student> {
        String name;
        int kor, eng, math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student other) {
            if (this.kor != other.kor)
                return other.kor - this.kor; // 내림차순
            if (this.eng != other.eng)
                return this.eng - other.eng; // 오름차순
            if (this.math != other.math)
                return other.math - this.math; // 내림차순

            return this.name.compareTo(other.name); // 오름차순
        }
    }
}
