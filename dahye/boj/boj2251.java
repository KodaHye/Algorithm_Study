package dahye.boj;

/*
물통
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj2251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int water[];
    static boolean ans[];
    static boolean v[][][];
    static int pour[][] = {{0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        water = new int[3];

        for(int i = 0; i < 3; i++) water[i] = Integer.parseInt(st.nextToken());

        v = new boolean[water[0] + 1][water[1] + 1][water[2] + 1];
        ans = new boolean[water[2] + 1];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, water[2]});
        v[0][0][water[2]] = true;

        while(!queue.isEmpty()) {
            int current[] = queue.poll();

            if(current[0] == 0) ans[current[2]] = true;
            for(int i = 0; i < 6; i++) {
                int now[] = new int[] {current[0], current[1], current[2]};

                now[pour[i][1]] = now[pour[i][1]] + now[pour[i][0]];
                now[pour[i][0]] = 0;

                if(now[pour[i][1]] > water[pour[i][1]]) {
                    now[pour[i][0]] = now[pour[i][1]] - water[pour[i][1]];
                    now[pour[i][1]] = water[pour[i][1]];
                }

                if(!v[now[0]][now[1]][now[2]]) {
                    queue.add(new int[] {now[0], now[1], now[2]});
                    v[now[0]][now[1]][now[2]] = true;
                }
            }
        }

        for(int i = 0; i < ans.length; i++) {
            if(ans[i]) System.out.print(i + " ");
        }
    }
}
