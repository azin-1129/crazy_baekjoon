package Code;

import java.util.*;
import java.io.*;

class BOJ_13418 {
    static class Node{
        int next;
        boolean isLow;

        Node(int next, int isLow){
            this.next=next;
            if(isLow==0){
                this.isLow=false;
            }else{
                this.isLow=true;
            }
        }

        @Override
        public String toString(){
            return "[next:"+this.next+", isLow="+this.isLow+"]";
        }
    }
    static List<List<Node>> graph=new ArrayList<>();
    static boolean[] visited;
    static int lowWeight, highWeight, N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=13418;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken()); // 건물의 수
        visited=new boolean[N+1];
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        int M=Integer.parseInt(st.nextToken()); // 도로의 수
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int isLow=Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, isLow));
            graph.get(to).add(new Node(from, isLow));
        }

        // 시작: 항상 0
        dfs(0, 0, 0);
        // 각 MST 완성 후, 총 오르막길 개수**2가 피로도
        // 답 : 제일 피곤한 경로의 피로도-제일 덜 피곤한 경로의 피로도
        System.out.println(highWeight-lowWeight);
        br.close();
    }
    static void dfs(int node, int edgeCount, int upperCount){
        if(visited[node]){
            return;
        }
        System.out.println(node+" 건물 방문중. 거쳐온 길:"+edgeCount+", 오르막 수"+upperCount);

        if(edgeCount==N){ // MST 완성
            int weight=(int)Math.pow(upperCount, 2);
            if(lowWeight>weight){
                lowWeight=weight;
            }else if(highWeight<weight){
                highWeight=weight;
            }
            return;
        }

        for(Node nextPath : graph.get(node)){
            visited[node]=true;
            dfs(nextPath.next, edgeCount+1, nextPath.isLow ? upperCount+1 : upperCount);
            visited[node]=false;
        }
    }
}