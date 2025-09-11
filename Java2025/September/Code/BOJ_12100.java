package Code;

import java.util.*;
import java.io.*;

class BOJ_12100{
    static int[][] board;
    static int N;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=12100;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        // for문 밖에 idx(초기값)를 둔다.
        // 원본 배열 데이터가 0 이상이라면 idx에 둔다.
        // 만약 배열[idx]==원본 배열 데이터 라면,
        // 합산하고 idx를 1 증가시킨다.

        N=Integer.parseInt(br.readLine());
        board=new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        shift("up");

        for(int[] arr:board){
            System.out.println(Arrays.toString(arr));
        }
        br.close();
    }
    static void shift(String command){
        switch(command){
            case "left":
                // <- 쉬프트
                for(int x=0;x<N;x++){
                    int idx=0;
                    int temp=0;
                    for(int y=0;y<N;y++){
                        if(y==(N-1)){ // 대기해 봤자라면 그냥 put
                            System.out.println("put:"+idx);
                            board[x][idx]=temp;
                            break;
                        }
                        if(board[x][y]==0){
                            continue;
                        }else{
                            if(temp==0){
                                temp=board[x][y]; // 합산 대기 처리
                                board[x][y]=0;
                                if(y==(N-1)){ // 대기해 봤자라면 그냥 put
                                    System.out.println("put:"+idx);
                                    board[x][idx]=temp;
                                }
                            }else{
                                if(temp==board[x][y]){
                                    System.out.println("합산, y:"+y);
                                    board[x][idx]=temp*2;
                                    board[x][y]=0;
                                    idx+=1;
                                    temp=0;
                                }else{ // 0이 아닌 데이터이나, 합산 불가능 시 패스
                                    System.out.println("패스, y:"+y);
                                    board[x][idx]=temp;
                                    idx+=1;
                                    temp=board[x][y];
                                }
                            }
                        }
                    }
                }
                break;
            case "right":
                // -> 쉬프트
                for(int x=0;x<N;x++){
                    int idx=(N-1);
                    int temp=0;
                    for(int y=(N-1);y>=0;y--){
                        if(y==0){ // 대기해 봤자라면 그냥 put
                            if(temp!=0){ // temp가 아직 있다면 뱉어내야 함
                                board[x][idx--]=temp;
                            }
                            System.out.println("현재 값은"+board[x][y]);
                            board[x][idx]=board[x][y];
                            board[x][y]=0;
                            break;
                        }

                        if(board[x][y]==0){
                            continue;
                        }else{
                            if(temp==0){
                                temp=board[x][y]; // 합산 대기 처리
                                System.out.println("합산 대기중입니다:"+temp);
                                board[x][y]=0;
                                if(y==0){ // 대기해 봤자라면 그냥 put
                                    System.out.println("put:"+idx+", temp:"+temp);
                                    board[x][idx]=temp;
                                }
                            }else{
                                if(temp==board[x][y]){
                                    System.out.println("합산, y:"+y);
                                    board[x][idx]=temp*2;
                                    board[x][y]=0;
                                    idx-=1;
                                    temp=0;
                                }else{ // 0이 아닌 데이터이나, 합산 불가능 시 패스
                                    System.out.println("패스, y:"+y);
                                    board[x][idx]=temp;
                                    idx-=1;
                                    temp=board[x][y];
                                }
                            }
                        }
                    }
                }
                break;
            case "up":
                // ^ 쉬프트
                for(int y=0;y<N;y++){
                    int idx=0;
                    int temp=0;
                    for(int x=0;x<N;x++){
                        if(x==(N-1)){ // 대기해 봤자라면 그냥 put
                            if(temp!=0){ // temp가 아직 있다면 뱉어내야 함
                                board[idx++][y]=temp;
                            }
                            System.out.println("현재 값은"+board[x][y]);
                            if(idx!=(N-1)){
                                board[idx][y]=board[x][y];
                                board[x][y]=0;
                            }
                            break;
                        }

                        if(board[x][y]==0){
                            continue;
                        }else{
                            if(temp==0){
                                temp=board[x][y]; // 합산 대기 처리
                                System.out.println("합산 대기중입니다:"+temp);
                                board[x][y]=0;
                                if(x==(N-1)){ // 대기해 봤자라면 그냥 put
                                    System.out.println("put:"+idx+", temp:"+temp);
                                    board[idx][y]=temp;
                                }
                            }else{
                                if(temp==board[x][y]){
                                    System.out.println("합산, y:"+y);
                                    board[idx][y]=temp*2;
                                    board[x][y]=0;
                                    idx+=1;
                                    temp=0;
                                }else{ // 0이 아닌 데이터이나, 합산 불가능 시 패스
                                    System.out.println("패스, y:"+y);
                                    board[idx][y]=temp;
                                    idx+=1;
                                    temp=board[x][y];
                                }
                            }
                        }
                    }
                }
                break;
            case "down":
                break;
        }
    }
}