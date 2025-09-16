package Code;

import java.util.*;
import java.io.*;

class BOJ_16401{
    static int M, result;
    static int[] snackLengths;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=16401;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        M=Integer.parseInt(st.nextToken());
        int N=Integer.parseInt(st.nextToken());
        snackLengths=new int[N];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            snackLengths[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snackLengths);
        binarySearch(snackLengths[N-1]);
        System.out.println(result);
        br.close();
    }
    static void binarySearch(int maxLength){
        int left=1;
        int right=maxLength;
        
        while(left<=right){
            int middle=(left+right)/2;
            int count=0;
            for(int snackLength:snackLengths){
                count+=snackLength/middle;
            }
            if(count<M){ // 더 잘게 썰어야 함
                right=middle-1;
            }else{ // 더 크게 썰어보기
                left=middle+1;
                result=middle;
            }
        }
    }
}