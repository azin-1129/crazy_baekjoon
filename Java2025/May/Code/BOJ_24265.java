package Code;

import java.io.*;

class BOJ_24265{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        long n=Long.parseLong(br.readLine());

        // 1부터 n-1 까지의 합
        // 1~n 합 공식: n(n+1)/2
        System.out.println(((n-1)*(n))/2);
        System.out.println(2);
        br.close();
    }
}