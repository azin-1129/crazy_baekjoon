package Code;

import java.util.*;
import java.io.*;

class BOJ_2146{
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int depth;

        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
        public Node(int x, int y, int depth){
            this.x=x;
            this.y=y;
            this.depth=depth;
        }

        @Override
        public int compareTo(Node n){
            return Integer.compare(this.depth, n.depth);
        }

        @Override
        public String toString(){
            return "[Node: ("+x+", "+y+"), depth: "+depth+"]";
        }
    }

    static boolean[][] visitedIsland;
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    static int[][] map;
    static int N;
    static int result=Integer.MAX_VALUE;
    static Queue<Node> edgeq=new PriorityQueue<>(); // 외곽 좌표 저장
    static int count;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2146;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        visitedIsland=new boolean[N][N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(map[x][y]==1 & visitedIsland[x][y]==false){
                    marking(x, y); // visitedIsland 마킹
                    search(); // 최단 다리 검색
                }
            }
        }

        System.out.println(result);
        br.close();
    }
    static void marking(int startX, int startY){
        Queue<Node> q=new ArrayDeque<>();
        Node start=new Node(startX, startY);
        q.offer(start);
        while(!q.isEmpty()){
            Node curr=q.poll();
            int currX=curr.x;
            int currY=curr.y;

            if(visitedIsland[currX][currY]){
                continue;
            }
            if(map[currX][currY]==0){
                edgeq.offer(new Node(currX, currY, 0));
                continue;
            }

            visitedIsland[currX][currY]=true;

            for(int d=0;d<4;d++){
                int nx=currX+dx[d];
                int ny=currY+dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=N){
                    continue;
                }
                q.offer(new Node(nx, ny));
            }
        }
    }
    static void search(){
        while(!edgeq.isEmpty()){
            // 외곽부터 BFS하는 로직
            Node curr=edgeq.poll();
            bfs(curr.x, curr.y);
        }
    }
        static void bfs(int startX, int startY){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        boolean[][] visited=new boolean[N][N];
        pq.offer(new Node(startX, startY, 0));
        while(!pq.isEmpty()){
            // 시작하는 섬의 좌표들로부터 다른 섬까지 다리를 놓아 보는 로직
            Node curr=pq.poll();
            int currX=curr.x;
            int currY=curr.y;
            int currDepth=curr.depth;

            if(visited[currX][currY]){ // 이미 탐색해 본 좌표이다.
                continue;
            }

            if(visitedIsland[currX][currY]==false & map[currX][currY]==1){ // 다른 섬의 육지라면 최단거리 갱신
                result=Math.min(result, currDepth);
            }

            visited[currX][currY]=true; // 방문 처리

            for(int d=0;d<4;d++){
                int nx=currX+dx[d];
                int ny=currY+dy[d];
                
                if(nx<0 || ny<0 || nx>=N || ny>=N){
                    continue;
                }
                pq.offer(new Node(nx, ny, currDepth+1));
            }
        }
    }
}