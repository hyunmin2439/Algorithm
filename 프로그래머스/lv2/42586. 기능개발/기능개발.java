import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer, queue = new int[101];
        int now, rear = 0;
        
        for(int i = 0; i < progresses.length; i++) {
            progresses[i] = (int)Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        
        queue[rear] = 1;
        now = progresses[0];
        for(int i = 1; i < progresses.length; i++) {
            if(now >= progresses[i]) {
                queue[rear]++;
            }
            else {
                queue[++rear]++;
                now = progresses[i];
            }
        }

        answer = new int[rear + 1];
        
        for(int i = 0; i <= rear; i++) {
            answer[i] = queue[i];
        }
        
        return answer;
    }
}