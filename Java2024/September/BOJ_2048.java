package Java_9월;

import java.io.*;
import java.util.*;

public class BOJ_2048 {
    static int maxValue, N=0;
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/java_9월/";
        BufferedReader br=new BufferedReader(new FileReader(path+"input2048.txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        int[][] board=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                try{
                    board[i][j]=Integer.parseInt(st.nextToken());
                }catch(NoSuchElementException e){
                    board[i][j]=0;
                }
            }
        }

        for(int dir=0;dir<4;dir++){
            swipeBoardMax5Cnt(board, 1, dir);
        }

        System.out.println(maxValue);
    }

    static void swipeBoardMax5Cnt(int[][] board, int cnt, int dir){
        int[][] nextBoard=new int[N][N];

        System.out.println("dir 값입니다:"+dir);
        // dir에 따라 로직 분기해야함
        switch(dir){ // 상하좌우
            case 0:
                for(int y=0;y<N;y++){
                    int block=0;
                    int blockIdx=0;
                    for(int x=0;x<N;x++){
                        if(board[x][y]==0){
                            continue;
                        }

                        if(board[x][y]==block){
                            nextBoard[x][blockIdx++]=block*2;
                            block=0;
                        }else{
                            if(block==0){
                                block=board[x][y];
                            }
                            else{
                                nextBoard[x][blockIdx++]=block;
                                block=board[x][y];
                            }
                        }
                    }
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

        System.out.println("쉬프트 테스트");

        for(int x=0;x<board.length;x++){
            System.out.println(Arrays.toString(board[x]));
        }

        if(cnt==5){
            reNewMaxValue(board);
            return;
        }else{
            swipeBoardMax5Cnt(board, cnt+1, dir);
        }
    }

    static void reNewMaxValue(int[][] board){
        int maxValueTemp=0;

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                maxValueTemp=Math.max(maxValueTemp, board[i][j]);
            }  
        }

        if(maxValue<maxValueTemp){
            maxValue=maxValueTemp;
        }
    }
}
