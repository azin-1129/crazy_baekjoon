package Code;

import java.util.*;
import java.io.*;

class BOJ_14888{
    static long maxResult=Long.MIN_VALUE;
    static long minResult=Long.MAX_VALUE;
    static int N;
    static int[] nums;
    static char[] originOperators; // 순열 만들 때 사용할 연산자 배열 원본
    static char[] operatorPermutation; // 순열 결과가 출력되는 연산자 배열
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14888;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        nums=new int[N];
        originOperators=new char[N-1];
        operatorPermutation=new char[N-1];
        visited=new boolean[N-1];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        
        char[] operators={'+', '-', '*', '/'};
        int idx=0;
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<4;i++){
            int opCount=Integer.parseInt(st.nextToken());
            for(int j=0;j<opCount;j++){
                originOperators[idx++]=operators[i];
            }
        }

        perm(0);
        System.out.println(maxResult);
        System.out.println(minResult);
        br.close();
    }
    static void perm(int depth){
        if(depth>=(N-1)){
            calc();
        }

        for(int i=0;i<(N-1);i++){
            if(!visited[i]){
                visited[i]=true;
                operatorPermutation[depth]=originOperators[i];
                perm(depth+1);
                visited[i]=false;
            }
        }
    }
    static void calc(){
        int res=nums[0];
        for(int i=0;i<(N-1);i++){
            switch(operatorPermutation[i]){
                case '+':
                    res+=nums[i+1];
                    break;
                case '-':
                    res-=nums[i+1];
                    break;
                case '*':
                    res*=nums[i+1];
                    break;
                case '/':
                    res/=nums[i+1];
                    break;
            }
        }

        if(minResult>res){
            minResult=res;
        }
        
        if(maxResult<res){
            maxResult=res;
        }
    }
}