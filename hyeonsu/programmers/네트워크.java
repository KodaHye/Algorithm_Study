package hyeonsu.programmers;

import java.util.*;

class 네트워크 {
    public int solution(int n, int[][] computers) {
        boolean[] checked = new boolean[n];
        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(!checked[i]) {
                answer++;
                bfs(computers, checked, i, n);
            }
        }
        return answer;
    }

    public void bfs(int[][] computers, boolean[] checked, int start, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        checked[start] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i = 0; i < n; i++) {
                if(cur == i || checked[i]) continue;
                if(computers[cur][i] == 1) {
                    checked[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
