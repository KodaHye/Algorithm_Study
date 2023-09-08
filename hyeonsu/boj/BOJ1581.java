package hyeonsu.boj;

import java.io.*;
import java.util.*;

public class BOJ1581 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ff, fs, sf, ss, ans;

    public static void main(String[] args) throws IOException {

        //입력
        st = new StringTokenizer(br.readLine());
        ff = stoi(st.nextToken());
        fs = stoi(st.nextToken());
        sf = stoi(st.nextToken());
        ss = stoi(st.nextToken());

        //로직
        //빠르게 시작하는 곡이 존재 하는가?
        if (ff > 0 || fs > 0) {
            ans += ff;
            if (fs > 0) {
                ans += 1;
                fs--;
                ans += ss;
                if (sf > 0) {
                    ans += (sf + fs) - (Math.abs(sf - fs));
                    if (sf > fs) {
                        ans += 1;
                    }
                }
            }
        }
        //빠르게 시작하는 곡이 없을 떄
        else {
            ans += ss;
            if (sf > 0) {
                ans += 1;
            }
        }

        //출력
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
