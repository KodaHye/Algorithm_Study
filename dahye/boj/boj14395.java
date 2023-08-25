import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
4연산
 */
public class boj14395 {
    static class info {
        long n;
        String s;

        public info(long n, String s) {
            this.n = n;
            this.s = s;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int s, t;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        Queue<info> queue = new LinkedList<>();
        HashSet<Long> set = new HashSet<>();

        queue.add(new info(s, ""));

        if(s == t) {
            System.out.println(0);
            return;
        }

        while(!queue.isEmpty()) {
            info info = queue.poll();

            if(info.n == t) {
                System.out.println(info.s);
                return;
            }

            if(0 <= info.n * info.n && info.n * info.n <= t && !set.contains(info.n * info.n)) {
                queue.add(new info(info.n * info.n, info.s + "*"));
                set.add(info.n * info.n);
            }
            if(0 <= info.n - info.n && info.n - info.n <= t && !set.contains(info.n - info.n)) {
                queue.add(new info(info.n - info.n, info.s + "-"));
                set.add(info.n - info.n);
            }
            if(0 <= info.n + info.n && info.n + info.n <= t && !set.contains(info.n + info.n)) {
                queue.add(new info(info.n + info.n, info.s + "+"));
                set.add(info.n + info.n);
            }
            if(info.n != 0 && 0 <= info.n / info.n && info.n / info.n <= t && !set.contains(info.n / info.n)) {
                queue.add(new info(info.n / info.n, info.s + "/"));
                set.add(info.n / info.n);
            }
        }

        System.out.println(-1);
    }
}
