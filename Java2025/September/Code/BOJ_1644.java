package Code;

import java.util.*;
import java.io.*;

class BOJ_1644{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1644;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int primeCount=Integer.valueOf(N)-1;
        boolean[] isNotPrime=new boolean[N+1];
        // 1~N까지 소수 구하기
        for(int n=2;n<=Math.sqrt(N);n++){
            if(isNotPrime[n]){
                continue;
            }
            for(int m=n*n;m<=N;m+=n){
                if(isNotPrime[m]){
                    continue;
                }
                isNotPrime[m]=true;
                primeCount--;
            }
        }

        primeCount+=1; // 마지막 start 점검
        int idx=0;
        int[] primes=new int[primeCount];
        for(int i=2;i<=N;i++){
            if(isNotPrime[i]==false){
                primes[idx++]=i;
            }
        }

        // 투 포인터
        int result=0;
        int start=0;
        int end=0;
        int sum=0;
        while(start<=end && end<primeCount){
            System.out.println("start:"+start+", end:"+end);
            if(sum<N){
                sum+=primes[end++];
                System.out.println("end:"+end);
            }else{
                if(sum==N){
                    result++;
                }
                sum-=primes[start++];
            }
        }

        System.out.println(result);
        br.close();
    }
}