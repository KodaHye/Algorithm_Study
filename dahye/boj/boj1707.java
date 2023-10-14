package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
이분 그래프
 */
public class boj1707 {
    static int K, V, E, check[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static ArrayList<Integer> adj[];
    static boolean result, v[];

    public static void main(String[] args) throws Exception {
        K = Integer.parseInt(br.readLine());

        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            adj = new ArrayList[V + 1];
            check = new int[V + 1];
            v = new boolean[V + 1];
            result = true;

            for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj[a].add(b);
                adj[b].add(a);
            }

            for(int i = 1; i < V + 1; i++) {
                if(result) {
                    dfs(i);
                } else {
                    break;
                }
            }

            if(!result) sb.append("NO" + "\n");
            else sb.append("YES" + "\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int current) {
        v[current] = true;

        for(int next: adj[current]) {
            if(!v[next]) {
                check[next] = (check[current] + 1) % 2;
                dfs(next);
            }
            else if(check[current] == check[next]) result = false;
        }
    }
}
