package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj16928 {
    static int N, M, min;
    static Map<Integer, Integer> laddle, snake;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/input/boj16928.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        laddle = new HashMap<>();
        snake = new HashMap<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            laddle.put(x, y);
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            snake.put(u, v);
        }

//        min = Integer.MAX_VALUE;
//        findMin(0, 1);
//        System.out.println(min);

        findMinBfs();
    }

    static void findMin(int count, int currentIndex){
        if(count > min) return;
        if(currentIndex == 100){
            min = Math.min(min, count);
            return;
        }

        for(int i = 1; i<=6; i++){
            int nextIndex = currentIndex + i;
            if(laddle.containsKey(nextIndex)){
                nextIndex = laddle.get(currentIndex + i);
            }else if(snake.containsKey(nextIndex)){
                nextIndex = snake.get(currentIndex + i);
            }

            findMin(count+1, nextIndex);
        }
    }

    static class Point{
        int count, currentIndex;
        Point(int count, int currentIndex){
            this.count = count;
            this.currentIndex = currentIndex;
        }
    }
    static void findMinBfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[] visit = new boolean[101];

        queue.offer(new Point(0, 1));
        visit[1] = true;

        while (!queue.isEmpty()){
            Point p = queue.poll();

            for(int i = 1; i<= 6; i++){
                int nextIndex = p.currentIndex + i;
                if(laddle.containsKey(nextIndex)){
                    nextIndex = laddle.get(p.currentIndex + i);
                }else if(snake.containsKey(nextIndex)){
                    nextIndex = snake.get(p.currentIndex + i);
                }

                if(visit[nextIndex] || nextIndex > 100) continue;
                visit[nextIndex] = true;
                if(nextIndex == 100){
                    System.out.println(p.count + 1);
                    return;
                }
                queue.offer(new Point(p.count + 1, nextIndex));
            }
        }
    }
}
