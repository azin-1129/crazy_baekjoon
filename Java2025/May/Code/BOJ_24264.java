package Code;

import java.io.*;

class BOJ_24264{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        System.out.println((long)Math.pow(n, 2));
        System.out.println(2);
        br.close();
    }
}