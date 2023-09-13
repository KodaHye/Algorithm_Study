package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 호텔 {


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // i명의 고객을 얻고자 할 때, 필요한 최소 비용을 담는 배열
        int[] dp = new int[C+101];

        for (int i = 0 ; i < C+101 ; i++) {
            dp[i] = 100000000;
        }

        dp[0] = 0;

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            // i번째 도시에서 1의 배수만큼 투자하면 customer 의 고객을 얻을 수 있으므로, i번째 도시에서 최대한 투자를 해서 얻을 수 있는 인원의 경우의 수를 모두 찾는다
            for (int j = customer ; j < C + 101 ; j++) {
                // dp[j] : i번째 도시에선 투자를 하지 않은 경우, j 만큼의 고객을 얻기 위해 든 비용
                // cost + dp[j - customer] : i번째 도시에서 투자를 한 경우 든 비용 + j에서 customer 만큼 뺀 고객을 얻기 위해 든 비용
                dp[j] = Math.min(dp[j], cost + dp[j - customer]);
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = C ; i < C + 101 ; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);

    }

}
