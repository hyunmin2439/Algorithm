import java.util.Arrays;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0, currWeight = 0, idx = 0, front = 0, rear = -1;
        int[] queue = new int[bridge_length];
        
        while(time < bridge_length) {
            time++;
            
            if(idx >= truck_weights.length) continue;
            
            rear = (rear + 1) % bridge_length;
            if(currWeight + truck_weights[idx] <= weight) {
                queue[rear] = truck_weights[idx];
                currWeight += truck_weights[idx++];
            }
            else
                queue[rear] = 0;
        }
        
        while(front != rear) {
            time++;
            
            currWeight -= queue[front];
            front = (front + 1) % bridge_length;
            
            if(idx >= truck_weights.length) continue;
            
            rear = (rear + 1) % bridge_length;
            if(currWeight + truck_weights[idx] <= weight) {
                queue[rear] = truck_weights[idx];
                currWeight += truck_weights[idx++];
            }
            else
                queue[rear] = 0;
        }
        
        return time + 1;
    }
}