package Code;

import java.util.*;
import java.io.*;

class BOJ_2003{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2003;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken())+1;
        int M=Integer.parseInt(st.nextToken());
        int[] numbers=new int[N];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N-1;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }

        int sum=0;
        int start=0;
        int end=0;
        int count=0;
        while(start<=end && end<N){
            if(sum<M){
                sum+=numbers[end];
                end++;
            }else{
                if(sum==M){
                    count++;
                }
                sum-=numbers[start];
                start++;
            }
        }
        
        System.out.println(count);
        br.close();
    }
}