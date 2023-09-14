package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 회사_문화_1 {

    static int N, M;
    static int[] dp, W;
    static List<List<Integer>> company = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            company.add(new ArrayList<>());
        }

        W = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(bf.readLine());

        String dump = st.nextToken();

        for (int i = 1 ; i < N ; i++) {
            // 직상 상사
            int boss = Integer.parseInt(st.nextToken());
            // 부하 직원
            int employee = i+1;

            company.get(boss).add(employee);
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());

            int employee = Integer.parseInt(st.nextToken());

            int w = Integer.parseInt(st.nextToken());

            dp[employee] += w;
        }

        dfs(1);

        for (int i = 1 ; i <= N ; i++) {
            System.out.print(dp[i] + " ");
        }
    }

    static void dfs(int boss) {

        for (int i = 0 ; i < company.get(boss).size() ; i++) {
            int employee = company.get(boss).get(i);

            // boss 가 받은 만큼 employee 도 칭찬받아야함
            dp[employee] += dp[boss];

            dfs(employee);
        }
    }
}
