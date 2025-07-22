package Code;

import java.util.*;
import java.io.*;

class BOJ_14503{
    static int N, M, result;
    static int[][] room;
    static int[] dx={-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy={0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14503;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine(), " ");
        int startX=Integer.parseInt(st.nextToken());
        int startY=Integer.parseInt(st.nextToken());
        int startDir=Integer.parseInt(st.nextToken());

        room=new int[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++){
                room[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // print2DArr("청소 전");
        clean(startX, startY, startDir);
        // print2DArr("청소 후");
        System.out.println(result);
        br.close();
    }
    static void clean(int x, int y, int dir){
        if(room[x][y]==0){
            room[x][y]=2;
            result+=1;
        }

        int count=1;
        for(;count<5;count++){
            int nextDir=(dir-count)<0? 4-Math.abs((dir-count)) : (dir-count);
            int nextX=x+dx[nextDir];
            int nextY=y+dy[nextDir];

            if(nextX<0 || nextY<0 || nextX>=N || nextY>=M){
                continue;
            }

            if(room[nextX][nextY]==0){ // 90도 회전 필요
                clean(nextX, nextY, nextDir);
                break;
            }
        }

        if(count==5){
            int nextX=x-dx[dir];
            int nextY=y-dy[dir];
            if(nextX>=0 & nextY>=0 & nextX<N & nextY<M){
                if(room[nextX][nextY]!=1){
                    clean(nextX, nextY, dir);
                }
            }
        }
    }
    static void print2DArr(String str){
        System.out.println(str+"room 상태입니다.");
        for(int x=0;x<N;x++){
            System.out.println(Arrays.toString(room[x]));
        }
    }
}