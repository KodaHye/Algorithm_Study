package 프래그러머스;
import java.util.*;

class Solution {
    class Point{
        int time,weight;

        public Point(int time,int weight){
            this.time = time;
            this.weight= weight;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Point> q = new LinkedList<>();
        int curWeight=0;
        int time=0;
        int idx=0;
        while(true){
            //트럭이 다 지나가면 종료
            if(idx==truck_weights.length){
                break;
            }
            time++;
            //트럭 다리에서 나가기
            if(!q.isEmpty()&&time-q.peek().time==bridge_length){
                Point p = q.poll();
                curWeight-=p.weight;
            }
            //다리에 진입한 트럭들 무게보다 다리제한 무게보다 크면 진입 X
            if(curWeight+ truck_weights[idx]>weight) continue;
            //다리에 진입한 트럭이 다리길이보다 크거나 같으면 진입 x
            if(q.size()>=bridge_length) continue;

            //트럭 진입
            curWeight+= truck_weights[idx];
            q.add(new Point(time,truck_weights[idx]));

            idx++;

        }

        answer= bridge_length+time;
        return answer;
    }

