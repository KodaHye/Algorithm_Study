import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//boj 15903
public class 카드합체놀이 {
    static int n,m;
    static int[] card;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        card = new int[n];
        st = new StringTokenizer(br.readLine());
        Queue<Long> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            q.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            Long c1 = q.poll();
            Long c2 = q.poll();
            q.add(c1 + c2);
            q.add(c1 + c2);
        }

        long sum=0;
        while (!q.isEmpty()) {
            sum += q.poll();
        }

        System.out.println(sum);



    }
}