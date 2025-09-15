package Code;

import java.util.*;
import java.io.*;

class BOJ_1822{
    static int nB;
    static long[] B;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1822;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int nA=Integer.parseInt(st.nextToken());
        nB=Integer.parseInt(st.nextToken());

        long[] A=new long[nA];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<nA;i++){
            A[i]=Long.parseLong(st.nextToken());
        }
        B=new long[nB];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<nB;i++){
            B[i]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        // System.out.println("A:"+Arrays.toString(A));
        // System.out.println("B:"+Arrays.toString(B));
        int count=0;
        StringBuilder sb=new StringBuilder();
        for(Long a:A){
            if(binarySearch(a)){
                continue;
            }else{
                count+=1;
                sb.append(a+" ");
            }
        }

        // System.out.println();
        if(count!=0){
            System.out.println(count);
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
        }else{
            System.out.println(0);
        }
        br.close();
    }
    static boolean binarySearch(long a){
        int left=0;
        int right=nB-1;

        // System.out.println(a+"를 찾고 있습니다.");

        while(left<=right){
            int middle=(left+right)/2;
            // System.out.println("L:"+left+", R:"+right);
            // System.out.println(middle);
            if(B[middle]>a){
                right=middle-1;
            }else if(B[middle]<a){
                left=middle+1;
            }else{
                return true;
            }
        }

        return false;
    }
}