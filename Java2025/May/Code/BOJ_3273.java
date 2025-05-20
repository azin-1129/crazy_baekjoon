package Code;

import java.util.*;
import java.io.*;

class BOJ_3273{
    static int LIMIT=2;
    static int result, x, n;
    static boolean[] visited;
    static int[] combResult=new int[2];
    static int[] nums;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        // 원소 수가 최대 2개인 조합 원소의 합이 x와 일치하는 조합 수 구하기?
        n=Integer.parseInt(br.readLine());
        nums=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        x=Integer.parseInt(br.readLine());

        visited=new boolean[n];
        comb(0, 0);
        System.out.println(result);
        br.close();
    }
    static void comb(int start, int depth){
        if(depth==LIMIT){
            if((combResult[0]+combResult[1])==x){
                result+=1;
            }
            return;
        }

        for(int i=start;i<n;i++){
            if(!visited[i]){
                combResult[depth]=nums[i];
                visited[i]=true;
                comb(i+1, depth+1);
                visited[i]=false;
            }
        }
    }
}