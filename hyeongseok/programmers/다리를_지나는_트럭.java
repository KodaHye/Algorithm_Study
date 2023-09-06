package hyeongseok.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를_지나는_트럭 {
    public static void main(String[] args) {
        solution(2, 10, new int[]{7, 4, 5, 6});
    }

    static public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> Q = new LinkedList<>();

        int nowBridge = 0; // 현재 다리 위에 올라가있는 트럭 무게
        int time = 0; // 경과 시간

        /**
         * 조건 1 : 다리에 최대 트럭이 bridge_length 만큼 올라갈 수 있음
         * 조건 2 : 다리에 무게가 최대 weight 까지 올라갈 수 있음
         */
        for (int i = 0; i < truck_weights.length; i++) {
            int truck = truck_weights[i]; // 현재 트럭의 무게

            while (true) {
                // 다리에 아무런 트럭이 없다면?
                if (Q.isEmpty()) {
                    nowBridge += truck;
                    Q.add(truck);
                    time++;
                    break; // 트럭 하나 추가하고, 바로 다음 트럭 넣을 준비
                }
                // 다리에 트럭이 하나라도 있다면?
                else {
                    if (Q.size() == bridge_length) { // 다리가 꽉 차서 트럭을 못 넣는 경우
                        nowBridge -= Q.poll();
                    } else if (nowBridge + truck <= weight) { // 다리가 꽉 차지 않았고, 무게도 문제 없는 경우
                        nowBridge += truck;
                        Q.add(truck);
                        time++;
                        break;
                    } else { // 현재 가장 앞에 대기중인 트럭이 무게때문에 다리에 못 올라가는 경우
                        Q.add(0);
                        time++;
                    }
                }
            }
        }

        return time + bridge_length;
    }
}
