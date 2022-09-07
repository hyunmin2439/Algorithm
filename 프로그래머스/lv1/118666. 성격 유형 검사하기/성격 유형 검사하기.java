class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] alpha = new int[26];
        char[] answer = new char[4];
        
        for(int i = 0; i < choices.length; i++) {
            switch( choices[i] ) {
                case 1, 2, 3:
                    alpha[ survey[i].charAt(0) - 65 ] += 4 - choices[i];
                    break;
                case 5, 6, 7:
                    alpha[ survey[i].charAt(1) - 65 ] += choices[i] % 4;
                    break;
            }
        }
        
        answer[0] = alpha['R' - 65] >= alpha['T' - 65] ? 'R' : 'T';
        answer[1] = alpha['C' - 65] >= alpha['F' - 65] ? 'C' : 'F';
        answer[2] = alpha['J' - 65] >= alpha['M' - 65] ? 'J' : 'M';
        answer[3] = alpha['A' - 65] >= alpha['N' - 65] ? 'A' : 'N';
        
        return String.valueOf(answer);
    }
}