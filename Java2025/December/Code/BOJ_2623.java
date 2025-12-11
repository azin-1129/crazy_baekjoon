package Code;

import java.util.*;
import java.io.*;

class BOJ_2623 {
    static StringBuilder sb=new StringBuilder();
    static List<List<Integer>> graph=new ArrayList<>();
    static int[] inDegree;
    static int N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 2623;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken()); // 가수 수
        int M=Integer.parseInt(st.nextToken()); // PD 수
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        inDegree=new int[N+1];

        // graph, inDegree 정보 등록
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int m=Integer.parseInt(st.nextToken());
            int before=-1;
            int now=-1;
            for(int j=0;j<m;j++){
                if(j==0){
                    before=Integer.parseInt(st.nextToken());
                    continue;
                }
                now=Integer.parseInt(st.nextToken());
                graph.get(before).add(now);
                inDegree[now]+=1;
                before=now;
            }
        }

        if(isCycle()){
            sb.append('0'+"\n");
        }else{
            topologySort();
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static boolean isCycle(){
        Stack<Integer> stack=new Stack<>();
        boolean[] visited;
        for(int i=1;i<=N;i++){
            visited=new boolean[N+1];
            stack.push(i);
            while(!stack.isEmpty()){
                int current=stack.pop();
                if(visited[current]){
                    continue;
                }
                visited[current]=true;
                for(int next : graph.get(current)){
                    if(visited[next]){
                        if(next==i){
                            return true;
                        }else{
                            continue;
                        }
                    }
                    stack.push(next);
                }
            }
        }
        return false;
    }
    static void topologySort(){
        Queue<Integer> q=new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            if(inDegree[i]==0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int current=q.poll();
            sb.append(current+"\n");
            for(int next : graph.get(current)){
                inDegree[next]-=1;
                if(inDegree[next]==0){
                    q.offer(next);
                }
            }
        }
    }
}