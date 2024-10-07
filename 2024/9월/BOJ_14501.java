package Java_9월;

import java.io.*;
import java.util.*;

class BOJ_14501{
    static int[][] scheduleInfo;
    static int N, payMaximum;
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/Java_9월/";
        
        BufferedReader br=new BufferedReader(new FileReader(path+"input14501.txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        scheduleInfo=new int[N][2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            scheduleInfo[i][0]=Integer.parseInt(st.nextToken()); // 일 수
            scheduleInfo[i][1]=Integer.parseInt(st.nextToken()); // 페이
        }

        for(int i=0;i<N;i++){
            System.out.println((i+1)+"번째 일부터 시작합니다.");

            if(i+scheduleInfo[i][0]>=N){
                System.out.println("일수가 오버되어 solution을 실행하지 않습니다.");
            }else{
                solution(scheduleInfo[i][0], scheduleInfo[i][1], N-i);
            }

            System.out.println("ㅡㅡㅡㅡㅡㅡcycle이 끝났습니다.ㅡㅡㅡㅡㅡㅡ");
        }

        System.out.println(payMaximum);
    }

    public static void solution(int day, int pay, int remainingDay){
        System.out.println("진행하려는 일의 info. 총 일한 날짜:"+day+", 페이:"+pay);
        if(day>=N || day+scheduleInfo[day][0]>remainingDay){ // 다음날 진행하기 전에 체크
            payMaximum=Math.max(payMaximum, pay);

            return;
        }

        solution(day+scheduleInfo[day][0], pay+scheduleInfo[day][1], remainingDay);

        // 근데 이렇게 구현하면 다른 경우를 못잡을 거같음 ㅠㅠㅠㅠ
        // 1 2 3 4 5의 선택지가 있다고 가정했을 때 1 4 5가 답인데 1 2 3만 하고 끝난다던지
    }
}