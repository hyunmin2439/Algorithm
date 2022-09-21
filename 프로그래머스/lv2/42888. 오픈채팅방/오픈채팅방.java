import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Queue<User> queue = new LinkedList<>();
        Map<String, String> map = new HashMap<>();
        String inst, id, nick, msg;
        User user;
        
        for(int i = 0; i < record.length; i++) {
            StringTokenizer st = new StringTokenizer(record[i]);
            inst = st.nextToken();
            id = st.nextToken();
            
            switch(inst) {
                case "Change":
                    map.put(id, st.nextToken());
                    break;
                case "Enter":
                    map.put(id, st.nextToken());
                    queue.offer(new User(id, "in"));
                    break;
                case "Leave":
                    queue.offer(new User(id, "out"));
                    break;
            }
        }
        
        while( !queue.isEmpty() ) {
            user = queue.poll();
            
            if( "in".equals(user.inOut) )
                msg = "님이 들어왔습니다.";
            else
                msg = "님이 나갔습니다.";
            
            answer.add( map.get(user.id) + msg );
        }
        
        return answer.toArray(new String[0]);
    }
}

class User {
    String id, inOut;
    
    public User(String id, String inOut) {
        this.id = id;
        this.inOut = inOut;
    }
}