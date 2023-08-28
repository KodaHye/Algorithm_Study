import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 거북이 {
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int max_x=0,min_x=0,max_y=0,min_y=0;
            int x=0,y=0,dir=0;

            String order = br.readLine();

            for (int i = 0; i <order.length() ; i++) {
                char c = order.charAt(i);

                if (c == 'F') {
                    x += dx[dir];
                    y += dy[dir];
                } else if (c == 'B') {
                    x -= dx[dir];
                    y -= dy[dir];
                } else if (c == 'L') {
                    if (dir == 0) {
                        dir = 3;
                    } else dir--;
                } else {
                    dir=(dir+1)%4;
                }
                max_x = Math.max(max_x, x);
                min_x = Math.min(min_x, x);
                max_y = Math.max(max_y, y);
                min_y = Math.min(min_y, y);

            }
            System.out.println((Math.abs(min_x)+Math.abs(max_x))*(Math.abs(min_y)+Math.abs(max_y)));

        }
    }
}
