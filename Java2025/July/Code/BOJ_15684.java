package Code;

import java.util.*;
import java.io.*;

class BOJ_15684{
    static boolean[][] result, origin;
    static int N, M, H;
    static int count=Integer.MAX_VALUE;
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15684;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());
        result=new boolean[N][M];
        origin=new boolean[N][M];
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            origin[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=true;
        }

        for(int cnt=1;cnt<=(M+H);cnt++){
            comb(0, 0, 0, 0, cnt);
        }

        System.out.println(count);
        br.close();
    }
    static void comb(int start, int depth, int lineNumber, int lineCount, int maxLineCount){
        if(lineCount>maxLineCount){
            System.out.println("가로선 너무 많이 고름");
            return;
        }
        
        if(lineNumber>=N){
            System.out.println("완성된 2D 배열입니다.");
            for(int x=0;x<N;x++){
                System.out.println(Arrays.toString(result[x]));
            }
            // 가로선이 연속되는 열이 있는지 확인
            if(isPossibleColumn()){
                System.out.println("조건을 만족한 2D 배열입니다.");
                for(int x=0;x<N;x++){
                    System.out.println(Arrays.toString(result[x]));
                }
                System.out.println();
                ladder(lineCount);
            }
            return;
        }

        if(depth>=M){ // 한 라인 끝남
            System.out.println("한 라인 끝");
            for(int x=0;x<N;x++){
                System.out.println(Arrays.toString(result[x]));
            }
            System.out.println();
            if(isPossibleLine(lineNumber)){ // 접하는지 확인
                comb(0, 0, lineNumber+1, lineCount, maxLineCount);
            }
            return;
        }
        
        for(int i=start;i<M;i++){
            result[lineNumber][depth]=true;
            comb(i+1, depth+1, lineNumber, lineCount+1, maxLineCount);
        }
    }
    static boolean isPossibleColumn(){
        for(int y=0;y<M;y++){
            int count=0;
            for(int x=0;x<N;x++){
                if(result[x][y]){
                    count+=1;
                }
            }
            if(count>H){
                return false;
            }
        }
        return true;
    }
    static void ladder(int lineCount){
        int[] ladderResult=new int[N];

        for(int y=0;y<M;y++){
            int position=y-1;
            for(int x=0;x<N;x++){
                if(!outRange(x, position)){
                    if(result[x][position]){
                        position-=1;
                    }
                }else if(!outRange(x, position+1)){
                    if(result[x][position+1]){
                        position+=1;
                    }
                }
            }
            ladderResult[position+1]+=1;
        }

        if(correctPath(ladderResult)){
            count=Math.min(count, lineCount);
        }
    }
    static boolean correctPath(int[] ladderResult){
        for(int y=0;y<M;y++){
            if(ladderResult[y]!=(y+1)){
                return false;
            }
        }
        return true;
    }
    static boolean isPossibleLine(int lineNumber){
        for(int y=0;y<M;y++){
            boolean origin=result[lineNumber][y];
            if(!origin){
                continue;
            }
            for(int d=0;d<4;d++){
                int nx=lineNumber+dx[d];
                int ny=y+dy[d];
                if(outRange(nx, ny)){
                    continue;
                }
                if(origin==result[nx][ny]){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean outRange(int x, int y){
        if(x<0 || y<0 || x>=N || y>=M){
            return true;
        }else{
            return false;
        }
    }
}