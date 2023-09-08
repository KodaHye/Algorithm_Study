package hyeonsu.programmers;

import java.util.*;

public class 다리를_지나는_트럭 {

    public class Truck{

        int weight;
        int entry;

        public Truck(int weight, int entry) {
            this.weight = weight;
            this.entry = entry;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int answer = 0;
        int weightTotal = 0;

        Queue<Truck> waitingTrucks = new LinkedList<Truck>();
        Queue<Truck> bridge = new LinkedList<Truck>();

        //트럭이 1대일 때
        if(truck_weights.length == 1) {
            return answer += (bridge_length + 1);
        }

        for(int i = 0; i < truck_weights.length; i++) {
            waitingTrucks.offer(new Truck(truck_weights[i], 0));
        }

        while(!waitingTrucks.isEmpty() || !bridge.isEmpty()) {
            answer++;
            //다리에 트럭이 올라가 있으면
            if(!bridge.isEmpty()){
                Truck t = bridge.peek();
                //다리 끝에 도착했으면 다리 큐에서 빼준다.
                if(answer - t.entry >= bridge_length) {
                    weightTotal -= t.weight;
                    bridge.poll();
                }
            }

            //남아있는 트럭을 다리로 옮기는 과정
            if(!waitingTrucks.isEmpty()) {
                //현재 다리 위에 있는 트럭들의 무게 합 + 곧 올라갈 트럭 무게가 한계중량 안에 들어오면 대기 중인 트럭을 다리로 옮긴다.
                if(weightTotal + waitingTrucks.peek().weight <= weight) {
                    Truck t = waitingTrucks.poll();
                    weightTotal += t.weight;
                    bridge.offer(new Truck(t.weight, answer));
                }
            }
        }

        return answer;
    }
}
