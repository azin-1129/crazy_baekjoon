package Code;

import java.util.*;
import java.io.*;

class BOJ_10798{
    static int ROW_SIZE=5;
    static int MAX_COL_SIZE=15;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10798;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        String[][] str=new String[ROW_SIZE][MAX_COL_SIZE];
        for(int i=0;i<ROW_SIZE;i++){
            String[] input=br.readLine().split("");
            for(int j=0;j<input.length;j++){
                str[i][j]=input[j];
            }
        }

        StringBuilder sb=new StringBuilder();
        for(int y=0;y<MAX_COL_SIZE;y++){
            for(int x=0;x<ROW_SIZE;x++){
                if(str[x][y]!=null){
                    sb.append(str[x][y]);
                }
            }
        }
        System.out.println(sb);

        br.close();
    }
}