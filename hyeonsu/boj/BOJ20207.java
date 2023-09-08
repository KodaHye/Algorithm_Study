package hyeonsu.boj;

import java.util.*;
import java.io.*;

public class BOJ20207 {

    static final int S = 0;
    static final int E = 1;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, ans;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine());
        cnt = new int[366];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            for (int j = s; j <= e; j++) {
                cnt[j]++;
            }
        }

        //로직
        //직사각형의 코팅지 크기 구하기
        int idx = 1;
        while (true) {
            if (idx > 365) break;

            int max = Integer.MIN_VALUE;
            int dayCnt = 0;
            while (cnt[idx] > 0) {
                max = Math.max(max, cnt[idx]);
                dayCnt++;
                idx++;

                if (idx > 365) break;
            }

            if (dayCnt == 0) {
                idx++;
            } else {
                ans += dayCnt * max;
                idx++;
            }

        }

        //출력
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class Plan implements Comparable<Plan>{
        int s;
        int e;
        int term;

        public Plan(int s, int e, int term) {
            this.s = s;
            this.e = e;
            this.term = term;
        }

        @Override
        public int compareTo(Plan o) {
            if (this.s == o.s) {
                return o.term - this.term;
            }
            return this.s - o.s;
        }
    }
}
