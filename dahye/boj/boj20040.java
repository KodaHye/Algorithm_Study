package dahye.boj;

import java.io.*;
import java.util.*;

public class boj20040 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, answer, parent[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		for(int k = 0; k < m; k++) {
			
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			answer++;
			
			
			if(find(a) == find(b)) {
				System.out.println(answer);
				return;
			}
			
			union(a, b);
		}
		
		System.out.println(0);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) parent[b] = a;
	}
	
	private static int find(int a) {
		return parent[a] = parent[a] == a ? a : find(parent[a]);
	}
}
