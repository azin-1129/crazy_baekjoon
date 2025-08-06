package Code;

import java.util.*;
import java.io.*;

class BOJ_14501{
    static int result=0;
    static int N;
    static int[][] reserv;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14501;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        N=Integer.parseInt(br.readLine());
        reserv=new int[N][2];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            reserv[i][0]=Integer.parseInt(st.nextToken());
            reserv[i][1]=Integer.parseInt(st.nextToken());
        }

        scoring(0, 0);
        System.out.println(result);
        br.close();
    }
    static void scoring(int idx, int score){
        if(idx>N){
            result=Math.max(result, score-reserv[idx][1]);
            return;
        }

        for(int i=idx;i<N;i++){
            scoring(idx+reserv[i][0], score+reserv[i][1]);
        }
    }
}