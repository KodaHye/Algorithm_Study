import java.util.*;

public class programmers_네트워크 {

    public int solution(int n, int[][] computers) {
        int answer = 0;

        answer = findNetworksNum(computers);
        return answer;
    }

    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int findNetworksNum(int[][] computers){
        int n = computers.length;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];

        int count = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!visit[i][j] && !visit[j][i] && computers[i][j] == 1){
                    visit[i][j] = true;
                    visit[j][i] = true;
                    queue.offer(new Point(i, j));
                    queue.offer(new Point(j, i));

                    while(!queue.isEmpty()){
                        Point p = queue.poll();

                        for(int d = 0; d<n; d++){
                            if((visit[p.r][d] && visit[d][p.r]) || computers[p.r][d] == 0) continue;
                            visit[p.r][d] = true;
                            visit[d][p.r] = true;
                            queue.offer(new Point(p.r, d));
                            queue.offer(new Point(d, p.r));
                        }
                    }
                    count++;
                }
            }
        }

        return count;
    }
}
