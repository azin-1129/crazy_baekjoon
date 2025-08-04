package Code;

import java.util.*;
import java.io.*;

class BOJ_11053{
    static int[] dp;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11053;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        dp=new int[N];
        int[] numbers=new int[N];
        int idx=1;
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }
        // System.out.println("numbers:"+Arrays.toString(numbers));
        dp[0]=numbers[0];
        for(int i=1;i<N;i++){
            System.out.println("현재 idx:"+idx);
            if(dp[idx-1]<numbers[i]){
                dp[idx++]=numbers[i];
            }else{
                int foundIdx=binarySearch(0, idx-1, numbers[i]);
                System.out.println("이분탐색 idx:"+foundIdx);
                dp[foundIdx]=numbers[i];
            }
            // System.out.println(Arrays.toString(dp));
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(idx);
        br.close();
    }
    static int binarySearch(int start, int end, int value){
        while(start<=end){
            int middle=(start+end)/2;
            if(dp[middle]>value){
                end=middle-1;
            }else if(dp[middle]<value){
                start=middle+1;
            }else{
                return middle;
            }
        }
        return start;
    }
}