package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한_배낭 {

    static int N, K;
    static int[] W, V;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N+1];
        V = new int[N+1];

        dp = new int[N+1][K+1];

        W[0] = V[0] = 0;

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(dp[N][K]);
    }

    /**
     * dp[i][j] : i번째 물품을 넣으려는데, 가방에 넣을 수 있는 최대 무게가 j인 경우, 넣을 수 있는 최대 가치를 넣는 배열
     */
    static void solve() {

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= K ; j++) {
                if (W[i] <= j) { // i번째 물품을 최대 무게가 j인 배낭에 넣을 수 있는 경우
                    // Math.max(i번째 물품의 가치 + i번째 물품을 넣음으로서 생긴 빈 공간에 들어갈 수 있는 물품의 최대 가치, 물품을 넣지 않는 경우의 최대 가치)
                    dp[i][j] = Math.max(V[i] + dp[i - 1][j - W[i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
}
