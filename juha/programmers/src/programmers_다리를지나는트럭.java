import java.util.*;

public class programmers_다리를지나는트럭 {
    static Queue<Integer> queue;
    static int totalWeight;

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        queue = new LinkedList<>();
        totalWeight = 0;

        return passBridge(bridge_length, weight, truck_weights);
    }

    static int passBridge(int bridge_length, int weight, int[] truck_weights){
        int time = 1;
        int index = 0;
        int time_index = 0;
        while(time_index < truck_weights.length){
            if(index < truck_weights.length && totalWeight + truck_weights[index] <= weight
                    && queue.size() <= bridge_length){
                queue.offer(truck_weights[index]);
                totalWeight += truck_weights[index];
                index++;
            }else{
                queue.offer(0);
            }

            time++;

            if(queue.size() == bridge_length){
                int minus = queue.poll();
                totalWeight -= minus;
                if(minus > 0) time_index++;
            }
        }

        return time;
    }
}
