package Code;

import java.util.*;
import java.io.*;

class BOJ_15663{
    static int[] result, nums;
    static boolean[] visited;
    static int N, M;
    static int[][][] check;
    static int start;
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
        check=new int[max+1][max+1][M];

        Arrays.sort(nums);
        perm(0);

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void perm(int depth){
        // System.out.println("perm?");
        if(depth>=M){
            if(check[start][result[M-1]][depth-1]==2){ // 이미 봤던 조합
                return;
            }
            for(int i=0;i<M;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            // System.out.println(Arrays.toString(result).replaceAll("[\\[\\],]",""));
            return;
        }

        // perm(0)
        // visited[0]=true;
        // result[0]=nums[0];
        // perm(1);
        // visited[1]=true;
        // result[1]=nums[1];
        // perm(2);

        // check가 3차원 배열, check[N][N][M]
        // 1 depth부터 원소 선정 시작이니 check[1depth원소][다음선택한원소][depth] 갱신 필요
        for(int i=0;i<N;i++){
            // System.out.println("perm?");
            if(!visited[i]){
                visited[i]=true;
                result[depth]=nums[i];

                if(depth==0){
                    start=nums[i]; // 1depth로 선정될 원소
                }else{ // 2depth 원소 선택 시작
                    check[start][nums[i]][depth]+=1;
                }
                perm(depth+1);
                visited[i]=false;
            }
        }
    }
}