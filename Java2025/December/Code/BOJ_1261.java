package Code;

import java.util.*;
import java.io.*;

class BOJ_1261 {
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int breakCount;

        Node(int x, int y, int breakCount){
            this.x=x;
            this.y=y;
            this.breakCount=breakCount;
        }

        @Override
        public int compareTo(Node n){
            return Integer.compare(this.breakCount, n.breakCount);
        }

        @Override
        public String toString(){
            return "[ ("+x+","+y+") :"+breakCount+" ]";
        }
    }
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1261;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        int[] dx={-1, 1, 0, 0};
        int[] dy={0, 0, -1, 1};

        // N*M 크기 미로
        // 빈 방 or 벽
        // 운영진은 모두 같은 방에 있음.
        // 이동 반경은 상하좌우 빈 방

        // 1, 1에 있는 운영진이 N, M으로 이동하기 위해
        // 부수어야 하는 벽의 최소 개수는?

        // 배열의 값이 좌표 x, y까지 도달하기 위해 부순 최소 벽의 개수인 듯

        st=new StringTokenizer(br.readLine(), " ");
        int M=Integer.parseInt(st.nextToken());
        int N=Integer.parseInt(st.nextToken());

        // 미로 정보, 최단 테이블 정보 초기화
        int[][] maze=new int[N][M];
        int[][] shortestCount=new int[N][M];
        for(int x=0;x<N;x++){
            String[] input=br.readLine().split("");
            for(int y=0;y<M;y++){
                // 0: 빈 방, 1: 벽
                maze[x][y]=Integer.parseInt(input[y]);
            }
            Arrays.fill(shortestCount[x], Integer.MAX_VALUE);
        }

        // 다익스트라
        PriorityQueue<Node> pq=new PriorityQueue<>();
        // 1, 1부터 시작
        pq.offer(new Node(0, 0, 0));
        while(!pq.isEmpty()){
            Node node=pq.poll();
            // System.out.println(node);
            int x=node.x;
            int y=node.y;
            int breakCount=node.breakCount;
            if(shortestCount[x][y]<=breakCount){
                continue;
            }

            shortestCount[x][y]=breakCount;
            for(int i=0;i<4;i++){
                int nx=x+dx[i];
                int ny=y+dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=M){
                    continue;
                }

                if(maze[nx][ny]==1){ // 가려고 하는 곳이 벽이다.
                    pq.offer(new Node(nx, ny, breakCount+1));
                }else{
                    pq.offer(new Node(nx, ny, breakCount));
                }
            }
        }

        System.out.println(shortestCount[N-1][M-1]);
        br.close();
    }
}