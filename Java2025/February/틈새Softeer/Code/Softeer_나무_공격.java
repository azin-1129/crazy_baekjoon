package Code;

import java.io.*;
import java.util.*;

public class Softeer_나무_공격 {
    static int N,M;
    static int[][] grid;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="나무_공격";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        grid=new int[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                grid[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<2;i++){
            st=new StringTokenizer(br.readLine());
            int L=Integer.parseInt(st.nextToken())-1;
            int R=Integer.parseInt(st.nextToken())-1;

            attack(L, R);
        }

        System.out.println(countSurvivor());

        br.close();
    }
    static void attack(int L, int R){
        for(int x=L;x<=R;x++){
            for(int y=0;y<M;y++){
                if(grid[x][y]==1){
                    grid[x][y]=0;
                    break;
                }
            }
        }
    }
    static int countSurvivor(){
        int count=0;
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(grid[x][y]==1){
                    count+=1;
                }
            }
        }

        return count;
    }
}
