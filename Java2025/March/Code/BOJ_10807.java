package Code;

import java.util.*;
import java.io.*;

class BOJ_10807{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10807;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());

        int[] countTable=new int[201];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            countTable[Integer.parseInt(st.nextToken())+100]+=1;
        }
        st=new StringTokenizer(br.readLine());
        int v=Integer.parseInt(st.nextToken());

        System.out.println(countTable[v+100]);
        br.close();
    }
}