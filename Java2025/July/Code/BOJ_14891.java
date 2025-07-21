package Code;

import java.util.*;
import java.io.*;

class BOJ_14891{
    static int WHEEL_COUNT=4;
    static int TOOTH_COUNT=8;
    static int[][] wheelStat=new int[WHEEL_COUNT][TOOTH_COUNT];
    static int[][] toothIndex=new int[WHEEL_COUNT][TOOTH_COUNT];
    static int[] reservation;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14891;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        int result=0;
        
        for(int i=0;i<WHEEL_COUNT;i++){
            String[] input=br.readLine().split("");
            for(int j=0;j<TOOTH_COUNT;j++){
                wheelStat[i][j]=Integer.parseInt(input[j]);
                toothIndex[i][j]=j;
            }
        }

        StringTokenizer st;
        int K=Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            reservation=new int[WHEEL_COUNT];

            st=new StringTokenizer(br.readLine(), " ");
            int rotateWheelNum=Integer.parseInt(st.nextToken())-1;
            int rotateDirection=Integer.parseInt(st.nextToken());
            reservation[rotateWheelNum]=rotateDirection;
            // 회전 방향 예약
            cascade(rotateWheelNum, rotateDirection, -1);
            cascade(rotateWheelNum, rotateDirection, 1);

            // System.out.println("회전 예약이 완료되었습니다.");
            // System.out.println(Arrays.toString(reservation));

            // System.out.println("회전하기 전 상태입니다.");
            // for(int x=0;x<4;x++){
            //     System.out.println(Arrays.toString(toothIndex[x]));
            // }
            // 예약대로 회전
            rotate();
            // System.out.println("회전을 완료했습니다.");
            // for(int x=0;x<4;x++){
            //     System.out.println(Arrays.toString(toothIndex[x]));
            // }
            // System.out.println();
        }

        int score=1;
        for(int i=0;i<WHEEL_COUNT;i++){
            if(wheelStat[i][toothIndex[i][0]]==1){
                result+=score;
            }
            score*=2;
        }

        System.out.println(result);
        br.close();
    }
    static void cascade(int wheelNum, int rotateDir, int position){
        if(position==-1){ // 좌 연쇄
            int leftWheelNum=wheelNum-1;
            if(leftWheelNum<0){
                return;
            }
            if(wheelStat[wheelNum][toothIndex[wheelNum][6]]!=wheelStat[leftWheelNum][toothIndex[leftWheelNum][2]]){
                if(rotateDir==1){
                    reservation[leftWheelNum]=-1;
                }else{
                    reservation[leftWheelNum]=1;
                }
                cascade(leftWheelNum, reservation[leftWheelNum], -1);
            }
        }else{ // 우 연쇄
            int rightWheelNum=wheelNum+1;
            if(rightWheelNum>=WHEEL_COUNT){
                return;
            }
            if(wheelStat[wheelNum][toothIndex[wheelNum][2]]!=wheelStat[rightWheelNum][toothIndex[rightWheelNum][6]]){
                if(rotateDir==1){
                    reservation[rightWheelNum]=-1;
                }else{
                    reservation[rightWheelNum]=1;
                }
                cascade(rightWheelNum, reservation[rightWheelNum], 1);
            }
        }
    }
    static void rotate(){
        for(int i=0;i<WHEEL_COUNT;i++){
            // i번째 톱니를 reservation[i] 방향으로 회전
            int value;
            if(reservation[i]==1){ // 시계 방향
                value=-1;
            }else if(reservation[i]==-1){ // 반시계 방향
                value=1;
            }else{
                continue;
            }
            for(int j=0;j<TOOTH_COUNT;j++){
                int temp=toothIndex[i][j]+value;
                if(temp>=8){
                    toothIndex[i][j]=0;
                }else if(temp<0){
                    toothIndex[i][j]=TOOTH_COUNT-1;
                }else{
                    toothIndex[i][j]=temp;
                }
            }
        }
    }
}