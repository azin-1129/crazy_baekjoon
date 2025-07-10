package Code;

import java.util.*;
import java.io.*;

class BOJ_15664{
    static int N,M;
    static int[] nums, result;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15664;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        nums=new int[N];
        result=new int[M];

        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        comb(0, 0);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void comb(int start, int depth){
        if(depth>=M){
            for(int i=0;i<M;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            return;
        }

        int temp=0;
        for(int i=start;i<N;i++){
            if(nums[i]==temp){
                continue;
            }
            result[depth]=nums[i];
            temp=nums[i];
            comb(i+1, depth+1);
        }
    }
}