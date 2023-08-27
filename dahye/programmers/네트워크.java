package dahye.programmers;

import java.util.*;

class 네트워크 {

    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean v[] = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        for(int i = 0; i < n; i++) {
            if(!v[i]) answer++;

            queue.add(i);
            v[i] = true;

            while(!queue.isEmpty()) {
                int current = queue.poll();

                for(int j = 0; j < n; j++) {
                    int net = computers[current][j];

                    if(net == 1 && !v[j]) {
                        queue.add(j);
                        v[j] = true;
                    }
                }
            }
        }

        return answer;
    }
}