package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
소풍
 */
public class boj2026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, F;
    static ArrayList<Integer> adj[], result;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        result = new ArrayList<>();

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        bfs();

        if(result.size() == 0) {
            System.out.println(-1);
            return;
        }
        for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }

    private static void bfs() {

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> tmpList = new ArrayList<>();

        for(int i = 1; i < N + 1; i++) {
            int tmp = 0;
            boolean v[] = new boolean[N + 1];

            if(adj[i].size() != 0 && !v[i]) {
                queue.add(i);
                tmpList.add(i);
                tmp++;
                v[i] = true;

                while (!queue.isEmpty()) {
                    int current = queue.poll();

                    for (Integer next: adj[current]) {
                        if(!v[next]) {
                            queue.add(next);
                            tmpList.add(next);
                            tmp++;
                            v[next] = true;
                        }
                    }
                }

                if(tmp == K) {
                    if(result.size() == 0) result = new ArrayList<>(tmpList);

                    for(int j = 0; j < Math.min(result.size(), tmpList.size()); j++) {
                        if(result.get(j) > tmpList.get(j)) {
                            result = new ArrayList<>(tmpList);
                        }
                    }
                }
            }
        }
    }
}
