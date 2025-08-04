package Code;

import java.util.*;
import java.io.*;

class BOJ_11053{
    static int[] numbers;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11053;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[] dp=new int[N];
        numbers=new int[N];
        int idx=0;
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }
        // System.out.println("numbers:"+Arrays.toString(numbers));
        for(int i=0;i<N;i++){
            System.out.println("현재 idx:"+idx);
            if(dp[idx]<numbers[i]){
                dp[idx++]=numbers[i];
            }else if(dp[idx]>numbers[i]){
                int foundIdx=binarySearch(0, idx-1, numbers[i]);
                System.out.println("이분탐색 idx:"+foundIdx);
                dp[foundIdx]=numbers[i];
            }
            System.out.println(Arrays.toString(dp));
        }

        System.out.println(idx);
        br.close();
    }
    static int binarySearch(int start, int end, int value){
        while(start<end){
            int middle=(start+end)/2;
            if(numbers[middle]<value){
                end=middle-1;
            }else if(numbers[middle]>value){
                start=middle+1;
            }else{
                return middle;
            }
        }
        return start;
    }
}