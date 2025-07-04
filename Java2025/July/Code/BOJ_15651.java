package Code;

import java.util.*;
import java.io.*;

class BOJ_15651{
    static int N,M;
    static int[] result;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15651;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        result=new int[M];
        perm(0, 0);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void perm(int start, int depth){
        if(depth>=M){
            for(int i=0;i<M;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            return;
        }

        for(int i=0;i<N;i++){
            result[depth]=i+1;
            perm(i, depth+1);
        }
    }
}