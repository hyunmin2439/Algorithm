import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        int size = plans.length, top = -1;
        int now, work = 0, term = 0, idx = 0;
        int[] stack = new int[size];
        String[] answer = new String[size];
        Subject[] subjects = new Subject[size];
        
        StringTokenizer st;
        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(plans[i][1], ":");
            subjects[i] = new Subject(plans[i][0], st.nextToken(), st.nextToken(), plans[i][2]);
        }
        
        Arrays.sort(subjects);
        
        work = 0;
        now = subjects[work].startTime;
        for(int i = 1; i < size; i++) {
            term = subjects[i].startTime - now;
            
            if(subjects[work].taskTime > term) {
                subjects[work].taskTime -= term;
                stack[++top] = work;
            }
            else {
                answer[idx++] = subjects[work].name;
                term -= subjects[work].taskTime;
                
                while(top >= 0 && term > 0) {
                    work = stack[top--];
                    
                    if(subjects[work].taskTime > term) {
                        subjects[work].taskTime -= term;
                        stack[++top] = work;
                        term = 0;
                    }
                    else if(subjects[work].taskTime <= term) {
                        answer[idx++] = subjects[work].name;
                        term -= subjects[work].taskTime;
                    }
                }
            }
            
            work = i;
            now = subjects[work].startTime;
        }
        
        answer[idx++] = subjects[work].name;
        
        while(idx < size) {
            answer[idx++] = subjects[ stack[top--] ].name;
        }
        
        return answer;
    }
    
    class Subject implements Comparable<Subject> {
        String name;
        int startTime, taskTime;
        
        public Subject(String name, String hour, String min, String taskTime) {
            this.name = name;
            this.startTime = Integer.parseInt(hour) * 60 + Integer.parseInt(min);
            this.taskTime = Integer.parseInt(taskTime);
        }
        
        @Override
        public int compareTo(Subject other) {
            return this.startTime - other.startTime;
        }
    }
}