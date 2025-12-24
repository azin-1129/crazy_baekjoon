package Code;

import java.util.*;
import java.io.*;

class BOJ_11562{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11562;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        // 플로이드 그래프 초기화
        // weight는, 필요한 양방향 간선의 수
        int INF=(N*(N-1))/2+1;
        int[][] floyd=new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            Arrays.fill(floyd[i], INF);
            floyd[i][i]=0;
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int isTwoWay=Integer.parseInt(st.nextToken());
            floyd[from][to]=0; // from -> to는 무조건 가능하다.
            if(isTwoWay==0){ // 단방향
                floyd[to][from]=1; // to -> from은 양방향이 필요하다.
            }else{
                floyd[to][from]=0;
            }
        }

        for(int z=1;z<=N;z++){
            for(int x=1;x<=N;x++){
                for(int y=1;y<=N;y++){
                    floyd[x][y]=Math.min(floyd[x][y], floyd[x][z]+floyd[z][y]);
                }
            }
        }

        StringBuilder sb=new StringBuilder();
        int K=Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            sb.append(floyd[from][to]+"\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}