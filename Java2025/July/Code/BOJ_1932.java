package Code;

import java.util.*;
import java.io.*;

class BOJ_1932{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1932;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[][] origin=new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int idx=0;
            while(st.hasMoreTokens()){
                origin[i][idx++]=Integer.parseInt(st.nextToken());
            }
        }

        for(int x=(N-2);x>=0;x--){
            for(int y=x;y>=0;y--){
                origin[x][y]=Math.max(origin[x+1][y+1], origin[x+1][y])+origin[x][y];
            }
        }

        System.out.println(origin[0][0]);
        br.close();
    }
}