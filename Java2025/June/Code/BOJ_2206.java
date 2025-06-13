package Code;

import java.util.*;
import java.io.*;

class BOJ_2206{
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int depth;
        boolean crashed;

        public Node(int x, int y, int depth, boolean crashed){
            this.x=x;
            this.y=y;
            this.depth=depth;
            this.crashed=crashed;
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
    static int N, M;
    static int[][] map;
    static boolean[][] visitedNoCrash;
    static boolean[][] visitedCrash;
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

        boolean isWallExists=false;
        for(int x=0;x<N;x++){
            String[] inputs=br.readLine().split("");
            for(int y=0;y<M;y++){
                int current=Integer.parseInt(inputs[y]);

                if(current==1){
                    isWallExists=true;
                }
                map[x][y]=Integer.parseInt(inputs[y]);
            }
        }

        int result=Integer.MAX_VALUE;
        // 벽이 없다면, (N+M-1)을 반환한다.
        if(!isWallExists){
            result=N+(M-1);
        }else{
            visitedNoCrash=new boolean[N][M];
            visitedCrash=new boolean[N][M];
            PriorityQueue<Node> pq=new PriorityQueue<>();
            pq.offer(new Node(0, 0, 1, false));

            while(!pq.isEmpty()){
                // visitedInfo();
                Node current=pq.poll();
                int currentX=current.x;
                int currentY=current.y;
                int currentDepth=current.depth;
                boolean currentCrashed=current.crashed;

                if(currentX==(N-1) & currentY==(M-1)){
                    result=Math.min(result, currentDepth);
                    break;
                }

                if(currentCrashed){
                    if(visitedCrash[currentX][currentY]){
                        continue;
                    }

                    visitedCrash[currentX][currentY]=true;
                }else{
                    if(visitedNoCrash[currentX][currentY]){
                        continue;
                    }

                    visitedNoCrash[currentX][currentY]=true;
                }

                for(int d=0;d<4;d++){
                    int nextX=currentX+dx[d];
                    int nextY=currentY+dy[d];

                    if(nextX<0 || nextY<0 || nextX>=N || nextY>=M){
                        continue;
                    }

                    // 벽 한 번 부쉈던 애가 가봤을 때 방문 처리 되어서,
                    // 그 후 벽을 안 부쉈던 애가 가 보지도 못하는 듯
                    
                    // 그 외에는, 다음 좌표가 벽일 시 crack을 true로 바꾸고 벽으로 진행한다.
                    if(currentCrashed){ // 부딪힌 이력이 있다면 0으로만 진행 가능
                        if(map[nextX][nextY]!=1){
                            pq.offer(new Node(nextX, nextY, currentDepth+1, currentCrashed));
                        }
                    }else{ // 부딪힌 이력이 없다면
                        if(map[nextX][nextY]==1){ // 벽을 부수고 진행 가능
                            pq.offer(new Node(nextX, nextY, currentDepth+1, !currentCrashed));
                        }else{ // 0으로 진행
                            pq.offer(new Node(nextX, nextY, currentDepth+1, currentCrashed));
                        }
                    }
                }
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
    // static void visitedInfo(){
    //     System.out.println("방문 현황입니다.");
    //     for(int x=0;x<N;x++){
    //         System.out.println(Arrays.toString(visited[x]));
    //     }
    //     System.out.println();
    // }
}