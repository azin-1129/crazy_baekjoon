package Java_9월;

import java.io.*;
import java.util.*;

public class BOJ_2048 {
    static int maxValue=0;
    static int[] dx=new int[]{-1,1,0,0};
    static int[] dy=new int[]{0,0,-1,1};
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/java_9월/";
        BufferedReader br=new BufferedReader(new FileReader(path+"input2048.txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[][] board=new int[N][N];
        boolean[][] isMerged=new boolean[N][N];

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

        swipeBoardMax5Cnt(board, isMerged, 0, 0);

        System.out.println(maxValue);
    }

    static void swipeBoardMax5Cnt(int[][] board, boolean[][] isMerged, int cnt, int dir){
        if(cnt==5){
            reNewMaxValue(board);
            return;
        }

        System.out.println("dir 값입니다:"+dir);
        // dir에 따라 로직 분기해야함
        switch(dir){
            case 0:
                for(int y=0;y<board.length;y++){
                    int temp=0;
                    int tempIndex=0;
                    for(int x=0;x<board.length;x++){
                        if(board[x][y]==0){
                            if(x==board.length-1 & temp!=0){
                                board[tempIndex++][y]=temp;
                            }
                            continue;
                        }else if(board[x][y]==temp){ // 병합
                            board[tempIndex++][y]=temp*2;
                            board[x][y]=0;
                            temp=0;
                        }else{ // 병합하지 않음
                            if(temp==0){
                                temp=board[x][y];

                                if(x==board.length-1){
                                    board[tempIndex][y]=temp;
                                    board[x][y]=0;
                                }else{
                                    board[x][y]=0;
                                }
                            }else{ // temp가 0이 아니고 0이 아닌 board 내용물과 다름
                                board[tempIndex++][y]=temp;
                                temp=board[x][y];
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int y=0;y<board.length;y++){
                    int temp=0;
                    int tempIndex=3;
                    for(int x=board.length-1;x>=0;x--){
                        if(board[x][y]==0){
                            if(x==0 & temp!=0){
                                board[tempIndex--][y]=temp;
                            }
                            continue;
                        }else if(board[x][y]==temp){ // 병합
                            board[tempIndex--][y]=temp*2;
                            board[x][y]=0;
                            temp=0;
                        }else{ // 병합하지 않음
                            if(temp==0){
                                temp=board[x][y];

                                if(x==0){
                                    board[tempIndex][y]=temp;
                                    board[x][y]=0;
                                }else{
                                    board[x][y]=0;
                                }
                            }else{ // temp가 0이 아니고 0이 아닌 board 내용물과 다름
                                board[tempIndex--][y]=temp;
                                temp=board[x][y];

                                if(x==0){
                                    board[tempIndex][y]=temp;
                                    board[x][y]=0;
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int x=0;x<board.length;x++){
                    int temp=0;
                    int tempIndex=0;
                    for(int y=0;y<board.length;y++){
                        if(board[x][y]==0){
                            if(y==board.length-1 & temp!=0){
                                board[x][tempIndex++]=temp;
                            }
                            continue;
                        }else if(board[x][y]==temp){ // 병합
                            board[x][tempIndex++]=temp*2;
                            board[x][y]=0;
                            temp=0;
                        }else{ // 병합하지 않음
                            if(temp==0){
                                temp=board[x][y];

                                if(y==board.length-1){
                                    board[x][tempIndex]=temp;
                                    board[x][y]=0;
                                }else{
                                    board[x][y]=0;
                                }
                            }else{ // temp가 0이 아니고 board 내용물과 다름
                                board[x][tempIndex++]=temp;
                                temp=board[x][y];
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int x=0;x<board.length;x++){
                    int temp=0;
                    int tempIndex=3;
                    for(int y=board.length-1;y>=0;y--){
                        if(board[x][y]==0){
                            if(y==0 & temp!=0){
                                board[x][tempIndex--]=temp;
                            }
                            continue;
                        }else if(board[x][y]==temp){ // 병합
                            board[x][tempIndex--]=temp*2;
                            board[x][y]=0;
                            temp=0;
                        }else{ // 병합하지 않음
                            if(temp==0){
                                temp=board[x][y];
                                
                                if(y==0){
                                    board[x][tempIndex]=temp;
                                    board[x][y]=0;
                                }else{
                                    board[x][y]=0;
                                }
                            }else{ // temp가 0이 아니고 board 내용물과 다름
                                board[x][tempIndex--]=temp;
                                temp=board[x][y];
                            }
                        }
                    }
                }
                break;
        }

        System.out.println("쉬프트 테스트");

        for(int x=0;x<board.length;x++){
            System.out.println(Arrays.toString(board[x]));
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
