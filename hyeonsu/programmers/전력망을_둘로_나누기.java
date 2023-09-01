package hyeonsu.programmers;

import java.util.*;

public class 전력망을_둘로_나누기 {
    int[][] adj;
    boolean[] checked;
    int ans = Integer.MAX_VALUE;
    int len;

    public int solution(int n, int[][] wires) {
        adj = new int[n+1][n+1];
        len = wires.length;
        for(int i = 0; i < len; i++) {
            adj[wires[i][0]][wires[i][1]] = 1;
            adj[wires[i][1]][wires[i][0]] = 1;
        }

        //모든 간선을 하나씩 끊어보면서 확인
        int left = 0, right = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                //이어진 간선이라면 전력망을 2개로 나눈다.
                if(adj[i][j] == 1) {
                    checked = new boolean[n+1];
                    adj[i][j] = 0;
                    adj[j][i] = 0;
                    left = bfs(i, n);
                    //아직 가지 않은 전력망도 마저 확인
                    for(int k = 1; k <= n; k++) {
                        if(!checked[k]) {
                            right = bfs(k, n);
                        }
                    }
                    //송전탑 개수의 차이값 확인 후 갱신
                    ans = Math.min(Math.abs(left - right), ans);
                    //간선 되돌리기
                    adj[i][j] = 1;
                    adj[j][i] = 1;
                }
            }
        }

        return ans;
    }

    public int bfs(int start, int n) {
        int cnt = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        checked[start] = true;

        while(!q.isEmpty()) {
            //꺼내기
            int cur = q.poll();
            //인접한 정점 확인
            for(int i = 1; i <= n; i++) {
                //갈 수 있는가? : 이어졌으면서 방문하지 않은 송전탑
                if(adj[cur][i] == 1 && !checked[i]) {
                    //큐에 넣기(체크인)
                    cnt++;
                    checked[i] = true;
                    q.add(i);
                }
            }
        }

        return cnt;
    }
}
