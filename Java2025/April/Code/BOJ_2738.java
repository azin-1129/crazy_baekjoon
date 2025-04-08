package Code;

import java.util.*;
import java.io.*;

class BOJ_2738{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2738;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int[][] arrA=new int[N][M];
        for(int x=0;x<N;x++){
            st=new StringTokenizer(br.readLine()," ");
            for(int y=0;y<M;y++){
                arrA[x][y]=Integer.parseInt(st.nextToken());
            }
        }

        int[][] arrB=new int[N][M];
        for(int x=0;x<N;x++){
            st=new StringTokenizer(br.readLine()," ");
            for(int y=0;y<M;y++){
                arrB[x][y]=Integer.parseInt(st.nextToken());
            }
        }

        int[][] result=new int[N][M];
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                result[x][y]=arrA[x][y]+arrB[x][y];
            }
        }
        for(int x=0;x<N;x++){
            System.out.println(Arrays.toString(result[x]).replaceAll("[\\[\\],]",""));
        }
        br.close();
    }
}