package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//boj 20040
public class 사이클게임 {

    static int n,m;
    static int[] parent;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i]=i;
        }
        int anw = 0;
        int cnt = 0;
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cnt++;
            //부모가 다르면
            if(find(a)!=find(b)){
                //union
                union(a,b);
            }
            //부모가 같으면 사이클
            else {
                flag= true;
                anw=cnt;
                break;
            }

        }
        if(flag) System.out.println(anw);
        else System.out.println(0);

    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x <= y) {
            parent[y]=x;
        }else {
            parent[x]=y;
        }
    }

    private static int find(int x) {
        if(parent[x]==x) return x;
        else return find(parent[x]);
    }
}
