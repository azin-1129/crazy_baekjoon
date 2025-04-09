package Code;

import java.util.*;
import java.io.*;

class BOJ_2563{
    static int BOARD_SIZE=100;
    static int PAPER_SIZE=10;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2563;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        boolean[][] board=new boolean[BOARD_SIZE][BOARD_SIZE];

        int result=0;
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            for(int j=x;j<x+PAPER_SIZE;j++){
                for(int k=y;k<y+PAPER_SIZE;k++){
                    if(!board[j][k]){
                        board[j][k]=true;
                        result+=1;
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}