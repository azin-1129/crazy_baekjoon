package Code;

import java.util.*;
import java.io.*;

class BOJ_2573{
    static class Ice{
        int x;
        int y;

        public Ice(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString(){
            return "[Ice: ("+x+","+y+")]";
        }
    }

    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    static boolean[][] visited;
    static int[][] map;
    static int result=Integer.MAX_VALUE;
    static int N,M;
    static int dfsResult;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2573;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];

        for(int x=0;x<N;x++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int y=0;y<M;y++){
                map[x][y]=Integer.parseInt(st.nextToken());
            }
        }
        int year=0;
        while(true){
            year+=1;
            if(melt()==0){
                year=0;
                break;
            }

            if(countIceChunks()>=2){
                break;
            }
        }

        System.out.println(year);
        br.close();
    }
    static int melt(){
        visited=new boolean[N][M];
        // 1. BFS로 빙산 각각 녹이기
        int iceCount=0;
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(map[x][y]>0 & visited[x][y]==false){
                    visited[x][y]=true;
                    iceCount+=1;
                    for(int d=0;d<4;d++){
                        int nx=x+dx[d];
                        int ny=y+dy[d];

                        if(nx<0 || ny<0 || nx>=N || ny>=M){
                            continue;
                        }

                        if(map[nx][ny]>0){
                            continue;
                        }

                        if(visited[nx][ny]){
                            continue;
                        }

                        if((map[x][y]-1)<0){
                            iceCount-=1;
                            break;
                        }else{
                            map[x][y]-=1;
                        }
                    }
                }
            }
        }

        System.out.println("빙산이 녹았습니다.");
        for(int x=0;x<N;x++){
            System.out.println(Arrays.toString(map[x]));
        }
        return iceCount;
    }
    static int countIceChunks(){
        // 2. BFS로 빙산 덩어리 수 세기
        int iceChunkCount=0;
        visited=new boolean[N][M];
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(map[x][y]>0 & visited[x][y]==false){
                    bfs(x, y);
                    iceChunkCount+=1;
                }
            }
        }
        return iceChunkCount;
    }
    static void bfs(int x, int y){
        Queue<Ice> q=new ArrayDeque<>();
        q.offer(new Ice(x, y));

        while(!q.isEmpty()){
            Ice current=q.poll();
            int currentX=current.x;
            int currentY=current.y;

            if(visited[currentX][currentY]){
                continue;
            }

            visited[currentX][currentY]=true;

            for(int d=0;d<4;d++){
                int nx=currentX+dx[d];
                int ny=currentY+dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=M){
                    continue;
                }

                if(map[nx][ny]>0){
                    q.offer(new Ice(nx, ny));
                }
            }
        }
    }
}