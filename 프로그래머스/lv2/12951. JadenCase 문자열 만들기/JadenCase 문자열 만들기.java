import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        StringBuilder sb = new StringBuilder();
        String[] split = s.split(" ");
        
        for(int i = 0; i < split.length; i++) {  
            if( split[i].isEmpty() )
                sb.append(split[i]).append(' ');
            else if( 'a' <= split[i].charAt(0) )
                sb.append( (char)(split[i].charAt(0) - 32) ).append(split[i].substring(1, split[i].length()).toLowerCase()).append(' ');
            else
                sb.append( (char)(split[i].charAt(0)) ).append(split[i].substring(1, split[i].length()).toLowerCase()).append(' ');
        }
        
        if(s.charAt(s.length() - 1) != ' ')
            sb.setLength(sb.length() - 1);
        
        return sb.toString();
    }
}