package Code;

import java.util.*;
import java.io.*;

class BOJ_10813{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10813;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[] basket=new int[N+1];
        for(int i=1;i<=N;i++){
            basket[i]=i;
        }
        
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int I=Integer.parseInt(st.nextToken());
            int J=Integer.parseInt(st.nextToken());
            int temp=0;

            temp=basket[J];
            basket[J]=basket[I];
            basket[I]=temp;
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=N;i++){
            sb.append(basket[i]+" ");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}