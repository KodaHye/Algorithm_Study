//boj 16724

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 피리부는사나이 {
    static int n,m,cnt;
    static int[][] map;
    static boolean[][] v;
    static boolean[][] end;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];
        end = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                //위이면 0
                if(input.charAt(j)=='U') map[i][j]=0;
                //오른쪽이면 1
                else if(input.charAt(j)=='R') map[i][j]=1;
                //아래쪽이면 2
                else if(input.charAt(j)=='D') map[i][j]=2;
                // 왼쪽이면 3
                else map[i][j]=3;
            }
            
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!v[i][j]){
                    dfs(i,j);
                }
            }
        }
        System.out.println(cnt);
    }

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    private static void dfs(int x, int y) {

        v[x][y]=true;

        int nx = x+dx[map[x][y]];
        int ny = y+dy[map[x][y]];

        //방문 했으면 사이클 마지막 지점이 방문안했으면 safezone 추가
        if (v[nx][ny]) {
            if (!end[nx][ny]) {
                cnt++;
            }
        }
        //아니면 사이클 진행
        else {
            dfs(nx,ny);
        }
        end[x][y]=true;




    }
}
