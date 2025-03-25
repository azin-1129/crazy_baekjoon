package Code;

import java.util.*;
import java.io.*;

class BOJ_5597{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=5597;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        boolean[] checked=new boolean[31];
        for(int i=0;i<28;i++){
            st=new StringTokenizer(br.readLine());
            int check=Integer.parseInt(st.nextToken());
            checked[check]=true;
        }

        for(int i=1;i<=30;i++){
            if(checked[i]==false){
                System.out.println(i);
            }
        }
        
        br.close();
    }
}