import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//boj 2251
public class 물통 {
    static class WaterBottle{
        int a,b,c;
        public WaterBottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static int max_a,max_b,max_c;
    static ArrayList<Integer> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        max_a = Integer.parseInt(st.nextToken());
        max_b = Integer.parseInt(st.nextToken());
        max_c = Integer.parseInt(st.nextToken());
        result = new ArrayList<>();

        Queue<WaterBottle> q = new LinkedList<>();
        boolean[][][] v = new boolean[max_a+1][max_b+1][max_c+1];
        q.add(new WaterBottle(0, 0, max_c));
        while (!q.isEmpty()) {
            WaterBottle p = q.poll();
            if(v[p.a][p.b][p.c]) continue;
            v[p.a][p.b][p.c]=true;
            if (p.a == 0) {
                result.add(p.c);
            }
           //a->b
            if (p.a + p.b <= max_b) {
                q.add(new WaterBottle(0, p.a + p.b, p.c));
            } else {
                q.add(new WaterBottle(p.a + p.b - max_b, max_b, p.c));
            }
            //b->a
            if (p.a + p.b <= max_a) {
                q.add(new WaterBottle(p.a + p.b, 0, p.c));
            } else {
                q.add(new WaterBottle(max_a, p.a + p.b - max_a, p.c));
            }
            //a->c
            if (p.a + p.c <= max_c) {
                q.add(new WaterBottle(0, p.b, p.a + p.c));
            } else {
                q.add(new WaterBottle(p.a + p.c - max_c, p.b, max_c));
            }
            //c->a
            if (p.a + p.c <= max_a) {
                q.add(new WaterBottle(p.a + p.c, p.b, 0));
            } else {
                q.add(new WaterBottle(max_a, p.b, p.a + p.c - max_a));
            }
            //b->c
            if (p.b + p.c <= max_c) {
                q.add(new WaterBottle(p.a, 0, p.b + p.c));
            } else {
                q.add(new WaterBottle(p.a, p.b + p.c - max_c, max_c));
            }
            //c->b
            if (p.b + p.c <= max_b) {
                q.add(new WaterBottle(p.a, p.b + p.c, 0));
            } else {
                q.add(new WaterBottle(p.a,  max_b, p.b + p.c - max_b));
            }

        }
        Collections.sort(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i)+" ");
        }
    }
}
