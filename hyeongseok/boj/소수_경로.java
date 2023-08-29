package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소수_경로 {

    static boolean[] prime;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // false 가 소수
        prime = new boolean[10000];
        prime[0] = prime[1] = true;
        for (int i = 2; i < 10000; i++) {
            if (!prime[i]) {
                for (int j = i + i; j < 10000; j += i) {
                    prime[j] = true;
                }
            }
        }

        int T = Integer.parseInt(bf.readLine());

        for (int Test = 0 ; Test < T ; Test++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            answer = 0;

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            boolean possibleAnswer = solve(num1, num2);

            if (possibleAnswer) {
                sb.append(answer).append("\n");
            } else {
                sb.append("Impossible").append("\n");
            }
        }

        System.out.println(sb);
    }

    static boolean solve(int num1, int num2) {
        Queue<Node> Q = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        Q.add(new Node(num1, 0));

        visited[num1] = true;

        while (!Q.isEmpty()) {
            int size = Q.size();

            Node n = Q.poll();

            int number = n.num;

            if (number == num2) {
                answer = n.cnt;
                return true;
            }

            for (int i = 0 ; i < 4 ; i++) {
                for (int j = 0 ; j < 10 ; j++) {
                    if (i == 0 && j == 0) { // 0번째 자리를 0으로 바꾸면 안된다
                        continue;
                    }

                    int k = changeNumber(number, i, j);

                    if (!prime[k] && !visited[k]) { // k가 소수이고 k를 방문하지 않았을 때
                        visited[k] = true;
                        Q.add(new Node(k, n.cnt + 1));
                    }
                }
            }

        }

        return false;
    }

    public static int changeNumber(int num, int i, int j) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.setCharAt(i, (char) (j + '0'));
        return Integer.parseInt(sb.toString());

    }

    static class Node {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
