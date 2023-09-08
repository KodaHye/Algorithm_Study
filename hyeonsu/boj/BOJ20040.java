package hyeonsu.boj;

import java.io.*;
import java.util.*;

public class BOJ20040 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, ans = 0;
    static int[] parents, rank;

    public static void main(String[] args) throws IOException{

        //초기화
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        parents = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        //로직
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            if (find(a) != find(b)) {
                union(a, b);
            } else {
                ans = i + 1;
                break;
            }
        }

        //출력
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void union(int a, int b) {
        int aParents = find(a);
        int bParents = find(b);

        if (aParents < bParents) {
            parents[bParents] = aParents;
        } else {
            parents[aParents] = bParents;
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

}
