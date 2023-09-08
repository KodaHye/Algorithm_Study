package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 카드_섞기 {

    static int N, answer;
    static int[] P, S, card;
    static ArrayList<Integer> player0, player1, player2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        P = new int[N];
        S = new int[N];
        card = new int[N];
        player0 = new ArrayList<>();
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            card[i] = i;
        }

        StringTokenizer st1 = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            P[i] = Integer.parseInt(st1.nextToken());

            if (P[i] == 0) player0.add(i);
            else if (P[i] == 1) player1.add(i);
            else if (P[i] == 2) player2.add(i);

            S[i] = Integer.parseInt(st2.nextToken());
        }


        solve();

        System.out.println(answer);
    }

    static void solve() {
        int cnt = 0;
        boolean possible = true;

        while (!checkCard()) {
            int[] tmp = copy();

            // S 배열에 맞게 카드 섞기
            for (int i = 0 ; i < N ; i++) {
                card[S[i]] = tmp[i];
            }

            cnt++;

            if (cnt >= 130000) {
                possible = false;
                break;
            }
        }

        if (possible)
            answer = cnt;
        else
            answer = -1;
    }

    static int[] copy() {
        int[] copiedArray = new int[N];

        for (int i = 0 ; i < N ; i++) {
            copiedArray[i] = card[i];
        }

        return copiedArray;
    }

    static boolean checkCard() {

        // 현재 card 배열의 순서대로 플레이어에게 카드를 나눠줬을 때, 결과
        for (int i = 0; i < N ; i++) {
            if (i % 3 == 0) { // 0번 플레이어
                if (!player0.contains(card[i])) {
                    return false;
                }
            } else if (i % 3 == 1) { // 1번 플레이어
                if (!player1.contains(card[i])) {
                    return false;
                }
            } else { // 2번 플레이어
                if (!player2.contains(card[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
