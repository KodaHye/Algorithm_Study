package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 회사 문화 1
 * 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력
 * 부모의 칭찬 점수를 자식에게 그대로 물려주면 됨..
 */
public class boj14267 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, d[];
	static ArrayList<Integer> adj[];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());
		
		adj = new ArrayList[n + 1];
		d = new int[n + 1];
		
		for(int k = 0; k < adj.length; k++) adj[k] = new ArrayList<Integer>();
		
		int idx = 0;
		
		while(st.hasMoreTokens()) {
			int k = Integer.parseInt(st.nextToken());
			if(k != -1) {
				adj[k].add(idx + 1);
			}
			idx++;
		}
		
		for(int k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());

			int i = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			d[i] += w;
		}
		
		dfs(1);

		for(int i = 1; i < d.length; i++) {
			sb.append(d[i] + " ");
		}
		
		System.out.println(sb);
	}
	private static void dfs(int i) {
		for(int next: adj[i]) {
			d[next] += d[i];
			dfs(next);
		}
	}
}