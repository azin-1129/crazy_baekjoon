package Code;

import java.util.*;
import java.io.*;

class BOJ_1365{
    static int[] poles, memo;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1365;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        memo=new int[N+1];

        poles=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int len=0;
        int idx=0;
        for(int n=0;n<N;n++){
            if(memo[len]<poles[n]){
                memo[++len]=poles[n]; // len++ 이면 무조건 0이랑 비교할 수밖에 없음
            }else{
                idx=binarySearch(1,len,poles[n]);
                System.out.println("idx:"+idx);
                memo[idx]=poles[n];
            }
            System.out.println(Arrays.toString(memo));
        }

        System.out.println(N-len);
        br.close();
    }
    static int binarySearch(int left, int right, int target){
        while(left<=right){
            int middle=(left+right)/2;
            if(memo[middle]<target){
                left=middle+1;
            }else if(memo[middle]>target){
                right=middle-1;
            }
        }

        System.out.println("L:"+left+", R:"+right);
        return left;
    }
}