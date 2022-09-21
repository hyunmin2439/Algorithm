class Solution { 
    private static final int[][] pos = {
        {3, 1}, // 0
        {0, 0}, {0, 1}, {0, 2}, // 1 ~ 3
        {1, 0}, {1, 1}, {1, 2}, // 4 ~ 6
        {2, 0}, {2, 1}, {2, 2}, // 7 ~ 9
        {3, 0}, {3, 2}, // *, #
    };
    
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int leftPos[] = pos[10], rightPos[] = pos[11],
            leftDist = 0, rightDist = 0;
        
        for(int i = 0; i < numbers.length; i++) {
            
            switch(numbers[i]) {
                case 1, 4, 7:
                    leftPos = pos[ numbers[i] ];
                    sb.append('L');
                    break;  
                case 3, 6, 9:
                    rightPos = pos[ numbers[i] ];
                    sb.append('R');
                    break;
                default:
                    leftDist = calDist(leftPos, numbers[i]);
                    rightDist = calDist(rightPos, numbers[i]);
                    
                    if(leftDist < rightDist || 
                       (leftDist == rightDist && "left".equals(hand)) ) {
                        leftPos = pos[ numbers[i] ];
                        sb.append('L');
                    }
                    else {
                        rightPos = pos[ numbers[i] ];
                        sb.append('R');
                    }
            }
        }
        
        return sb.toString();
    }
    
    private int calDist(int[] handPos, int target) {
        return Math.abs(handPos[0] - pos[target][0]) 
            + Math.abs(handPos[1] - pos[target][1]);
    }
}