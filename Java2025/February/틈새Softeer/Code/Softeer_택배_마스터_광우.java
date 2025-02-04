package Code;

import java.util.*;
import java.io.*;

class Softeer_택배_마스터_광우{
    static int N, M, K;
    static int weightMin=Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] result, weights;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="택배_마스터_광우";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));
        StringTokenizer st;

        int[] inputs=new int[3];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<3;i++){
            int inputValue=Integer.parseInt(st.nextToken());
            inputs[i]=inputValue;
        }
        N=inputs[0];
        M=inputs[1];
        K=inputs[2];
        result=new int[N];
        visited=new boolean[N];

        weights=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int inputValue=Integer.parseInt(st.nextToken());
            weights[i]=inputValue;
        }

        permutation(0);
        
        System.out.println(weightMin);

        br.close();
    }
    public static void permutation(int cnt){
        if(cnt==N){
            int index=0;
            int weightTemp=0;
            int count=0;
            int weightSum=0;

            while(true){
                if(index==N){
                    index=0;
                }
                if((weightTemp+result[index])<=M){
                    weightTemp+=result[index++];
                }else{
                    count+=1;
                    weightSum+=weightTemp;
                    weightTemp=0;

                    if(count==K){
                        break;
                    }
                }
            }

            weightMin=Math.min(weightMin, weightSum);

            return;
        }

        for(int i=0;i<N;i++){
            if(visited[i]){
                continue;
            }

            visited[i]=true;
            result[cnt]=weights[i];
            permutation(cnt+1);
            visited[i]=false;
        }
    }
}