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
    static Queue<Node> Islandq=new PriorityQueue<>(); // 현재 섬 좌표들 저장
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
                    // System.out.println("탐색이 완료되었습니다.");
                    // System.out.println();
                }
            }
        }

        System.out.println(result-1);
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
                continue;
            }

            visitedIsland[currX][currY]=true;
            Islandq.offer(new Node(currX, currY));

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
        // PriorityQueue<Node> pq=new PriorityQueue<>();
        count+=1;
        // System.out.println(count+"번째 섬의 좌표 목록입니다.");
        // System.out.println(Islandq);

        while(!Islandq.isEmpty()){
            // 시작하는 섬의 좌표들을 하나하나 DFS하는 로직
            Node curr=Islandq.poll();
            bfs(curr.x, curr.y);
        }
    }
        static void bfs(int startX, int startY){
        // 아 bfs 하고잇었네
        PriorityQueue<Node> pq=new PriorityQueue<>();
        // System.out.println("탐색 연산을 시작합니다.");
        // System.out.println("탐색 시작 위치는 "+startX+", "+startY+" 입니다.");
        // System.out.println(pq);

        boolean[][] visited=new boolean[N][N];
        pq.offer(new Node(startX, startY, 0));
        while(!pq.isEmpty()){
            // 시작하는 섬의 좌표들로부터 다른 섬까지 다리를 놓아 보는 로직
            Node curr=pq.poll();
            int currX=curr.x;
            int currY=curr.y;
            int currDepth=curr.depth;

            // 섬의 다른 좌표가 가본 곳도 가봐야 함.. 섬 좌표 제외 ㅠㅠ

            // 해당 좌표에서 출발한다.
            // visited[x][y]=true면 이미 가본 곳. continue
            // map[x][y]=1일 때, visitedIsland[x][y]=true면 동지이므로 result 갱신 X
            // visited[x][y]= true
            if(visited[currX][currY]){ // 이미 탐색해 본 좌표이다.
                continue;
            }

            if(visitedIsland[currX][currY]==false & map[currX][currY]==1){ // 다른 섬의 육지라면 최단거리 갱신
                result=Math.min(result, currDepth);
                // System.out.println("여기라고 판단했어요:"+currX+", "+currY);
                // System.out.println("depth는 "+currDepth+"입니다!");
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