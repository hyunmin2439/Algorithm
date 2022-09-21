class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 1단계
        new_id = new_id.toLowerCase();
        
        // 2단계
        // (){}[]\ 는 \\를 추가해줘야 한다.
        new_id = new_id.replaceAll("[~!@#$%^&*()=+\\[{\\]}:?,<>/]", "");
        
        // 3단계
        // *+$|는 []로 감싸줘야 한다.
        new_id = new_id.replaceAll("[.]{2,}", "."); // .문자가 2번 이상 반복
        
        // 4단계
        if( !new_id.isEmpty() && new_id.charAt(0) == '.' )
            new_id = new_id.substring(1, new_id.length());
        
        if( !new_id.isEmpty() && new_id.charAt(new_id.length() - 1) == '.' )
            new_id = new_id.substring(0, new_id.length() - 1);
        
        // 5단계
        if( new_id.isEmpty() )
            new_id = "a";
        
        // 6단계
        if( new_id.length() > 15 ) {
            new_id = new_id.substring(0, 15);
            
            if(new_id.charAt( new_id.length() - 1) == '.' )
                new_id = new_id.substring(0, new_id.length() - 1);
        }
        
        // 7단계
        while( new_id.length() <= 2 ) {
            new_id += new_id.charAt( new_id.length() - 1 );
        }
        
        return new_id;
    }
}