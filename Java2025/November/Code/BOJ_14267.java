import java.util.*;
import java.io.*;

class BOJ_14267 {
    static List<List<Integer>> graph=new ArrayList<>();
    static long[] result;
    static int[] parents;
    static int n;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 14267;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        result=new long[n+1];
        parents=new int[n+1];

        st=new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            int parent=Integer.parseInt(st.nextToken());
            if(parent==-1){
                continue;
            }
            parents[i]=parent;
            graph.get(i).add(parent);
            graph.get(parent).add(i);
        }

        for(int j=0;j<m;j++){
            st=new StringTokenizer(br.readLine(), " ");
            int i=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            bfs(i, w);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(result[i]+" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void bfs(int start, int weight){
        boolean[] visited=new boolean[n+1];
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(start);
        while(!q.isEmpty()){
            int current=q.poll();
            if(visited[current]){
                continue;
            }
            visited[current]=true;
            result[current]+=weight;
            for(int next : graph.get(current)){
                if(parents[current]!=next){
                    q.offer(next);
                }
            }
        }
    }
}