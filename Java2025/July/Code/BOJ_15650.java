package Code;

import java.util.*;
import java.io.*;

class BOJ_문제번호{
    static StringBuilder sb=new StringBuilder();
    static int N, M;
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15650;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        result=new int[M];
        visited=new boolean[N];

        perm(0, 0);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void perm(int start, int depth){
        if(depth==M){
            for(int i=0;i<M;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            return;
        }

        for(int i=start;i<N;i++){
            if(!visited[i]){
                visited[i]=true;
                result[depth]=(i+1);
                perm(i+1, depth+1);
                visited[i]=false;
            }
        }
    }
}