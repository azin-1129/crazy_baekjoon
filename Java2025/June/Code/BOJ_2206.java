package Code;

import java.util.*;
import java.io.*;

class BOJ_2206{
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int depth;

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
            return "Node info: ("+x+","+y+"), depth: "+depth;
        }
    }
    static class Wall{
        int x;
        int y;
  
        public Wall(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString(){
            return "Wall info: ("+x+","+y+")";
        }
    }
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2206;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        int[] dx={-1, 1, 0, 0};
        int[] dy={0, 0, -1, 1};
        
        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        Queue<Wall> wq=new ArrayDeque<>();
        for(int x=0;x<N;x++){
            String[] inputs=br.readLine().split("");
            for(int y=0;y<M;y++){
                int current=Integer.parseInt(inputs[y]);

                if(current==1){
                    wq.offer(new Wall(x, y)); // info 중, 벽 좌표를 파악한다.
                }
                map[x][y]=Integer.parseInt(inputs[y]);
            }
        }

        // System.out.println("벽 정보를 확보했습니다:"+wq);

        int result=Integer.MAX_VALUE;
        // 벽이 없다면, (N+M-1)을 반환한다.
        if(wq.isEmpty()){
            System.out.println(N+(M-1));
        }else{
            while(!wq.isEmpty()){
                boolean[][] visited=new boolean[N][M];

                Wall wall=wq.poll();
                int wallX=wall.x;
                int wallY=wall.y;
                // 벽을 1칸씩 없앤다.
                map[wallX][wallY]=0;

                // printing("벽을 파괴했습니다.");
                // BFS를 진행(목적지 도달 시 최단 거리 값 갱신 후 break)
                PriorityQueue<Node> pq=new PriorityQueue<>();
                // 시작 위치는 0, 0
                pq.offer(new Node(0, 0, 1));
                while(!pq.isEmpty()){
                    Node current=pq.poll();
                    int currentX=current.x;
                    int currentY=current.y;
                    int currentDepth=current.depth;

                    // 만약 도착? (N-1, M-1): 최단거리 갱신 후 break
                    if(currentX==(N-1) & currentY==(M-1)){
                        result=Math.min(result, currentDepth);
                        break;
                    }
                    // 방문? continue
                    if(visited[currentX][currentY]){
                        continue;
                    }
                    // 방문 처리
                    visited[currentX][currentY]=true;

                    // 4방향 탐색 후 offer
                    for(int d=0;d<4;d++){
                        int nextX=currentX+dx[d];
                        int nextY=currentY+dy[d];

                        // 범위 밖? continue
                        if(nextX<0 || nextY<0 || nextX>=N || nextY>=M){
                            continue;
                        }
                        
                        // 벽이면 진행 못함
                        if(map[nextX][nextY]==1){
                            continue;
                        }

                        pq.offer(new Node(nextX, nextY, currentDepth+1));
                    }
                }
                // 다음 BFS를 시작하기 전에, 벽 정보를 복구한다.
                map[wallX][wallY]=1;
            }
        }

        if(result==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
        br.close();
    }
    static void printing(String str){
        System.out.println(str);
        for(int x=0;x<N;x++){
            System.out.println(Arrays.toString(map[x]));
        }
        System.out.println();
    }
}