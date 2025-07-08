package Code;

import java.util.*;
import java.io.*;

class BOJ_15663{
    static int[] result, nums;
    static boolean[] visited;
    static int N, M;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15663;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        nums=new int[N];
        visited=new boolean[N];
        result=new int[M];
        st=new StringTokenizer(br.readLine());
        int max=0;
        for(int i=0;i<N;i++){
            nums[i]=Integer.parseInt(st.nextToken());
            max=Math.max(nums[i], max);
        }

        Arrays.sort(nums);
        perm(0);

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void perm(int depth){
        if(depth>=M){
            for(int i=0;i<M;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            return;
        }
        int tmp=0;
        for(int i=0;i<N;i++){
            if(!visited[i]){
                if(nums[i]==tmp){
                    continue;
                }
                visited[i]=true;
                result[depth]=nums[i];
                tmp=nums[i];
                perm(depth+1);
                visited[i]=false;
            }
        }
    }
}