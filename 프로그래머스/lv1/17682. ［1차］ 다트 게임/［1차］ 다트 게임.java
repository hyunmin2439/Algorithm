class Solution {
    public int solution(String dartResult) {
        int idx = 0, score[] = new int[4];
        char c;
        
        for(int i = 0; i < dartResult.length(); i++) {
            c = dartResult.charAt(i);
            
            switch(c) {
                case 'S': break;
                case 'D': 
                    score[idx] = (int) Math.pow(score[idx], 2);
                    break;
                case 'T':
                    score[idx] = (int) Math.pow(score[idx], 3);
                    break;
                case '*':
                    score[idx - 1] *= 2;
                    score[idx] *= 2;
                    break;
                case '#':
                    score[idx] *= -1;
                    break;
                default:
                    idx++;
                    if(dartResult.charAt(i + 1) == '0'){
                        i++;
                        score[idx] = 10;
                    }
                    else
                        score[idx] =  c - '0';
            }
        }
        
        return score[1] + score[2] + score[3];
    }
}