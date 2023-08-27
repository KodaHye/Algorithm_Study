package dahye.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
이차원 배열과 연산
 */
class numCount implements Comparable<numCount>{
    int num, count;

    public numCount(int num, int count) {
        super();
        this.num = num;
        this.count = count;
    }
    @Override
    public int compareTo(numCount o) {
        if(this.count==o.count) {
            return Integer.compare(this.num, o.num);
        }
        return Integer.compare(this.count, o.count);
    }
}

public class boj17140 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map=new int[100][100];
    static int r,c,k;
    static int rlen,clen;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rlen=3;
        clen=3;
        int time=0;
        if(map[r][c]==k) {
            System.out.println(0);
            return;
        }
        while(time++ <100) {
            if(rlen>=clen) {
                sortR();
            }else {
                sortC();
            }
            if(map[r][c]==k) {
                System.out.println(time);
                return;
            }
        }
        System.out.println(-1);
    }


    private static void sortC() {
        int[][] copy=new int[100][100];
        rlen=0;
        for (int j = 0; j < clen; j++) {
            int[] count=new int[101];
            ArrayList<numCount> list=new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                if(map[i][j]==0)continue;
                count[map[i][j]]+=1;
            }

            for (int i = 0; i < 100; i++) {
                if(map[i][j]==0)continue;
                if(count[map[i][j]]>0) {
                    list.add(new numCount(map[i][j],count[map[i][j]]));
                    count[map[i][j]]=0;
                }

            }
            rlen=Math.max(rlen, list.size()*2);
            if(rlen>100)rlen=100;
            Collections.sort(list);
            for (int i = 0; i <100; i+=2) {
                if(list.size()==0)break;
                copy[i][j]=list.get(0).num;
                if(i+1>=100)break;
                copy[i+1][j]=list.get(0).count;
                list.remove(0);
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j]=copy[i][j];
            }
        }

    }
    private static void sortR() {
        int[][] copy=new int[100][100];
        clen=0;
        for (int i = 0; i < rlen; i++) {
            int[] count=new int[101];

            ArrayList<numCount> list=new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                if(map[i][j]==0)continue;
                count[map[i][j]]+=1;
            }

            for (int j = 0; j < 100; j++) {
                if(map[i][j]==0)continue;
                if(count[map[i][j]]>0) {
                    list.add(new numCount(map[i][j],count[map[i][j]]));
                    count[map[i][j]]=0;
                }
            }
            Collections.sort(list);
            clen=Math.max(clen, list.size()*2);
            if(clen>=100)clen=100;
            for (int j = 0; j <100; j+=2) {
                if(list.size()==0)break;
                copy[i][j]=list.get(0).num;
                if(j+1>=100)break;
                copy[i][j+1]=list.get(0).count;
                list.remove(0);
            }

        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j]=copy[i][j];
            }
        }
    }
}