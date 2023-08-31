package hyeonsu.boj;

import java.io.*;
import java.util.*;

public class BOJ1581 {

    static final String FF = "FF";
    static final String FS = "FS";
    static final String SF = "SF";
    static final String SS = "SS";


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ff, fs, sf, ss, ans;
    static String start;

    public static void main(String[] args) throws IOException {

        //입력
        st = new StringTokenizer(br.readLine());
        ff = stoi(st.nextToken());
        fs = stoi(st.nextToken());
        sf = stoi(st.nextToken());
        ss = stoi(st.nextToken());

        //로직
        solve(0, false, new Stack<>());

        //출력
        System.out.println(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void solve(int cnt, boolean hasFastStart, Stack<String> album) {

        if (cnt == 0) {
            //녹음 한 곡 중 빠르게 시작하는 노래가 1곡이라도 존재할 시 그곡으로 시작해야함.
            if (ff > 0 || fs > 0) {
                if (ff > 0) {
                    ff--;
                    album.push(FF);
                    start = FF;
                    solve(cnt + 1, true, album);
                    album.pop();
                }
                if (fs > 0) {
                    fs--;
                    album.push(FS);
                    start = FS;
                    solve(cnt + 1, true, album);
                    album.pop();
                }
            } else {
                if (ff > 0) {
                    ff--;
                    album.push(FF);
                    start = FF;
                    solve(cnt + 1, true, album);
                    album.pop();
                }
                if (fs > 0) {
                    fs--;
                    album.push(FS);
                    start = FS;
                    solve(cnt + 1, true, album);
                    album.pop();
                }
                if (sf > 0) {
                    sf--;
                    album.push(SF);
                    start = SF;
                    solve(cnt + 1, hasFastStart, album);
                    album.pop();
                }
                if (ss > 0) {
                    ss--;
                    album.push(SS);
                    start = SS;
                    solve(cnt + 1, hasFastStart, album);
                    album.pop();
                }
            }
        } else {
            //빠르게 끝나는 노래일 경우
            if (album.peek() == FF || album.peek() == SF) {
                //빠르게 시작하는 노래가 와야한다.
                if (ff > 0) {
                    ff--;
                    album.push(FF);
                    solve(cnt + 1, true, album);
                    album.pop();
                }
                if (fs > 0) {
                    fs--;
                    album.push(FS);
                    solve(cnt + 1, true, album);
                    album.pop();
                }
            } else {
                //느리게 시작하는 노래가 와야한다.
                if (sf > 0) {
                    sf--;
                    album.push(SF);
                    solve(cnt + 1, hasFastStart, album);
                    album.pop();
                }
                if (ss > 0) {
                    ss--;
                    album.push(SS);
                    solve(cnt + 1, hasFastStart, album);
                    album.pop();
                }
            }
        }

        if (hasFastStart) {
            if (!start.equals(FF) && !start.equals(FS)) return;
            ans = Math.max(ans, cnt);
        } else {
            ans = Math.max(ans, cnt);
        }
    }
}
