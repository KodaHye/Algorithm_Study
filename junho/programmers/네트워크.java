package 프래그러머스;


class 네트워크 {
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        v = new boolean[computers.length];

        for(int i=0;i<computers.length;i++){
            if(!v[i]){
                answer++;
                dfs(i,computers);
            }
        }

        return answer;
    }

    public void dfs(int x,int[][] computers){
        v[x]=true;
        for(int i=0;i<computers.length;i++){
            if(v[i]) continue;
            if(computers[x][i]==1){
                dfs(i,computers);

            }
        }
    }
}
