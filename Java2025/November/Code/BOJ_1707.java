import java.util.*;
import java.io.*;

class BOJ_1707 {
    static int[] color;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1707;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        int K=Integer.parseInt(br.readLine());
        for(int k=0;k<K;k++){
            boolean isBipartite=true;
            graph=new ArrayList<>();
            st=new StringTokenizer(br.readLine(), " ");

            int V=Integer.parseInt(st.nextToken());
            color=new int[V+1];
            for(int v=0;v<=V;v++){
                graph.add(new ArrayList<>());
            }

            int E=Integer.parseInt(st.nextToken());
            for(int e=0;e<E;e++){
                st=new StringTokenizer(br.readLine(), " ");
                int from=Integer.parseInt(st.nextToken());
                int to=Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            // 이분 그래프 알고리즘
            for(int v=1;v<=V;v++){
                if(color[v]>0){
                    continue;
                }

                color[v]=1;
                if(!bfs(v)){ // 노드 v와 인접한 노드의 색이 일치하면, 이분 그래프가 아님
                    isBipartite=false;
                    break;
                }
            }
            if(isBipartite){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
        br.close();
    }
    static boolean bfs(int v){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(v);
        while(!q.isEmpty()){
            int current=q.poll();
            int nextColor=color[current]%2+1;

            for(int next : graph.get(current)){
                if(color[next]==0){ // 미방문
                    color[next]=nextColor;
                    q.offer(next);
                }else if(color[next]!=nextColor){ // 인접 노드와 현재 노드의 색상이 같음(이분 X)
                    return false;
                }
            }
        }

        return true;
    }
}