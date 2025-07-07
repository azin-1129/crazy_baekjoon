package Code;

import java.util.*;
import java.io.*;

class BOJ_15656{
    static int[] result, nums;
    static int N,M;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15656;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        nums=new int[N];
        result=new int[M];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        rec(0);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void rec(int depth){
        if(depth>=M){
            for(int i=0;i<M;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");

            return;
        }

        for(int i=0;i<N;i++){
            result[depth]=nums[i];
            rec(depth+1);
        }
    }
}