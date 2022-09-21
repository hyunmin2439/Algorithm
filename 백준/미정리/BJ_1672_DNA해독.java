import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문자열을 배열을 만들어놓고 비교해가면서 바꿀 경우 시간이 오래걸림.
 * 
 * 때문에 메모리를 조금 더 쓰더라도 26 * 26 2차원 char 배열 선언 후 사용.
 * 
 * Memory:20,384KB / Time:248ms
 */

public class BJ_1672_DNA해독 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] DNAseq = new char[26][26];
		DNAseq['A' - 'A']['A' - 'A'] = 'A';
		DNAseq['A' - 'A']['G' - 'A'] = 'C';
		DNAseq['A' - 'A']['C' - 'A'] = 'A';
		DNAseq['A' - 'A']['T' - 'A'] = 'G';

		DNAseq['G' - 'A']['A' - 'A'] = 'C';
		DNAseq['G' - 'A']['G' - 'A'] = 'G';
		DNAseq['G' - 'A']['C' - 'A'] = 'T';
		DNAseq['G' - 'A']['T' - 'A'] = 'A';

		DNAseq['C' - 'A']['A' - 'A'] = 'A';
		DNAseq['C' - 'A']['G' - 'A'] = 'T';
		DNAseq['C' - 'A']['C' - 'A'] = 'C';
		DNAseq['C' - 'A']['T' - 'A'] = 'G';
		
		DNAseq['T' - 'A']['A' - 'A'] = 'G';
		DNAseq['T' - 'A']['G' - 'A'] = 'A';
		DNAseq['T' - 'A']['C' - 'A'] = 'G';
		DNAseq['T' - 'A']['T' - 'A'] = 'T';
		
		int N = Integer.parseInt(in.readLine());
		char[] seqExam = in.readLine().toCharArray();
		
		for(int i = N - 1; i > 0; i--) {
			seqExam[i - 1] = DNAseq[ seqExam[i - 1] - 'A' ][ seqExam[i] - 'A' ];
		}
		
		System.out.print( seqExam[0] );
		
		in.close();
	}

}
