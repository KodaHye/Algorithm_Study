package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * LCA
 * 두 노드의 쌍이 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력
 */
public class boj11437 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, p[], depth[], parent[], startA, startB;
	static ArrayList<Integer> adj[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N + 1];
		depth = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) adj[i] = new ArrayList();
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		M = Integer.parseInt(br.readLine());
		
		dfs(1, 1);

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b, depth[a], depth[b]) + "\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int current, int cnt) {
		depth[current] = cnt;
		
		for(int next: adj[current]) {
			if(depth[next] != 0) continue;
			parent[next] = current; 
			dfs(next, cnt + 1);
		}
	}

	private static int lca(int a, int b, int depthA, int depthB) {
		if(depthA > depthB) {
			while(depthA != depthB) {
				depthA--;
				a = parent[a];
			}
		} else if(depthA < depthB) {
			while(depthA != depthB) {
				depthB--;
				b = parent[b];
			}
		}
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}
	
}
