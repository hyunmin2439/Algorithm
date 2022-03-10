package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2621_카드게임 {

	public static void main(String[] args) throws Exception {
		Card[] cardList = input();
		
		int score = solve(cardList);
		
		System.out.print(score);
	}
	
	private static int solve(Card[] cardList) {
		int[] countColor = new int[4], countNumber = new int[10], sameNumber = new int[5];
		boolean sameColor = false, straight = false, fourNumber = false, threeNumber = false, twoNumber = false, doubleTwoNumber = false;
		int maxNumber = 0;
		
		for(int i = 0; i < 5; i++) {
			countColor[ cardList[i].color ]++;
			countNumber[ cardList[i].number ]++;
		}
		
		for(int i = 0; i < 4; i++) {
			if(countColor[i] == 5) {
				sameColor = true;
				break;
			}
		}
		
		for(int i = 4; i <= 9; i++) {
			if( countNumber[i - 4] == 1 && countNumber[i - 3] == 1 && 
					countNumber[i - 2] == 1 && countNumber[i - 1] == 1 && countNumber[i] == 1 ) {
				straight = true;
				break;
			}
		}
		
		for(int i = 1; i <= 9; i++) {
			if(countNumber[i] > 0)
				maxNumber = i > maxNumber ? i : maxNumber;
			
			if(countNumber[i] == 4) {
				fourNumber = true;
				sameNumber[4] = i;
			}
			else if(countNumber[i] == 3) {
				threeNumber = true;
				sameNumber[3] = i;
			}
			else if(countNumber[i] == 2) {
				if( twoNumber ) {
					doubleTwoNumber = true;
					sameNumber[1] = i;
				}
				else {
					twoNumber = true;
					sameNumber[2] = i;
				}
			}
		}
		

		// 1 규칙
		if(sameColor && straight)
			return 900 + maxNumber;
		// 2 규칙
		else if(fourNumber)
			return 800 + sameNumber[4];
		// 3 규칙
		else if(threeNumber && twoNumber)
			return 700 + sameNumber[3] * 10 + sameNumber[2];
		// 4 규칙
		else if(sameColor)
			return 600 + maxNumber;
		// 5 규칙
		else if(straight)
			return 500 + maxNumber;
		// 6 규칙
		else if(threeNumber)
			return 400 + sameNumber[3];
		// 7 규칙
		else if(doubleTwoNumber)
			return 300 + ( sameNumber[2] > sameNumber[1] ? sameNumber[2] * 10 + sameNumber[1] : sameNumber[1] * 10 + sameNumber[2] );
		// 8 규칙
		else if(twoNumber)
			return 200 + sameNumber[2];
		else 
			return 100 + maxNumber;
	}
	
	private static Card[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Card[] cardList = new Card[5];
		
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(in.readLine());
			String color = st.nextToken();
			int number = Integer.parseInt(st.nextToken());			
			cardList[i] = new Card(color, number);
		}
		
		in.close();
		
		return cardList;
	}
	
	static class Card {
		int color, number;
		
		public Card(String color, int number) {
			
			switch(color) {
			case "R": this.color = 0; break;
			case "B": this.color = 1; break;
			case "Y": this.color = 2; break;
			default : this.color = 3; break;
			}
			
			this.number = number;
		}
	}

}
