package Code;

import java.util.*;
import java.io.*;

class Node2660{
    int x;
    int depth;

    public Node2660(int x, int depth){
        this.x=x;
        this.depth=depth;
    }
}
class BOJ_2660{
    static int N;
    static int[] visitedCount;
    static boolean[][] visited;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2660;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        visitedCount=new int[N+1];
        visited=new boolean[N+1][N+1];

        graph=new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<Integer>());
        }

        Set<Integer> startNodes=new HashSet<>();
        while(true){
            st=new StringTokenizer(br.readLine()," ");

            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            if(x==-1 && y==-1){
                break;
            }

            startNodes.add(x);
            startNodes.add(y);

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for(int start:startNodes){
            bfs(start);
        }
        
        int minimum=Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            minimum=Math.min(visitedCount[i], minimum);
        }

        int minimumCount=0;
        List<Integer> leader=new ArrayList<>();
        for(int i=1;i<=N;i++){
            if(visitedCount[i]==minimum){
                leader.add(i);
                minimumCount++;
            }
        }

        Collections.sort(leader);

        System.out.println(minimum+" "+minimumCount);
        System.out.println(leader.toString().replaceAll("[\\[\\],]",""));

        br.close();
    }
    public static void bfs(int start){
        // System.out.println("BFS "+start+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        Queue<Node2660> q=new ArrayDeque<>();

        q.offer(new Node2660(start, 0));
        visited[start][start]=true;

        while(!q.isEmpty()){
            Node2660 current=q.poll();
            // System.out.println("***********방문 노드:"+current.x+", depth:"+current.depth);

            int currentDepth=current.depth;

            for(int next:graph.get(current.x)){
                if(!visited[next][start]){
                    visited[next][start]=true;
                    q.offer(new Node2660(next, Integer.valueOf(currentDepth)+1));
                    // System.out.print("추가한 다음 목적지:"+next);
                }
            }

            visitedCount[start]=Math.max(visitedCount[start], currentDepth);
            // System.out.println();
        }
    }
}