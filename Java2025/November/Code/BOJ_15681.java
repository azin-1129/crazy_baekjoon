import java.util.*;
import java.io.*;

class BOJ_15681 {
    static List<List<Integer>> graph=new ArrayList<>();
    static int[] childCount, parent;
    static int N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 15681;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        childCount=new int[N+1];
        parent=new int[N+1];
        for(int n=0;n<=N;n++){
            graph.add(new ArrayList<>());
            childCount[n]+=1;
        }
        int R=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());
        for(int n=0;n<N-1;n++){
            st=new StringTokenizer(br.readLine(), " ");
            int U=Integer.parseInt(st.nextToken());
            int V=Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }

        bfs(R);
        // System.out.println(Arrays.toString(childCount));
        StringBuilder sb=new StringBuilder();
        for(int q=0;q<Q;q++){
            int U=Integer.parseInt(br.readLine());
            sb.append(childCount[U]+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void bfs(int root){
        boolean[] visited=new boolean[N+1];
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            int current=q.poll();
            if(visited[current]){
                continue;
            }
            visited[current]=true;
            // 부모까지 갱신 필요
            calc(parent[current]);
            for(int next : graph.get(current)){
                if(!visited[next]){
                    parent[next]=current;
                    q.offer(next);
                }
            }
        }
    }
    static void calc(int before){
        if(before==0){
            return;
        }
        childCount[before]+=1;
        calc(find(before));
    }
    static int find(int x){
        return parent[x];
    }
}