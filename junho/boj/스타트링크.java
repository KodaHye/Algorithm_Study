import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크 {

    static class Point{
        int x,cnt;

        public Point(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
    static int f,s,g,u,d,min;
    static boolean flag;
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        min=0;
        flag = false;
        bfs(s);

        if(flag){
            System.out.println(min);
        }else {
            System.out.println("use the stairs");
        }

    }

    private static void bfs(int x) {
        Queue<Point> q = new LinkedList<>();
        v = new boolean[f+1][2];
        q.add(new Point(x,0));
        v[1][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            //목적지 층에 도착
            if (cur.x == g) {
                flag=true;
                min=cur.cnt;
                return;
            }
            int nx_up = cur.x+u;
            int nx_down=cur.x-d;
            //위로 갈 수 있는경우
            if(nx_up<=f&&nx_up>cur.x&&!v[nx_up][0]){
                v[nx_up][0]=true;
                q.add(new Point(nx_up, cur.cnt+1));
            }
            //아래로 갈 수 있는 경우
            if(nx_down>=1&&nx_down< cur.x&&!v[nx_down][1]){
                v[nx_down][1]=true;
                q.add(new Point(nx_down, cur.cnt+1));
            }
        }

    }
}
