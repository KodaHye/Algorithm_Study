package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class 물통 {

    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[][][] visited;
    static int totalA, totalB, totalC;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        visited = new boolean[201][201][201];

        totalA = Integer.parseInt(st.nextToken());
        totalB = Integer.parseInt(st.nextToken());
        totalC = Integer.parseInt(st.nextToken());

		visited[0][0][totalC] = true;

        solve();

		// 중복 제거
		List<Integer> answer = list.stream().distinct().collect(Collectors.toList());

		// 결과 출력
		Collections.sort(answer);

		for (int i = 0 ; i < answer.size() ; i++) {
			System.out.print(answer.get(i) + " ");
		}
    }

    // 내 물통에 있는 걸 다 붓거나, 붓고 있는 물통이 꽉 차면 Stop
    static public void solve() {
        Queue<AllBottle> Q = new LinkedList<>();

        Q.add(new AllBottle(0, 0, totalC));

        while (!Q.isEmpty()) {
            AllBottle ab = Q.poll();

            int inputA = ab.a;
            int inputB = ab.b;
            int inputC = ab.c;

            if (inputA == 0) {
                list.add(inputC);
            }

            // 물을 붓기위한 총 6가지의 방법
            // (A -> B), (A -> C), (B -> A), (B -> C), (C -> A), (C -> B)
            for (int i = 0; i < 6; i++) {
                int[] tmp = new int[2];
                int outputA = inputA;
                int outputB = inputB;
                int outputC = inputC;

                switch (i) {
                    case 0:
                        tmp = pourBottle(inputA, inputB, totalB);
                        outputA = tmp[0];
                        outputB = tmp[1];
                        break;
                    case 1:
                        tmp = pourBottle(inputA, inputC, totalC);
                        outputA = tmp[0];
                        outputC = tmp[1];
                        break;
                    case 2:
                        tmp = pourBottle(inputB, inputA, totalA);
                        outputB = tmp[0];
                        outputA = tmp[1];
                        break;
                    case 3:
                        tmp = pourBottle(inputB, inputC, totalC);
                        outputB = tmp[0];
                        outputC = tmp[1];
                        break;
                    case 4:
                        tmp = pourBottle(inputC, inputA, totalA);
                        outputC = tmp[0];
                        outputA = tmp[1];
                        break;
                    case 5:
                        tmp = pourBottle(inputC, inputB, totalB);
                        outputC = tmp[0];
                        outputB = tmp[1];
                        break;
                }

                if (outputA > -1 && outputB > -1 && outputC > -1 && !visited[outputA][outputB][outputC]) {
					visited[outputA][outputB][outputC] = true;
                    Q.add(new AllBottle(outputA, outputB, outputC));
                }
            }
        }
    }

    /**
     * @param minusBottle : 현재 비워야하는 물통에 있는 물의 양
     * @param plusBottle  : 현재 채워야하는 물통에 있는 물의 양
     * @param maxBottle   : 현재 채워야하는 물통의 최대용량
     */
    static public int[] pourBottle(int minusBottle, int plusBottle, int maxBottle) {
        int[] tmp = new int[2];

		if (minusBottle > 0) {
			// minusBottle 에 있는 물을 plusBottle 에 다 넣을 수 있는 경우
			if (minusBottle + plusBottle <= maxBottle) {
				tmp[0] = 0;
				tmp[1] = minusBottle + plusBottle;
			}
			// minusBottle 에 있는 물을 plusBottle 에 다 넣을 수 없는 경우 -> 넣다가 중간에 다 차는 경우
			else if (minusBottle + plusBottle > maxBottle) {
				tmp[0] = minusBottle - (maxBottle - plusBottle);
				tmp[1] = maxBottle;
			}
		} else {
			tmp[0] = -1;
			tmp[1] = -1;
		}

        return tmp;
    }

    static class AllBottle {
        int a, b, c;

        public AllBottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
