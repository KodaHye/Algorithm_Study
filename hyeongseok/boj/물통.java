package hyeongseok.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물통 {

	static ArrayList<Integer> list = new ArrayList<>();
	static boolean[][][] visited;
	static int totalA, totalB, totalC;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		visited = new boolean[200][200][200];

		totalA = Integer.parseInt(st.nextToken());
		totalB = Integer.parseInt(st.nextToken());
		totalC = Integer.parseInt(st.nextToken());

		solve();
	}

	// 물을 붓기위한 총 6가지의 방법
	// (A -> B), (A -> C), (B -> A), (B -> C), (C -> A), (C -> B)
	static int[][] pourSequence = {
		{0, 1},
		{0, 2},
		{1, 0},
		{1, 2},
		{2, 0},
		{2, 1}
	};

	// 내 물통에 있는 걸 다 붓거나, 붓고 있는 물통이 꽉 차면 Stop
	static public void solve() {
		Queue<AllBottle> Q = new LinkedList<>();

		Q.add(new AllBottle(totalA, totalB, totalC));

		while (!Q.isEmpty()) {
			AllBottle ab = Q.poll();

			int a = ab.a;
			int b = ab.b;
			int c = ab.c;

			if (a == 0) {
				list.add(c);
			}

			// 물을 붓기위한 총 6가지의 방법
			// (A -> B), (A -> C), (B -> A), (B -> C), (C -> A), (C -> B)
			for (int i = 0 ; i < 6 ; i++) {
				int[] tmp = new int[2];

				switch (i) {
					case 0:
						tmp = pourBottle(a, b);
						a = tmp[0];
						b = tmp[1];
						break;
				}
			}
		}
	}

	static public int[] pourBottle(int minusBottle, int plusBottle) {
		// minusBottle에 있는 물을 plusBottle에 다 넣을 수 있는 경우
		if ()
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
