package hyeonsu.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ17255 {

    static Scanner sc = new Scanner(System.in);

    static String n;
    static int ans, len;
    static char[] nums;

    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        n = sc.nextLine();
        nums = n.toCharArray();
        len = n.length();

        for (int i = 0; i < len; i++) {
            dfs(i, i, "" + nums[i], "" + nums[i]);
        }

        System.out.println(set.size());
    }

    static void dfs(int l, int r, String s, String path) {
        if (l == 0 && r == len - 1) {
            set.add(path);
            return;
        }

        //좌로 이동
        if (l - 1 >= 0){
            dfs(l - 1, r, nums[l - 1] + s, path + nums[l - 1] + s);
        }

        //우로 이동
        if (r + 1 <= len - 1) {
            dfs(l, r + 1, s + nums[r + 1], path + nums[r + 1] + s);
        }
    }
}
