package dahye.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
    public static void main(String[] args) {
        System.out.println(solution(100, 100, new int[] {10}));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        int idx = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(truck_weights[0]);

        idx++;
        int curWeight = truck_weights[0];

        while(!queue.isEmpty()) {

            if(queue.size() == bridge_length) {
                int tmp = queue.poll();
                if(tmp == -1) break;
                curWeight -= tmp;

                if(idx < truck_weights.length && (curWeight + truck_weights[idx]) > weight) {
                    queue.add(0);
                    answer++;
                }
                if(idx < truck_weights.length && (curWeight + truck_weights[idx] <= weight)){
                    queue.add(truck_weights[idx]);
                    curWeight += truck_weights[idx];
                    answer++;
                    idx++;
                }
                if(idx >= truck_weights.length && queue.size() < bridge_length) {
                    queue.add(-1);
                    answer++;
                }

            } else {
                if(idx < truck_weights.length && (curWeight + truck_weights[idx]) > weight) {
                    queue.add(0);
                    answer++;
                }
                if(idx < truck_weights.length && (curWeight + truck_weights[idx]) <= weight){
                    queue.add(truck_weights[idx]);
                    curWeight += truck_weights[idx];
                    answer++;
                    idx++;
                }
                if(idx >= truck_weights.length && queue.size() < bridge_length) {
                    queue.add(-1);
                    answer++;
                }
            }
        }
        return answer;
    }
}
