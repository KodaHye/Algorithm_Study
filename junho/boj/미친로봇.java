import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//boj1405
public class 미친로봇 {
    static int n;
    static double[] dir;
    static boolean[][] v;
    static double result;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        dir = new double[4];
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            dir[i] = Double.parseDouble(st.nextToken())*0.01;
        }
        v = new boolean[30][30];
        v[15][15] = true;
        dfs(15, 15, 1);
        System.out.println(result);

    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    private static void dfs(int x, int y, double pert) {

        if (n == 0) {
            result += pert;
            return;
        }

        for (int i = 0; i < 4; i++) {
            //방문 했으면
            if(v[x+dx[i]][y+dy[i]]) continue;
            //확률이 0이면
            if(dir[i]==0) continue;
            n--;
            v[x + dx[i]][y + dy[i]] = true;
            dfs(x + dx[i], y + dy[i], pert * dir[i]);
            n++;
            v[x + dx[i]][y + dy[i]] = false;
        }
    }
}
