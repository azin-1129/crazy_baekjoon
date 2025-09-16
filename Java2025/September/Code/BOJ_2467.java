package Code;

import java.util.*;
import java.io.*;

class BOJ_2467{
    static long[] liquids;
    static int N;
    static long rL, rR;
    static long result=Long.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2467;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        liquids=new long[N];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            liquids[i]=Long.parseLong(st.nextToken());
        }

        for(int i=0;i<N;i++){
            binarySearch(i);
        }

        System.out.println(rL+" "+rR);
        br.close();
    }
    static void binarySearch(int l){
        int left=l+1;
        int right=N-1;
        System.out.println("@@@ START. left:"+left+", right:"+right);

        while(left<=right){
            int middle=(left+right)/2;
            System.out.println("middle?:"+middle);

            long sum=liquids[l]+liquids[middle];
            if(Math.abs(sum)<result){
                rL=liquids[l];
                rR=liquids[middle];
                result=Math.abs(sum);
            }

            if(sum>=0){ // 덜 상쇄
                right=middle-1;
            }else{ // 더 상쇄
                left=middle+1;
            }
            System.out.println("Changed left:"+left+", right:"+right);
        }
    }
}